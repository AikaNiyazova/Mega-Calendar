package kg.megacom.megalab.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateLabelRequest {

    @NotBlank(message = "Label name cannot be blank")
    String labelName;

}
