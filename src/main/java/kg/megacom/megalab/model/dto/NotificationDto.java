package kg.megacom.megalab.model.dto;

import kg.megacom.megalab.model.entity.MeetingDateTime;
import kg.megacom.megalab.model.entity.User;
import kg.megacom.megalab.model.enums.Status;
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
    MeetingDateTime meetingDateTime;
    Status status;
    LocalDateTime sendAt;
    Boolean isRead;
}
