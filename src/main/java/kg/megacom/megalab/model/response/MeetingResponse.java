package kg.megacom.megalab.model.response;

import kg.megacom.megalab.model.entity.Room;
import kg.megacom.megalab.model.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MeetingResponse {

    Long id;
    User meetingAuthor;
    String meetingTopic;

    List<LocalDate> meetingDates;
    LocalTime meetingStartTime;
    LocalTime meetingEndTime;

    String roomName;
    String roomLocation;

//    String address;

    Boolean isVisible;

}
