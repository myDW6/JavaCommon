package com.shaodw.qrcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

import jp.sourceforge.qrcode.QRCodeDecoder;

public class QRCodeUtil {
	
	//加密
	/**
	 * 
	 * @param content 二维码内容
	 * @param imgPath 生成的二维码路径
	 * @param imgType 图片的格式
	 * @param size	     尺寸大小
	 */
	public void encoderQRCode(String content, String imgPath, String imgType, int size) throws Exception{
		//BufferedImage : 可以代表内存中的一张图片
		BufferedImage bufferedImage = qRCodeCommon(content, imgType, size);
		File file = new File(imgPath);//"src/二维码.ping" -> 二维码.png
		//生成图片 
		ImageIO.write(bufferedImage, imgType, file);
	}
	
	//通过该方法产生一个二维码的BUfferedImage
	public BufferedImage qRCodeCommon(String content, String imgType, int size) throws Exception{
		//String -> boolean[][] 需要使用Qrcode对象
		Qrcode qrCodeHandler = new Qrcode();
		//设置二维码的排错率：7% L　M Q H 30% 排错率越高可存储的信息越少 但是对二维码清晰度要求越小
		qrCodeHandler.setQrcodeErrorCorrect('M');
		//可存放的信息类型:N:数字 	A:数字 + A-Z  B:所有 
		qrCodeHandler.setQrcodeEncodeMode('B');
		//二维码尺寸 取值范围: 1-40
		qrCodeHandler.setQrcodeVersion(size);

		boolean[][] codeOut = qrCodeHandler.calQrcode(content.getBytes("UTF-8"));//String -> byte[] -> boolean[][]
		//以上得到了布尔值的二维数组
		
		int imgSize = 67 + 12 * (size - 1);
		BufferedImage bufImg = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);//颜色类型是RGB
		Graphics2D graphics2d = bufImg.createGraphics();//产生画图的2D面板
		graphics2d.setBackground(Color.white);//背景色设置为白色
		graphics2d.clearRect(0, 0, imgSize, imgSize);//初始化 将这个画板变白 （设置变白的区域）
		graphics2d.setColor(Color.black);//设置画板上图像的颜色
		
		int pixOff = 2;//设置一个偏移量，使得二维码图标不会填满整个图片
		//遍历二维数组, true填充黑色
		for (int i = 0; i < codeOut.length; i++) {
			for (int j = 0; j < codeOut.length; j++) {
				if (codeOut[i][j]) {
					graphics2d.fillRect(i*3 + pixOff, j*3 + pixOff, 3, 3);//设置每一个小方块边长为3
				}
			}
		}
		
		//增加logo
		//将硬盘中的图片加载为Image对象
		Image logo = ImageIO.read(new File("src/google.jpg"));
		int maxHeight = bufImg.getHeight();
		int maxWidth = bufImg.getWidth();
		//在已生成的二维码上画logo
		graphics2d.drawImage(logo, imgSize/5*2, imgSize/5*2, maxWidth/5, maxHeight/5, null);
		
		graphics2d.dispose(); //释放画板空间
		bufImg.flush();//清理    强制将管道的东西刷到硬盘
		return bufImg;
	}
	
	
	//解密 (图片信息 -> 文字)
	public String decoderQRCode(String imgPath) throws Exception {
		//BufferedImage 内存中的图片
		BufferedImage bufImg = ImageIO.read(new File(imgPath));
		//解密
		QRCodeDecoder decoder = new QRCodeDecoder();
		MyQRCodeImage codeImage = new MyQRCodeImage(bufImg);
		byte[] bys = decoder.decode(codeImage);
		return new String(bys, "UTF-8");
	}
}
