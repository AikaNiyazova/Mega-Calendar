package kg.megacom.megalab.model.request;

import kg.megacom.megalab.model.enums.Status;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateMeetingUserRequest {

    @NotNull
    Long id;

    @NotEmpty
    Status status;

    Long delegateId;

    Long labelId;

    String reasonForRejection;

}
