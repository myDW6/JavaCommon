package com.shaodw.javamail;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * 带图片和附件的邮件
 * @author shaodw
 *
 */
public class JavaMailWithAttachment {
	//邮件类型：MimeMessage 带有附件 图片
		public static MimeMessage createMimeMessage(Session session, String sender, String receive, String cReceive, String mReceive) throws Exception {
			MimeMessage message = new MimeMessage(session);
			//邮件：标题 正文 收件人   发件人   {附件 图片}
			message.setSubject("这是标题","UTF-8");
			Address address = new InternetAddress(sender,"发件人的名字","UTF-8");
			message.setFrom(address);
			
			
			
			//增加图片节点
			MimeBodyPart imagePart = new MimeBodyPart();
			imagePart.setDataHandler(new DataHandler(new FileDataSource("src/google.jpg")));
			imagePart.setContentID("myImage");
			
			//创建文本节点：是为了加载图片节点（考虑html）
			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setContent("正文内容：<img src='cid:myImage'/>", "text/html; charset=UTF-8");
			
			//将文本节点和图片节点组合成一个复合节点
			MimeMultipart multipart = new MimeMultipart();
			multipart.addBodyPart(imagePart);
			multipart.addBodyPart(textPart);
			multipart.setSubType("related");//设置关联关系
			
			//正文中只能出现普通节点 不能出现复合节点
			MimeBodyPart bodyPart = new MimeBodyPart();
			bodyPart.setContent(multipart);
			
			//附件处理
			//增加附件节点
			MimeBodyPart attachment = new MimeBodyPart();
			DataHandler fileDateHandler = new DataHandler(new FileDataSource("src/附件.txt"));
			attachment.setDataHandler(fileDateHandler);
			attachment.setFileName(MimeUtility.encodeText(fileDateHandler.getName()));//使用该工具类防止乱码     给附件设置名字
			//将处理好的文本加图片 与 附件复合成一个节点 然后再变成普通节点
			MimeMultipart mmulMultipart = new MimeMultipart();
			mmulMultipart.addBodyPart(bodyPart);
			mmulMultipart.addBodyPart(attachment);
			//附件和正文是混合关系   在正文中放 必须封装成一个节点  但是一部分是正文 一部分是附件则不需要封装成一个节点 
			mmulMultipart.setSubType("mixed");
			//将节点加入正文
			message.setContent(mmulMultipart, "text/html; charset=UTF-8");//注意这里的编码 需要与网页编码一致 
			
			//收件人类型：普通收件人TO 抄送CC 密送BCC
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receive, "普通收信人","UTF-8"));
			message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(cReceive, "抄送收信人","UTF-8"));
			message.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress(mReceive, "密送收信人","UTF-8"));
			message.setSentDate(new Date());//设置发件时间
			message.saveChanges();//保存邮件
			return message;
		}
		
		public static void main(String[] args) throws Exception {
			Properties props = new Properties();
			//邮箱的基本信息
			props.setProperty("mail.transport.protocol", "smtp");//使用的协议
			props.setProperty("mail.smtp.host", "smtp.qq.com");//协议地址
			props.setProperty("mail.smtp.port", "465");//协议端口
			props.setProperty("mail.smtp.auth", "true");//协议是否需要授权
			//SSL安全认证
			props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			//java 通过SSLSocketFactory类支持ssl验证
			props.setProperty("mail.smtp.socketFactory.fallback", "false");//只处理经过ssl认证的请求
			props.setProperty("mail.smtp.socketFactory.port", "465");//只处理经过ssl认证的请求
			//Session可以和邮箱服务器通信
			Session session = Session.getInstance(props);
			session.setDebug(true);//开启日志
			//创建邮件
			MimeMessage message = createMimeMessage(session, "634234952@qq.com", "15123096250@163.com", "15123096250@163.com", "15123096250@163.com");
			Transport transport = session.getTransport();//建立连接对象
			transport.connect("634234952@qq.com", "pgakdgrhgtwjbcig");//密码以授权码的形式体现
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		}
}
