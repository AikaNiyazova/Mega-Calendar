package kg.megacom.megalab.service.impl;

import kg.megacom.megalab.service.MailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MailSenderServiceImpl implements MailSenderService {

    private final String fromEmail = "marselcharginov2002@gmail.com";

    private final JavaMailSender mailSender;

    @Autowired
    public MailSenderServiceImpl(JavaMailSender mailSender) {
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
