package com.shaodw.zxing;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import jp.sourceforge.qrcode.util.Color;

public class LogoUtil {
	//传入一个logo 二维码  返回一个带logo的二维码
	public static BufferedImage getLogoBufferedImage(String logo, BufferedImage oriBuImage) throws Exception {
		//在二维码上画logo 在二维码上创建画板
		Graphics2D graphics2d = oriBuImage.createGraphics();
		//画logo      String -> BufferedImage 将logo放入内存
		BufferedImage logoImg = ImageIO.read(new File(logo));
		//得到二维码的宽和高
		int width = oriBuImage.getWidth();
		int height = oriBuImage.getHeight();
		//纯logo
		graphics2d.drawImage(logoImg, width * 2/5, height*2/5,width * 1/5, height * 1/5, null);
		//在二维码上从width * 2/5, height*2/5开始画 画width * 1/5, height * 1/5这么大
		
		//画边框 产生一个画白色圆角正方形的画笔
		BasicStroke stroke = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		graphics2d.setStroke(stroke);//关联画笔和画板
		//创建一个正方形
		RoundRectangle2D.Float round = new RoundRectangle2D.Float(width * 2/5, height*2/5, width * 1/5, height * 1/5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		graphics2d.setColor(java.awt.Color.white);
		graphics2d.draw(round);

		//画一个灰色的小边框
		BasicStroke stroke2 = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		graphics2d.setStroke(stroke2);//关联画笔和画板
		//创建一个正方形
		RoundRectangle2D.Float round2 = new RoundRectangle2D.Float(width * 2/5 + 2, height*2/5 + 2, width * 1/5 - 4, height * 1/5 - 4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		graphics2d.setColor(java.awt.Color.gray);
		graphics2d.draw(round2);
		graphics2d.dispose();
		oriBuImage.flush();
		return oriBuImage;
	}
}
