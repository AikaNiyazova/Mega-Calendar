package kg.megacom.megalab.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationRequest {

    Long userId;

    @Min(value = 8, message = "Password should not be less than 8 characters")
    @NotBlank
    String password;

    String confirmedPassword;

    String photoPath;

    String status;

}
