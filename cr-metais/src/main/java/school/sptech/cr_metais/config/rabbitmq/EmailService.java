package school.sptech.cr_metais.config.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${email.destinatario}")
    private String destinatario;

    @Value("${email.assunto}")
    private String assunto;

    public void enviar(String corpo) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(destinatario);
        message.setSubject(assunto);
        message.setText(corpo);
        mailSender.send(message);
    }
}
