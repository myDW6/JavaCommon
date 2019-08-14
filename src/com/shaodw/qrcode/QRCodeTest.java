package com.shaodw.qrcode;

import org.junit.Test;

/**
 * 	���ɶ�ά��	{
 * 				���ɶ�ά���·����src/��ά��.png
 * 				��ά�������ص����ֻ���ַ��Ϣ��"hello world"
 * 			}
 * 	���ܣ���Ϣ ����>��ά��
 * 	���ܣ� ��ά�롪��>��Ϣ
 * @author shaodw
 *
 */
public class QRCodeTest {
	@Test
	public void testEncoder() throws Exception {
		String imgPath = "src/��ά��.png";
		//String content = "helloworld";
		String content = "https://mydw6.github.io/";
		QRCodeUtil codeUtil = new QRCodeUtil();
		codeUtil.encoderQRCode(content, imgPath, "png", 17);
	}
	
	@Test
	public void testDecoder() throws Exception {
		String imgPath = "src/��ά��.png";
		QRCodeUtil codeUtil = new QRCodeUtil();
		String s = codeUtil.decoderQRCode(imgPath);
		System.out.println(s);
	}
}
