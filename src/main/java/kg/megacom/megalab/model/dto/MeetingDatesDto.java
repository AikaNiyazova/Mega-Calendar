package kg.megacom.megalab.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MeetingDatesDto {

    Long id;
    MeetingDto meeting;
    LocalDate meetingDate;

//    List<DayOfWeek> daysOfWeek;
//    Integer numberOfWeeks;

}
