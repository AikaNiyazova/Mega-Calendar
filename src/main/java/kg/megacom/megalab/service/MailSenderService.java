package kg.megacom.megalab.service;

import org.springframework.stereotype.Service;

@Service
public interface MailSenderService {

    void sendEmail(String toEmail,String subject,String body);

}
