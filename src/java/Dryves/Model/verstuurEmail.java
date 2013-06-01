/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Model;

/**
 *
 * @author RickSpijker
 */

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
 
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
 
public class verstuurEmail {
    
    String van = "dryveseu@gmail.com";
    private String naar;
    private String onderwerp;
    private String bericht;
    private String attachment;
    private String attachmentName;
    
   /**
    * 
    * @param van afzender van de e-mail
    * @param naar bestemming van de e-mail
    * @param onderwerp onderwerp van de e-mail
    * @param bericht inhoud van de e-mail
    * @param attachment bijlage van de e-mail
    * @param attachmentName naam van de bijlage van de e-mail  
    */
    
    public void verstuurEmail(String van, String naar, String onderwerp, String bericht, String attachment, String attachmentName){
    
        final String username = "dryveseu@gmail.com";
		final String password = "Qwerty!2";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
                    
                    MimeBodyPart messageBodyPart =
                            new MimeBodyPart();
                    Multipart multipart = new MimeMultipart();
                    
                    messageBodyPart = new MimeBodyPart();
                    
			Message message = new MimeMessage(session);
                        
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(naar));
			message.setSubject(onderwerp);
			message.setText(bericht);
                        
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
    
    }
    
    
        public void verstuurEmailZonderBijlage(String naar, String onderwerp, String bericht){
    
        final String username = "dryveseu@gmail.com";
		final String password = "Qwerty!2";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
                    
                    MimeBodyPart messageBodyPart =
                            new MimeBodyPart();
                    Multipart multipart = new MimeMultipart();
                    
                    messageBodyPart = new MimeBodyPart();
                    
			Message message = new MimeMessage(session);
                        
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(naar));
			message.setSubject(onderwerp);
			message.setText(bericht);
                        
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
    
    }
    
 
}
