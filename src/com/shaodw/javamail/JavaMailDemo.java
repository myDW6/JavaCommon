package com.shaodw.javamail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailDemo {
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
	
	//邮件类型：MimeMessage
	public static MimeMessage createMimeMessage(Session session, String sender, String receive, String cReceive, String mReceive) throws Exception {
		MimeMessage message = new MimeMessage(session);
		//邮件：标题 正文 收件人   发件人   {附件 图片}
		message.setSubject("这是标题","UTF-8");
		Address address = new InternetAddress(sender,"发件人的名字","UTF-8");
		message.setFrom(address);
		message.setContent("正文内容", "text/html; charset=UTF-8");//注意这里的编码 需要与网页编码一致
		
		//收件人类型：普通收件人TO 抄送CC 密送BCC
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receive, "普通收信人","UTF-8"));
		message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(cReceive, "抄送收信人","UTF-8"));
		message.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress(mReceive, "密送收信人","UTF-8"));
		message.setSentDate(new Date());//设置发件时间
		message.saveChanges();//保存邮件
		return message;
	}
}
