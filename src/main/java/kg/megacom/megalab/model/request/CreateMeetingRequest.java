package kg.megacom.megalab.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateMeetingRequest {

    @NotNull
    @Positive
    Long meetingAuthorId;

    @NotBlank(message = "Meeting topic cannot be blank")
    String meetingTopic;

    @NotNull
    List<LocalDate> meetingDates;

//    LocalDate meetingDate;

    LocalTime meetingStartTime;

    LocalTime meetingEndTime;

    @NotNull
    @Positive
    Long roomId;

    @Positive
    List<Long> participants;

//    String address; //todo ???

    @Positive
    Long labelId;

    Boolean isVisible;

//    Boolean isRepeatable;

//    List<DayOfWeek> daysOfWeek;

//    @Positive
//    Integer numberOfWeeks;

}
