package ar.edu.unlam.tallerweb1.servicios;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service("servicioEmail")
public class ServicioMailImpl implements ServicioMail {
    @Override
    public void enviarMsj(String destinatario, String asunto, String msj) throws AddressException, MessagingException {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("proyectoderivaciones@gmail.com");
        mailSender.setPassword("derivaciones123");
        Properties prop = new Properties();
        prop.setProperty("mail.smtp.auth", "true");
        prop.setProperty("mail.smtp.starttls.enable", "true");
        prop.setProperty("mail.transport.protocol", "smtp");
        mailSender.setJavaMailProperties(prop);
        System.out.println("MAIL: " + mailSender.getJavaMailProperties());
        System.out.println("MAIL: " + mailSender.getPassword() + mailSender.getUsername() + mailSender.getHost());
        MimeMessage mensaje = mailSender.createMimeMessage();
        mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
        mensaje.setSubject(asunto);
        mensaje.setContent(msj,"text/html; charset=utf-8");
        mailSender.send(mensaje);
    }
}
