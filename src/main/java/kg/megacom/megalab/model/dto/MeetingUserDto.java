package kg.megacom.megalab.model.dto;

import kg.megacom.megalab.model.enums.MemberType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MeetingUserDto {

    Long id;
    MeetingDto meeting;
    UserDto participant;
    MemberType memberType;
    UserDto delegate;
    LabelDto label;
    Boolean isDeclined;
    String reasonForDeclining;

}
