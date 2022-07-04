package kg.megacom.megalab.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    Long id;
    String photoPath;
    String firstName;
    String lastName;
    String patronymic;
    String msisdn;
    String email;
    String password;
    RoleDto role;
    String status;
    Boolean isDeleted;

}
