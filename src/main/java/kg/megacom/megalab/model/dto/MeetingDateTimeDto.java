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
public class MeetingDateTimeDto {

    Long id;
    MeetingDto meeting;
    LocalDate meetingDate;
    LocalTime meetingStartTime;
    LocalTime meetingEndTime;
    RoomDto room;
    Boolean isDeleted;

//    List<DayOfWeek> daysOfWeek;
//    Integer numberOfWeeks;

}
