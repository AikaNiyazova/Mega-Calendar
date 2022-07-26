package kg.megacom.megalab.model.request;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateRoomRequest {

    @NotBlank(message = "Name not be blank!")
    String roomName;
    @NotBlank(message = "Location not be blank!")
    String location;
    Boolean isDashboardAvailable;
    Boolean isProjectorAvailable;
    Boolean isAcAvailable;

}
