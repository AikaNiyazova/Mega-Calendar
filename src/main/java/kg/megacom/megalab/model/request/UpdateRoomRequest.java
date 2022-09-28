package kg.megacom.megalab.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateRoomRequest {

    @NotNull
    @Positive
    Long roomId;

    @NotBlank(message = "Name cannot be blank!")
    String roomName;

    @NotNull
    @Positive
    Integer roomCapacity;

    Boolean isDashboardAvailable;

    Boolean isProjectorAvailable;

    Boolean isAcAvailable;
}
