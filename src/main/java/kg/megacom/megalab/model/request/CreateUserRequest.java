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

    @Positive
    Long organizationId;

    @Positive
    Long departmentId;

    @Positive
    Long positionId;

    @Positive
    Long roleId;

    public String getPassword() {
        return null;
    }
}
