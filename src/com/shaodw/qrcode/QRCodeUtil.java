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
	
	//����
	/**
	 * 
	 * @param content ��ά������
	 * @param imgPath ���ɵĶ�ά��·��
	 * @param imgType ͼƬ�ĸ�ʽ
	 * @param size	     �ߴ��С
	 */
	public void encoderQRCode(String content, String imgPath, String imgType, int size) throws Exception{
		//BufferedImage : ���Դ����ڴ��е�һ��ͼƬ
		BufferedImage bufferedImage = qRCodeCommon(content, imgType, size);
		File file = new File(imgPath);//"src/��ά��.ping" -> ��ά��.png
		//����ͼƬ 
		ImageIO.write(bufferedImage, imgType, file);
	}
	
	//ͨ���÷�������һ����ά���BUfferedImage
	public BufferedImage qRCodeCommon(String content, String imgType, int size) throws Exception{
		//String -> boolean[][] ��Ҫʹ��Qrcode����
		Qrcode qrCodeHandler = new Qrcode();
		//���ö�ά����Ŵ��ʣ�7% L��M Q H 30% �Ŵ���Խ�߿ɴ洢����ϢԽ�� ���ǶԶ�ά��������Ҫ��ԽС
		qrCodeHandler.setQrcodeErrorCorrect('M');
		//�ɴ�ŵ���Ϣ����:N:���� 	A:���� + A-Z  B:���� 
		qrCodeHandler.setQrcodeEncodeMode('B');
		//��ά��ߴ� ȡֵ��Χ: 1-40
		qrCodeHandler.setQrcodeVersion(size);

		boolean[][] codeOut = qrCodeHandler.calQrcode(content.getBytes("UTF-8"));//String -> byte[] -> boolean[][]
		//���ϵõ��˲���ֵ�Ķ�ά����
		
		int imgSize = 67 + 12 * (size - 1);
		BufferedImage bufImg = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);//��ɫ������RGB
		Graphics2D graphics2d = bufImg.createGraphics();//������ͼ��2D���
		graphics2d.setBackground(Color.white);//����ɫ����Ϊ��ɫ
		graphics2d.clearRect(0, 0, imgSize, imgSize);//��ʼ�� ����������� �����ñ�׵�����
		graphics2d.setColor(Color.black);//���û�����ͼ�����ɫ
		
		int pixOff = 2;//����һ��ƫ������ʹ�ö�ά��ͼ�겻����������ͼƬ
		//������ά����, true����ɫ
		for (int i = 0; i < codeOut.length; i++) {
			for (int j = 0; j < codeOut.length; j++) {
				if (codeOut[i][j]) {
					graphics2d.fillRect(i*3 + pixOff, j*3 + pixOff, 3, 3);//����ÿһ��С����߳�Ϊ3
				}
			}
		}
		
		//����logo
		//��Ӳ���е�ͼƬ����ΪImage����
		Image logo = ImageIO.read(new File("src/google.jpg"));
		int maxHeight = bufImg.getHeight();
		int maxWidth = bufImg.getWidth();
		//�������ɵĶ�ά���ϻ�logo
		graphics2d.drawImage(logo, imgSize/5*2, imgSize/5*2, maxWidth/5, maxHeight/5, null);
		
		graphics2d.dispose(); //�ͷŻ���ռ�
		bufImg.flush();//����    ǿ�ƽ��ܵ��Ķ���ˢ��Ӳ��
		return bufImg;
	}
	
	
	//���� (ͼƬ��Ϣ -> ����)
	public String decoderQRCode(String imgPath) throws Exception {
		//BufferedImage �ڴ��е�ͼƬ
		BufferedImage bufImg = ImageIO.read(new File(imgPath));
		//����
		QRCodeDecoder decoder = new QRCodeDecoder();
		MyQRCodeImage codeImage = new MyQRCodeImage(bufImg);
		byte[] bys = decoder.decode(codeImage);
		return new String(bys, "UTF-8");
	}
}
