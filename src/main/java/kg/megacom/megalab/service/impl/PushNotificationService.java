package kg.megacom.megalab.service.impl;

import com.google.firebase.messaging.*;
import kg.megacom.megalab.model.entity.MeetingUser;
import org.springframework.stereotype.Service;

@Service
public class PushNotificationService {
    private final FirebaseMessaging firebaseMessaging;

    public PushNotificationService(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }

    public String sendPushNotification(MeetingUser meetingUser) throws FirebaseMessagingException {

        Notification notification = Notification
                .builder()
                .setTitle(meetingUser.getMeeting().getMeetingTopic())
                .setBody("Вы приглашены на " + meetingUser.getMeeting().getMeetingTopic()
                        + meetingUser.getMeeting().getMeetingDate()
                        + "c " + meetingUser.getMeeting().getMeetingStartTime()
                        + "по" + meetingUser.getMeeting().getMeetingEndTime()
                        + "в " + meetingUser.getMeeting().getRoom() + ".")
                .build();

        Message message = Message
                .builder()
                .setTopic(meetingUser.getMeeting().getMeetingTopic())
                .setNotification(notification)
//                .putAllData(note.getData())
                .setAndroidConfig(AndroidConfig
                        .builder()
                        .setPriority(AndroidConfig.Priority.HIGH)
                        .build())
                .setApnsConfig(ApnsConfig
                        .builder()
                        .build())
                .setWebpushConfig(WebpushConfig
                        .builder()
                        .build())
                .build();

        return firebaseMessaging.send(message);
    }
}
