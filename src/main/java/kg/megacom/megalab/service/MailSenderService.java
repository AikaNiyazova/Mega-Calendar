package kg.megacom.megalab.service;

public interface MailSenderService {

    void sendEmail(String toEmail,String subject,String body);

}
