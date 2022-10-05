package kg.megacom.megalab.model.entity;

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
    @JoinColumn(name = "send_from", nullable = false)
    User sendFrom;

    @OneToOne
    @JoinColumn(name = "send_to", nullable = false)
    User sendTo;

    @OneToOne
    @JoinColumn(name = "message", nullable = false)
    Meeting message;

    @Column(name = "send_at", nullable = false)
    LocalDateTime sendAt;

    @Column(name = "is_read", nullable = false)
    Boolean isRead;
}
