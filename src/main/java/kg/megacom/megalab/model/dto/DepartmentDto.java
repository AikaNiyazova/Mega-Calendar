package kg.megacom.megalab.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DepartmentDto {

    Long id;
    OrganizationDto organization;
    String departmentName;
    UserDto head;
    Boolean isDeleted;

}
