package kg.megacom.megalab.model.dto;

import kg.megacom.megalab.model.enums.Authority;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleDto {

    Long id;
    String roleName;
    List<Authority> authorities;

}
