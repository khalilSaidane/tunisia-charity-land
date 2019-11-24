/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import java.sql.SQLException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author oflcad
 */
public class SendMailSSL {
     
     public SendMailSSL(){
     }
     
     
     
     public void sendEmail(String email, String username ) throws SQLException{
         
                UserAuthenticationService service = new UserAuthenticationService();
                String confirmation_tokenSent = service.getConfirmationToken(email);
                
                System.out.println("we are sending an email with this confirmation token: " + confirmation_tokenSent);
                Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
                                @Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("","");
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(""));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(""));
			message.setSubject("Verification for Tunisia Charity Land subscription");
			message.setText("Dear Mr/Mme '"+ username +"'," +
                                        
					"\n\n Welcome to Tunisia Charity Land,"
                                + " Please verify your email with copying this confirmation Token"
                                + " please Copy this confirmation token :"
                                + " '"+confirmation_tokenSent+"'");

			Transport.send(message);

			System.out.println("we have sent an email to " + email);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
     
     
     public boolean adminApiTest() throws SQLException{
         boolean isWorking = false;
         
         
                
                
                Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
                                @Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("","");
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("example@example.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("developped@esprit.com"));
			message.setSubject("Admin MAILING API TEST");
			message.setText("Admin, " +
                                        
					"\n\n Welcome to Tunisia Charity Land,"
                                +" This is a simple mail api test, please refer from replying to this email"
                                + "Best Regards,"
                                + "You code! :)");

			Transport.send(message);

			isWorking = true;

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
            return isWorking;
	}
     
     
     public boolean contactMaker(String email, String subject, String body) {
         boolean isWorking = false;
         
         
                
                
                Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
                                @Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("omarlakhdhar@gmail.com","mabroukhabiba91*/");
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("omarlakhdhar@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email));
			message.setSubject(subject);
			message.setText(body);
			Transport.send(message);

			isWorking = true;

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
            return isWorking;
     }
     
     }
    


