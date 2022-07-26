package kg.megacom.megalab.service.impl;

import kg.megacom.megalab.service.MailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailSenderImpl implements MailSenderService {

    private final String fromEmail = "marselcharginov2002@gmail.com";

    private final JavaMailSender mailSender;

    @Autowired
    public MailSenderImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(String toEmail, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(text);
        message.setSubject(subject);
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        mailSender.send(message);
        log.info("Mail to EMAIL = '" + toEmail + "' sent successfully!");
    }
}
