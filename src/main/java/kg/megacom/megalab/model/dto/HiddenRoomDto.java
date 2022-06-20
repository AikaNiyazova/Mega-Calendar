package kg.megacom.megalab.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HiddenRoomDto {

    Long id;
    RoomDto room;
    LocalDate hidingStartDate;
    LocalDate hidingEndDate;
    String reasonForHiding;

}
