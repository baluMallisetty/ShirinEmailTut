package emailModule;

//File Name SendEmail.java

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class emailbasic {

public static void main(String [] args) { 
   // Recipient's email ID needs to be mentioned.
   String from = "balu.mallisetty@gmail.com";

   // Sender's email ID needs to be mentioned
   String to = "shirin.c9@gmail.com";

   // Assuming you are sending email from localhost
   String host = "smtp.gmail.com";

   // Get system properties
   Properties props = System.getProperties();

   // Setup mail server
   props.put("mail.smtp.host", host);
   props.put("mail.smtp.user", from);
   props.put("mail.smtp.password","dnbcsrijvslwywnj");
   props.put("mail.smtp.port", "587"); // 587 is the port number of yahoo mail
   props.put("mail.smtp.auth", "true");
   props.put("mail.smtp.starttls.enable", "true");
   // Get the default Session object.
   Session session = Session.getDefaultInstance(props, 
    new javax.mail.Authenticator(){
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(
                "balu.mallisetty@gmail.com", "dnbcsrijvslwywnj");// Specify the Username and the PassWord
        }
 });
   try {
      // Create a default MimeMessage object.
      MimeMessage message = new MimeMessage(session);
      
      // Set From: header field of the header.
      message.setFrom(new InternetAddress(from));

      // Set To: header field of the header.
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

      // Set Subject: header field
       message.setSubject("HEY M SUBJECT");

      // Now set the actual message
     message.setText("This is actual message");
      
     String html_display = "<h1> hiee this is html..lol!! </h1> " ;
     message.setContent(html_display, "text/html");               
      
    // STARTING MULTIPART
      
     MimeMultipart multipart = new MimeMultipart("related");
      
      //ADDING TEXT TO MULTIPART
      
      
      String headerONE="<h1> This is headerone </h1> "
      	+          "<h2> moreheaders </h2>";
      BodyPart htmlbodypart1 = new MimeBodyPart();
      htmlbodypart1.setContent(headerONE, "text/html");
     multipart.addBodyPart(htmlbodypart1);
      
      
      ////////////////////////////////new code fore Multip Attachment support//////////////
     String[] resource_path_list = new String[]{"C:\\Users\\balu\\Desktop\\email\\1.png","C:\\Users\\balu\\Desktop\\email\\2.png","C:\\Users\\balu\\Desktop\\email\\3.png"};
     for (String each_path : resource_path_list) {
    	 multipart.addBodyPart(stichBodypart(each_path));
     }
     ////////////////////////////////new code fore Multip Attachment support//////////////
     message.setContent(multipart);
      
      
      // Send message
      Transport.send(message);
      System.out.println("Sent message successfully....");
   } catch (MessagingException mex) {
      mex.printStackTrace();
   }   
}
public static BodyPart stichBodypart(String in_path) {
	 BodyPart image_html_bodypart = new MimeBodyPart();         
     //multipart.addBodyPart(image_html_bodypart );
     DataSource fds = new FileDataSource(in_path); // ADD ADDRESS
     
     try {
     image_html_bodypart.setDataHandler(new DataHandler(fds));
     image_html_bodypart.setHeader("Content-Type", "image/png; name="+UUID.randomUUID().toString()+".png");
	 image_html_bodypart.setHeader("Content-ID",  UUID.randomUUID().toString());
	 image_html_bodypart.setHeader("Content-Disposition", "inline");
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     return image_html_bodypart;
} 

public static BodyPart stichBodypart(String[] in_paths) {
	 BodyPart image_html_bodypart = new MimeBodyPart();
	for(String path_of_each_resource : in_paths) {
		 DataSource fds = new FileDataSource(path_of_each_resource); // ADD ADDRESS
		 try {
			    image_html_bodypart.setDataHandler(new DataHandler(fds));
			    image_html_bodypart.setHeader("Content-Type", "image/png; name="+UUID.randomUUID().toString()+".png");
				 image_html_bodypart.setHeader("Content-ID", UUID.randomUUID().toString());
				 image_html_bodypart.setHeader("Content-Disposition", "inline");
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	} 
    return image_html_bodypart;
} 
}