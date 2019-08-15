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
 * ��ͼƬ�͸������ʼ�
 * @author shaodw
 *
 */
public class JavaMailWithAttachment {
	//�ʼ����ͣ�MimeMessage ���и��� ͼƬ
		public static MimeMessage createMimeMessage(Session session, String sender, String receive, String cReceive, String mReceive) throws Exception {
			MimeMessage message = new MimeMessage(session);
			//�ʼ������� ���� �ռ���   ������   {���� ͼƬ}
			message.setSubject("���Ǳ���","UTF-8");
			Address address = new InternetAddress(sender,"�����˵�����","UTF-8");
			message.setFrom(address);
			
			
			
			//����ͼƬ�ڵ�
			MimeBodyPart imagePart = new MimeBodyPart();
			imagePart.setDataHandler(new DataHandler(new FileDataSource("src/google.jpg")));
			imagePart.setContentID("myImage");
			
			//�����ı��ڵ㣺��Ϊ�˼���ͼƬ�ڵ㣨����html��
			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setContent("�������ݣ�<img src='cid:myImage'/>", "text/html; charset=UTF-8");
			
			//���ı��ڵ��ͼƬ�ڵ���ϳ�һ�����Ͻڵ�
			MimeMultipart multipart = new MimeMultipart();
			multipart.addBodyPart(imagePart);
			multipart.addBodyPart(textPart);
			multipart.setSubType("related");//���ù�����ϵ
			
			//������ֻ�ܳ�����ͨ�ڵ� ���ܳ��ָ��Ͻڵ�
			MimeBodyPart bodyPart = new MimeBodyPart();
			bodyPart.setContent(multipart);
			
			//��������
			//���Ӹ����ڵ�
			MimeBodyPart attachment = new MimeBodyPart();
			DataHandler fileDateHandler = new DataHandler(new FileDataSource("src/����.txt"));
			attachment.setDataHandler(fileDateHandler);
			attachment.setFileName(MimeUtility.encodeText(fileDateHandler.getName()));//ʹ�øù������ֹ����     ��������������
			//������õ��ı���ͼƬ �� �������ϳ�һ���ڵ� Ȼ���ٱ����ͨ�ڵ�
			MimeMultipart mmulMultipart = new MimeMultipart();
			mmulMultipart.addBodyPart(bodyPart);
			mmulMultipart.addBodyPart(attachment);
			//�����������ǻ�Ϲ�ϵ   �������з� �����װ��һ���ڵ�  ����һ���������� һ�����Ǹ�������Ҫ��װ��һ���ڵ� 
			mmulMultipart.setSubType("mixed");
			//���ڵ��������
			message.setContent(mmulMultipart, "text/html; charset=UTF-8");//ע������ı��� ��Ҫ����ҳ����һ�� 
			
			//�ռ������ͣ���ͨ�ռ���TO ����CC ����BCC
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receive, "��ͨ������","UTF-8"));
			message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(cReceive, "����������","UTF-8"));
			message.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress(mReceive, "����������","UTF-8"));
			message.setSentDate(new Date());//���÷���ʱ��
			message.saveChanges();//�����ʼ�
			return message;
		}
		
		public static void main(String[] args) throws Exception {
			Properties props = new Properties();
			//����Ļ�����Ϣ
			props.setProperty("mail.transport.protocol", "smtp");//ʹ�õ�Э��
			props.setProperty("mail.smtp.host", "smtp.qq.com");//Э���ַ
			props.setProperty("mail.smtp.port", "465");//Э��˿�
			props.setProperty("mail.smtp.auth", "true");//Э���Ƿ���Ҫ��Ȩ
			//SSL��ȫ��֤
			props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			//java ͨ��SSLSocketFactory��֧��ssl��֤
			props.setProperty("mail.smtp.socketFactory.fallback", "false");//ֻ������ssl��֤������
			props.setProperty("mail.smtp.socketFactory.port", "465");//ֻ������ssl��֤������
			//Session���Ժ����������ͨ��
			Session session = Session.getInstance(props);
			session.setDebug(true);//������־
			//�����ʼ�
			MimeMessage message = createMimeMessage(session, "634234952@qq.com", "15123096250@163.com", "15123096250@163.com", "15123096250@163.com");
			Transport transport = session.getTransport();//�������Ӷ���
			transport.connect("634234952@qq.com", "pgakdgrhgtwjbcig");//��������Ȩ�����ʽ����
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		}
}
