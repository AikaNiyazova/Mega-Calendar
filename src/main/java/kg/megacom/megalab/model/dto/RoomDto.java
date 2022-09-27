package kg.megacom.megalab.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomDto {

    Long id;
    String roomName;
    Integer roomCapacity;
    String location;
    Boolean isDashboardAvailable;
    Boolean isProjectorAvailable;
    Boolean isAcAvailable;
    Boolean isDeleted;

}
