package kg.megacom.megalab.service;

import kg.megacom.megalab.model.entity.MeetingUser;
import kg.megacom.megalab.model.response.MessageResponse;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public interface MailSenderService {

//    void sendEmail(String toEmail,String subject,String body);

    MessageResponse sendInvitation(MeetingUser meetingUser) throws MessagingException;

}
