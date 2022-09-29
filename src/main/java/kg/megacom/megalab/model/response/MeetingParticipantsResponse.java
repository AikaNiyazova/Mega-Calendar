package kg.megacom.megalab.model.response;

import kg.megacom.megalab.model.dto.UserDto;
import kg.megacom.megalab.model.enums.Status;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MeetingParticipantsResponse {

    Long meetingId;
    Map<UserDto, Status> participantDetails;

}
