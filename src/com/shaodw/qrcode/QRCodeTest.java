package com.shaodw.qrcode;

import org.junit.Test;

/**
 * 	生成二维码	{
 * 				生成二维码的路径：src/二维码.png
 * 				二维码中隐藏的文字或网址信息："hello world"
 * 			}
 * 	加密：信息 ——>二维码
 * 	解密： 二维码——>信息
 * @author shaodw
 *
 */
public class QRCodeTest {
	@Test
	public void testEncoder() throws Exception {
		String imgPath = "src/二维码.png";
		//String content = "helloworld";
		String content = "https://mydw6.github.io/";
		QRCodeUtil codeUtil = new QRCodeUtil();
		codeUtil.encoderQRCode(content, imgPath, "png", 17);
	}
	
	@Test
	public void testDecoder() throws Exception {
		String imgPath = "src/二维码.png";
		QRCodeUtil codeUtil = new QRCodeUtil();
		String s = codeUtil.decoderQRCode(imgPath);
		System.out.println(s);
	}
}
