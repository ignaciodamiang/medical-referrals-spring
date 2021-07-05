package ar.edu.unlam.tallerweb1.servicios;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface ServicioMail {
    void enviarMsj(String destinatario, String asunto, String msj) throws AddressException, MessagingException;
}
