package kg.megacom.megalab.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_meeting_frequency")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MeetingFrequency {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    Long id;

    @ElementCollection
    @CollectionTable(name = "frequency_has_weekdays", joinColumns = @JoinColumn(name = "meeting_frequency_id"))
    @Column(name = "day_of_week_id", nullable = false)
    List<DayOfWeek> daysOfWeek;

    @Column(name = "number_of_weeks", nullable = false)
    Integer numberOfWeeks;

}
