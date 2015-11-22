package lxf.qs.com;
import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;


public class Email {

    public Email() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void toAddressWithFile(String address,String title,String content,String fileAdd) {
		// TODO Auto-generated constructor stub
    	Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.qq.com");
        properties.put("mail.smtp.port", "25");
        properties.put("mail.smtp.auth", "true");
        //ʹ��JavaMail�����ʼ���5������
        //1������session
        Session session = Session.getInstance(properties);
        //����Session��debugģʽ�������Ϳ��Բ鿴��������Email������״̬
        session.setDebug(true);
        //2��ͨ��session�õ�transport����
        properties.setProperty("mail.transport.protocol", "smtp");
        Transport ts = null;
		try {
			ts = session.getTransport();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //3��ʹ��������û��������������ʼ��������������ʼ�ʱ����������Ҫ�ύ������û����������smtp���������û��������붼ͨ����֤֮����ܹ����������ʼ����ռ��ˡ�
        try {
			ts.connect("smtp.qq.com", "1174254785@qq.com", "zpamzgxtacjqhged");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //4�������ʼ�
        Message message = null;
		try {
			message = createSimpleMail(session,address,title,content,fileAdd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //5�������ʼ�
        try {
			ts.sendMessage(message, message.getAllRecipients());
			ts.close();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public static MimeMessage createSimpleMail(Session session,String toAddress,String title,String contnt,String fileAdd)
            throws Exception {
        //�����ʼ�����
        MimeMessage message = new MimeMessage(session);
        //ָ���ʼ��ķ�����
        message.setFrom(new InternetAddress("1174254785@qq.com"));
        //ָ���ʼ����ռ��ˣ����ڷ����˺��ռ�����һ���ģ��Ǿ����Լ����Լ���
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
        //�ʼ��ı���
        message.setSubject(title);
        //�ʼ����ı�����
      //  message.setContent(contnt, "text/html;charset=UTF-8");
        
        //����
        // ��multipart����������ʼ��ĸ����������ݣ������ı����ݺ͸���
        Multipart multipart = new MimeMultipart();
        // ����ʼ�����
        BodyPart contentPart = new MimeBodyPart();
        contentPart.setContent(contnt, "text/html;charset=UTF-8");
        multipart.addBodyPart(contentPart);
        File file = new File(fileAdd);
        if (file != null) {
            BodyPart attachmentBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(file);
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            
            // ���������Ľ���ļ�������ķ�������ʵ��MimeUtility.encodeWord�Ϳ��Ժܷ���ĸ㶨
            // �������Ҫ��ͨ�������Base64�����ת�����Ա�֤������ĸ����������ڷ���ʱ����������
            //sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
            //messageBodyPart.setFileName("=?GBK?B?" + enc.encode(attachment.getName().getBytes()) + "?=");
            
            //MimeUtility.encodeWord���Ա����ļ�������
            attachmentBodyPart.setFileName(MimeUtility.encodeWord(file.getName()));
            multipart.addBodyPart(attachmentBodyPart);
        }
        //���ش����õ��ʼ�����
        // ��multipart����ŵ�message��
        message.setContent(multipart);
        // �����ʼ�
        message.saveChanges();
        return message;
    }
}