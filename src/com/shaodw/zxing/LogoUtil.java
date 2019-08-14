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
	//����һ��logo ��ά��  ����һ����logo�Ķ�ά��
	public static BufferedImage getLogoBufferedImage(String logo, BufferedImage oriBuImage) throws Exception {
		//�ڶ�ά���ϻ�logo �ڶ�ά���ϴ�������
		Graphics2D graphics2d = oriBuImage.createGraphics();
		//��logo      String -> BufferedImage ��logo�����ڴ�
		BufferedImage logoImg = ImageIO.read(new File(logo));
		//�õ���ά��Ŀ�͸�
		int width = oriBuImage.getWidth();
		int height = oriBuImage.getHeight();
		//��logo
		graphics2d.drawImage(logoImg, width * 2/5, height*2/5,width * 1/5, height * 1/5, null);
		//�ڶ�ά���ϴ�width * 2/5, height*2/5��ʼ�� ��width * 1/5, height * 1/5��ô��
		
		//���߿� ����һ������ɫԲ�������εĻ���
		BasicStroke stroke = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		graphics2d.setStroke(stroke);//�������ʺͻ���
		//����һ��������
		RoundRectangle2D.Float round = new RoundRectangle2D.Float(width * 2/5, height*2/5, width * 1/5, height * 1/5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		graphics2d.setColor(java.awt.Color.white);
		graphics2d.draw(round);

		//��һ����ɫ��С�߿�
		BasicStroke stroke2 = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		graphics2d.setStroke(stroke2);//�������ʺͻ���
		//����һ��������
		RoundRectangle2D.Float round2 = new RoundRectangle2D.Float(width * 2/5 + 2, height*2/5 + 2, width * 1/5 - 4, height * 1/5 - 4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		graphics2d.setColor(java.awt.Color.gray);
		graphics2d.draw(round2);
		graphics2d.dispose();
		oriBuImage.flush();
		return oriBuImage;
	}
}
