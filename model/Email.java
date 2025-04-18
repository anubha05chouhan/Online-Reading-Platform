package model;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	String message;
    String subject;
    String to;
    String from;
	public Email(String message, String subject, String to, String from) {
		this.message = message;
		this.subject = subject;
		this.to = to;
		this.from = from;
	}
	public boolean sendEmail() {
		 String host = "smtp.gmail.com";
		 Properties properties =  System.getProperties();
		 properties.put("mail.smtp.host", host);
		 properties.put("mail.smtp.port", "465");
		 properties.put("mail.smtp.ssl.enable", "true");
		 properties.put("mail.smtp.auth", "true");
		 
		 Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication("aadarshpatel901@gmail.com","llmi vxnn iuyr symj");
			}
		 });
		 session.setDebug(true);
		 
		 MimeMessage m = new MimeMessage(session);
		 
		 try 
		 {
			 m.setFrom(from);
			 m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			 m.setSubject(subject);
			 m.setText(message);
			 Transport.send(m);
			 System.out.println("message send succesfully");
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
			 return false;
		 }
		 return true;
	}
    
}
