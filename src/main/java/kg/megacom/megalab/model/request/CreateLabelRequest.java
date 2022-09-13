package kg.megacom.megalab.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateLabelRequest {

    @NotNull(message = "User id cannot be null")
    Long userId;

    @NotBlank(message = "Label name cannot be blank")
    String labelName;

    @NotBlank(message = "Label color cannot be blank")
    String labelColor;

}
