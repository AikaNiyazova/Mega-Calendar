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

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
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

}
