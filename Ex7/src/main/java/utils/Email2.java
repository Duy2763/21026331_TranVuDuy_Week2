package utils;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



public class Email2 {
	
	public static void sendEmail() {
		String recipient = "tduy2706@gmail.com";
		String subject = "Subject Of Email";
		String body = "<html>" +
	              "<body style='font-family: Arial, sans-serif; margin: 20px;'>" +
	              "<h1 style='color: #333;'>Hello</h1>" +
	              "<p style='font-size: 16px; color: #666;'>This is the <strong style='color: #000;'>Lab2</strong> file.</p>" +
	              "</body>" +
	              "</html>";
		String attachmentPath = "C:\\Users\\admin\\Desktop\\week2\\Ex7\\src\\main\\java\\FilePDF\\Lab2_Servlet.pdf";
		String senderEmail = "tranvuduy2706@gmail.com";
		String password = "qymg xnpp cqdy zkga";
		String host = "smtp.gmail.com";
		int port = 587;
		Properties properties = new Properties();
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");  // Bật STARTTLS
		properties.put("mail.smtp.ssl.protocols", "TLSv1.2"); // Sử dụng TLSv1.2
		properties.put("mail.smtp.host", host); // Đảm bảo host là "smtp.gmail.com"
		Session session = Session.getInstance(properties, new Authenticator()
		{
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, password);
			}
		});
		
		try {
			// Tạo đối tượng mặc định MimeMessage.
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmail));
			message.addRecipient(Message.RecipientType.TO, new
			InternetAddress(recipient));
			message.setSubject(subject);
			// Tạo đối tượng BodyPart của email.
			BodyPart messageBodyPart = new MimeBodyPart();
			// Nội dung của email.
			messageBodyPart.setContent(body, "text/html");
			// Email sẽ gồm 2 part (text, file attached)
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			// Phần xử lý với file attached
			if (attachmentPath != null && !attachmentPath.isEmpty()) {
				MimeBodyPart attachPart = new MimeBodyPart();
				DataSource source = new FileDataSource(attachmentPath);
				attachPart.setDataHandler(new DataHandler(source));
				attachPart.setFileName(source.getName());
				multipart.addBodyPart(attachPart);
			}
			message.setContent(multipart);
			// Gửi mail
			Transport.send(message);
			System.out.println("Email with attachment sent successfully to: " + recipient);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Email2.sendEmail();
	}
	
}
