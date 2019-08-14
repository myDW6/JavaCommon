package com.shaodw.zxing;

import java.io.File;

public class ZXingTest {
	public static void main(String[] args) throws Exception {
		String imgPath = "src/zing∂˛Œ¨¬Î.gif";
		String content = "http://www.baidu.com";
		String img1 = "src/1.png";
		//º”√‹
		ZXingUtil.encoderZXingCode(content, "gif", imgPath, 430, 430);
		//Ω‚√‹
		ZXingUtil.decoderZXingCode(new File(imgPath));
		ZXingUtil.decoderZXingCode(new File(img1));
	}
	
}
