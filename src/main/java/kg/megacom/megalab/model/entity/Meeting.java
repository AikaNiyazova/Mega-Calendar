package kg.megacom.megalab.model.entity;

import lombok.*;

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
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "meeting_author", nullable = false)
    User meetingAuthor;

    @Column(name = "meeting_topic", nullable = false)
    String meetingTopic;

    @Column(name = "meeting_date", nullable = false)
    LocalDate meetingDate;

    @Column(name = "meeting_start_time", nullable = false)
    LocalTime meetingStartTime;

    @Column(name = "meeting_end_time", nullable = false)
    LocalTime meetingEndTime;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    Room room;

    @Column(name = "address")
    String address;

    @Column(name = "is_visible", nullable = false)
    Boolean isVisible;

    @OneToOne
    @JoinColumn(name = "frequency_id", nullable = false, unique = true)
    MeetingFrequency frequency;

    @Column(name = "is_deleted", nullable = false)
    Boolean isDeleted;

}
