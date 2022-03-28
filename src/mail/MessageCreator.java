package mail;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MessageCreator {
	private Session ses;

	public MessageCreator() {
		super();
		this.ses = new SessionProvider().defalutSession();
	}

	public void sendDefaultMessage() {
		Message message = new MimeMessage(ses);
		try {
			message.setFrom(new InternetAddress("mailfortesting1damdsr@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("dani.serrano00@gmail.com"));
			message.setSubject("Mail Subject");
			String msg = "This is my first email using JavaMailer";

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
}
