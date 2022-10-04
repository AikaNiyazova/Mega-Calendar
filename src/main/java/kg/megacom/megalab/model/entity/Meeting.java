package kg.megacom.megalab.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_meeting")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Meeting {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "meeting_sequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "meeting_author", nullable = false)
    User meetingAuthor;

    @Column(name = "meeting_topic", nullable = false)
    String meetingTopic;

//    @Column(name = "meeting_date", nullable = false)
//    LocalDate meetingDate;
//
//    @Column(name = "meeting_start_time", nullable = false)
//    LocalTime meetingStartTime;
//
//    @Column(name = "meeting_end_time", nullable = false)
//    LocalTime meetingEndTime;
//
//    @ManyToOne
//    @JoinColumn(name = "room_id", nullable = false)
//    Room room;

    @Column(name = "address")
    String address; // todo: ???

    @Column(name = "is_visible", nullable = false)
    Boolean isVisible;

    @Column(name = "is_repeatable", nullable = false)
    Boolean isRepeatable;

}
