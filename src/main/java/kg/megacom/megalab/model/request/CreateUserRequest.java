package kg.megacom.megalab.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserRequest {

    String photoPath;

    @NotBlank
    String firstName;

    @NotBlank
    String lastName;

    String patronymic;

    @NotNull
    String msisdn;

    @Email
    @NotBlank
    String email;

    @Min(value = 8, message = "Password should not be less than 8 characters")
    @NotBlank
    String password;

    @Positive
    Long organizationId;

    @Positive
    Long departmentId;

    @Positive
    Long positionId;

}
