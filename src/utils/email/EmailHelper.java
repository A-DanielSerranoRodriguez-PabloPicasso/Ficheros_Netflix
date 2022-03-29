package utils.email;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.mysql.cj.Session;
import com.mysql.cj.protocol.Message;

public class EmailHelper {
	private Session ses;

	public EmailHelper() {
		super();
		this.ses = defalutSession();
	}

	public void sendDefaultMessage(int rndm) {
		Message message = new MimeMessage(ses);
		try {
			message.setFrom(new InternetAddress("mailfortesting1damdsr@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("dani.serrano00@gmail.com"));
			message.setSubject("Mail Subject");
			String msg = "Tu codigo de confirmacion es:\n\n" + rndm + "\n\n";

			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(mimeBodyPart);

			message.setContent(multipart);

			Transport.send(message);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		}
	}

	private Session defalutSession() {
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "25");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//		prop.put("mail.smtp.ssl.protocols","TLSv1.2");

		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("mailfortesting1damdsr", "WyF8YZhh22B7CDF");
			}
		});

		return session;
	}
}
