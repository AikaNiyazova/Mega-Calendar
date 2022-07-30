package kg.megacom.megalab.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateUserPersonalInfoRequest {

    @NotNull
    @Positive
    Long userId;

    @NotBlank
    String firstName;

    @NotBlank
    String lastName;

    @NotNull
    String msisdn;

    @Email
    @NotBlank
    String email;

}
