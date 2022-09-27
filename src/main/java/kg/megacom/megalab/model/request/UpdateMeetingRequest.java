package kg.megacom.megalab.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateMeetingRequest {

    @NotNull
    @Positive
    Long meetingId;

    @NotNull
    LocalDate meetingDate;

    LocalTime meetingStartTime;

    LocalTime meetingEndTime;

    @NotNull
    @Positive
    Long roomId;

//    String address; //todo ???

    Boolean isVisible;

//    Boolean isRepeatable;

//    List<DayOfWeek> daysOfWeek;

//    @Positive
//    Integer numberOfWeeks;

}
