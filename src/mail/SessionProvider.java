package mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class SessionProvider {
	private Properties prop;

	protected SessionProvider() {
		this.prop = new Properties();
	}

	protected Session defalutSession() {
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
