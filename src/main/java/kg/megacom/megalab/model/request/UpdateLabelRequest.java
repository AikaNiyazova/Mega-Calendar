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
public class UpdateLabelRequest {

    @NotNull(message = "Id cannot be null")
    Long id;

    @NotNull(message = "User id cannot be null")
    Long userId; // todo: remove?

    @NotBlank(message = "Label name cannot be blank")
    String labelName;

    @NotBlank(message = "Label color cannot be blank")
    String labelColor;

}
