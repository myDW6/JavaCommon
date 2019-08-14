package com.shaodw.zxing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import jp.sourceforge.qrcode.util.Color;
/**
 * ZXing��ά�빤����
 * @author shaodw
 *
 */
public class ZXingUtil {
	
	//����
	public static void encoderZXingCode(String content, String format,String imgPath,int width ,int height) throws Exception {
		Map<EncodeHintType, Object> hints = new Hashtable<>();
		//�Ŵ���
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		//����
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		//��߾ࣺmargin
		hints.put(EncodeHintType.MARGIN, 1);
		//�ڴ��е�һ��ͼƬ����ʱ��Ҫ��ͼƬ�Ƕ�ά�� ->����boolean[][] ��Ҫһ��BitMatrix
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
		/**
		 * content���ܵ�����
		 * BarcodeFormat.QR_CODE������ά�� ����������������
		 * hints:�����漰�Ĳ��������룬�Ŵ���
		 */
		
		BufferedImage bufImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				bufImg.setRGB(i, j, (bitMatrix.get(i, j) ? Color.BLACK : Color.WHITE));//����ʹ��qrcode��color��
			}
		}
		
		// logo
		bufImg = LogoUtil.getLogoBufferedImage("src/google.jpg", bufImg);
		
		
		
		ImageIO.write(bufImg, format, new File(imgPath));
	}
	//����
	public static void decoderZXingCode(File file) throws Exception {
		if (!file.exists()) {
			return ;
		}
		
		//file -> memory
		BufferedImage bufImg = ImageIO.read(file);
		MultiFormatReader formatReader = new MultiFormatReader();
		LuminanceSource source = new BufferedImageLuminanceSource(bufImg);
		Binarizer binarizer = new HybridBinarizer(source);
		BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
		Map map = new HashMap();
		map.put(EncodeHintType.CHARACTER_SET, "UTf-8");
		Result result = formatReader.decode(binaryBitmap, map);
		System.out.println(result.toString());
	}
	
}
