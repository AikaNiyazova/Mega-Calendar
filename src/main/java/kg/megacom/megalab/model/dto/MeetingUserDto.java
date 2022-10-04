package kg.megacom.megalab.model.dto;

import kg.megacom.megalab.model.enums.Status;
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
    UserDto user;
    Status status;
    UserDto delegate;
    LabelDto label;
    String reasonForRejection;

}
