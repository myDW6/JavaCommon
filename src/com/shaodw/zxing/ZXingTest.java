package com.shaodw.zxing;

import java.io.File;

public class ZXingTest {
	public static void main(String[] args) throws Exception {
		String imgPath = "src/zing��ά��.gif";
		String content = "http://www.baidu.com";
		String img1 = "src/1.png";
		//����
		ZXingUtil.encoderZXingCode(content, "gif", imgPath, 430, 430);
		//����
		ZXingUtil.decoderZXingCode(new File(imgPath));
		ZXingUtil.decoderZXingCode(new File(img1));
	}
	
}
