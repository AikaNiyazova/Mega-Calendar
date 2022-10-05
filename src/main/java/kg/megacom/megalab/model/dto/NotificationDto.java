package kg.megacom.megalab.model.dto;

import kg.megacom.megalab.model.entity.Meeting;
import kg.megacom.megalab.model.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationDto {

    Long id;
    User sendFrom;
    User sendTo;
    Meeting message;
    LocalDateTime sendAt;
    Boolean isRead;
}
