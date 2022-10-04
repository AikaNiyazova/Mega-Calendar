package kg.megacom.megalab.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateMeetingRequest {

    @NotNull
    @Positive
    Long meetingDateTimeId;

    @NotNull
    @Positive
    Long meetingId;

    @NotNull
    List<LocalDate> meetingDates;

    LocalTime meetingStartTime;

    LocalTime meetingEndTime;

    @NotNull
    @Positive
    Long roomId;

//    String address; //todo ???

    Boolean isVisible;

    Boolean isRepeatable;

    Long labelId;

//    List<DayOfWeek> daysOfWeek;

//    @Positive
//    Integer numberOfWeeks;

}
