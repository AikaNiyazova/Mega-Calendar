package kg.megacom.megalab.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LabelDto {

    Long id;
    UserDto userDto;
    String labelName;
    String labelColor;

}
