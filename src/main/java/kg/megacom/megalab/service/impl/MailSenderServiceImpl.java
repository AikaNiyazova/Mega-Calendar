package kg.megacom.megalab.service.impl;

import kg.megacom.megalab.model.entity.MeetingUser;
import kg.megacom.megalab.model.response.MessageResponse;
import kg.megacom.megalab.service.MailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

@Slf4j
@Service
public class MailSenderServiceImpl implements MailSenderService {

//    private final String fromEmail = "marselcharginov2002@gmail.com";

    private final JavaMailSender mailSender;

    @Autowired
    public MailSenderServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

//    @Override
//    public void sendEmail(String toEmail, String subject, String text) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setText(text);
//        message.setSubject(subject);
//        message.setFrom(fromEmail);
//        message.setTo(toEmail);
//        mailSender.send(message);
//        log.info("Mail to EMAIL = '" + toEmail + "' sent successfully!");
//    }

    @Override
    public MessageResponse sendInvitation(MeetingUser meetingUser) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(meetingUser.getMeeting().getMeetingAuthor().getEmail());
        helper.setTo(meetingUser.getUser().getEmail());
        helper.setSubject(meetingUser.getMeeting().getMeetingTopic());
        helper.setText("Вы приглашены на " + meetingUser.getMeeting().getMeetingTopic()
                + meetingUser.getMeeting().getMeetingDate()
                + "c " + meetingUser.getMeeting().getMeetingStartTime()
                + "по" + meetingUser.getMeeting().getMeetingEndTime()
                + "в " + meetingUser.getMeeting().getRoom() + ".");
        helper.setSentDate(Date.valueOf(LocalDate.now()));

        mailSender.send(message);
        return MessageResponse.of("Invitation is sent");
    }

}
