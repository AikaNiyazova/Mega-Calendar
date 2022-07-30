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
public class CreateRoomRequest {

    @NotBlank(message = "Name cannot be blank!")
    String roomName;
    @NotBlank(message = "Location cannot be blank!")
    String location;
    @NotNull
    @Positive
    Integer roomCapacity;
    Boolean isDashboardAvailable;
    Boolean isProjectorAvailable;
    Boolean isAcAvailable;

}
