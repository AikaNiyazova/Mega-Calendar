package kg.megacom.megalab.model.response;

import kg.megacom.megalab.model.entity.Room;
import kg.megacom.megalab.model.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationResponse {

    String meetingTopic;
    User meetingAuthor;
    LocalTime meetingStartTime;
    LocalTime meetingEndTime;
    Room room;



}
