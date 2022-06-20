package kg.megacom.megalab.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MeetingDto {

    Long id;
    UserDto meetingAuthor;
    String meetingTopic;
    LocalDate meetingDate;
    LocalTime meetingStartTime;
    LocalTime meetingEndTime;
    RoomDto room;
    String address;
    Boolean isVisible;
    MeetingFrequencyDto frequency;
    Boolean isDeleted;

}
