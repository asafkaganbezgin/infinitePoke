import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Mail {

    private String username;
    private String password;
    private String[] toEmail;
    private String subject;
    private String message;
    private int pokeCount;

    // Default Constuctor
    public Mail()
    {
    }

    // Constructor
    public Mail(String username, String password, String[] toEmail, String subject, String message, int pokeCount)
    {
        this.username = username;
        this.password = password;
        this.toEmail = toEmail;
        this.subject = subject;
        this.message = message;
        this.pokeCount = pokeCount;
    }

    public void sendEmail(Mail mail)
    {
        int sendVirus = mail.pokeCount;
        int recepientCount = 0;

        for (int i = 0; i < mail.toEmail.length; i++)
        {
            if (toEmail[i] != null)
                recepientCount++;
        }

        String userName = mail.username;
        String password = mail.password;
        String fromEmail = "seneticc@yahoo.com";
        String[] toEmail = mail.toEmail;

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.mail.yahoo.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(userName, password);
            }
        });

        while (sendVirus != 0)
        {
            MimeMessage msg = new MimeMessage(session);
            try
            {
                msg.setFrom(new InternetAddress(fromEmail));
                for (int i = 0; i < recepientCount; i++)
                {
                    msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail[i]));
                }
                msg.setSubject(mail.subject);
                msg.setText(mail.message);
                Transport.send(msg);
                System.out.println("Successfully sent...");
            }
            catch (MessagingException e)
            {
                e.printStackTrace();
            }

            sendVirus--;
        }
    }

    public static void main(String[] args)
    {
        String username, password, subject, message;
        username = "seneticc@yahoo.com";
        password = "lkucqscjyssqpmnp";
        subject = "subject";
        message = "message";
        String[] recipient = { "asafkagan@hotmail.com" };
        int pokeCount = 1;

        Mail mail = new Mail(username, password, recipient, subject, message, pokeCount);
        mail.sendEmail(mail);
    }
}