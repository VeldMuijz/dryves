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
    
    private String van;
    private String naar;
    private String onderwerp;
    private String bericht;
    private String attachment;
    
    public void verstuurEmail(String van, String naar, String onderwerp, String bericht, String attachment){
    
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
                        


                    // Part two is attachment  

                    DataSource source =
                            new FileDataSource(attachment);
                    
                    messageBodyPart.setDataHandler(
                            new DataHandler(source));
                    
                    messageBodyPart.setFileName(attachment);
                    
                    multipart.addBodyPart(messageBodyPart); 
                    
                    message.setContent(multipart);
                        
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
    
    }
 
}
