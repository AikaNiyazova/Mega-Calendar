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
public class AddPhotoRequest {

    @NotNull(message = "User id cannot be null")
    Long userId;

    @NotBlank(message = "Photo path cannot be blank")
    String photoPath;

}
