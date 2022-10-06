package kg.megacom.megalab.model.entity;

import kg.megacom.megalab.model.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_notification")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Notification {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "user_sequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    Long id;

    @OneToOne
    @JoinColumn(name = "send_from_id", nullable = false)
    User sendFrom;

    @OneToOne
    @JoinColumn(name = "send_to_id", nullable = false)
    User sendTo;

    @OneToOne
    @JoinColumn(name = "meeting_date_time_id", nullable = false)
    MeetingDateTime meetingDateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    Status status;

    @Column(name = "send_at", nullable = false)
    LocalDateTime sendAt;

    @Column(name = "is_read", nullable = false)
    Boolean isRead;
}
