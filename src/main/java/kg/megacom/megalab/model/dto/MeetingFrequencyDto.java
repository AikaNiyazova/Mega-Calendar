package kg.megacom.megalab.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.DayOfWeek;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MeetingFrequencyDto {

    Long id;
    Boolean isRepeatable;
    List<DayOfWeek> daysOfWeek;
    Integer numberOfWeeks;

}
