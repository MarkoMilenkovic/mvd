package rs.mvd.services;

import org.springframework.stereotype.Service;

import com.sun.mail.smtp.SMTPMessage;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;


@Service
public class EmailService {

    private final String username = "marko12993@gmail.com";
    private final String password = "supermario123";
    private Properties properties;

    private Properties createProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "805");
        return props;
    }

    private Session createSession(Properties props) {
        return Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    private void createAndSendMessage(Session session, String reciever, String content, String title) throws MessagingException {
        SMTPMessage message = new SMTPMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(reciever));

        message.setSubject(title);
//        message.setText("Testing mail!");
        message.setContent(content,  "text/html");
        message.setNotifyOptions(SMTPMessage.NOTIFY_SUCCESS);
        Transport.send(message);
    }

    public void sendMail(String reciever, String content, String title) throws MessagingException {
        if (properties == null) {
            properties = createProperties();
        }
        Session session = createSession(properties);
        createAndSendMessage(session, reciever, content, title);
    }


}
