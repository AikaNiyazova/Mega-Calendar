package kg.megacom.megalab.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateHiddenRoomRequest {

    @NotNull
    Long id;

    @NotNull
    LocalDate hidingStartDate;

    @NotNull
    LocalDate hidingEndDate;

    @NotBlank
    String reasonForHiding;

}
