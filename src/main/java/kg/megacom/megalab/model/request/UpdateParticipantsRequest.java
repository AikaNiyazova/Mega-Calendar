package kg.megacom.megalab.model.request;

import kg.megacom.megalab.model.dto.MeetingDateTimeDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateParticipantsRequest {

    @NotNull
    @Positive
    MeetingDateTimeDto meetingDateTimeDto;

    @Positive
    List<Long> participants;

}
