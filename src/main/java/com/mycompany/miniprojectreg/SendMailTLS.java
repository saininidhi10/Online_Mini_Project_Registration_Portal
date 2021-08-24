package com.mycompany.miniprojectreg;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/mail")
public class SendMailTLS {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{a}")
	 public String email(@PathParam("a") String m)  {
                
		System.out.println("In Mailing System");
             final String username = "miniprojportal@gmail.com";
		final String password = "project123";
                String res=null;
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
                                System.out.println("Checking miniproject email");
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("miniproj@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(m));
			message.setSubject("UNREGISTERED FOR MINI PROJECT");
			message.setText("Dear Student, You are unregistered for mini project in this academic session. Kindly register before deadline gets over.!"
				+ "\n\n Regards,\n" + "Computer Dept, VIT, Pune");

			Transport.send(message);

			System.out.println("Done");
                        res="Done";

		} catch (MessagingException e) {
                        res="Error";
			throw new RuntimeException(e);
		}
         return res;       
	}
}
