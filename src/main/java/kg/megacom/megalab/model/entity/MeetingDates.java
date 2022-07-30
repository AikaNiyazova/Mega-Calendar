package kg.megacom.megalab.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_meeting_dates")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MeetingDates {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "meeting_dates_sequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "meeting_id", nullable = false)
    Meeting meeting;

    @Column(name = "meeting_date", nullable = false)
    LocalDate meetingDate;

//    @ElementCollection
//    @CollectionTable(name = "frequency_has_weekdays", joinColumns = @JoinColumn(name = "meeting_frequency_id"))
//    @Column(name = "day_of_week_id", nullable = false)
//    List<DayOfWeek> daysOfWeek;
//
//    @Column(name = "number_of_weeks", nullable = false)
//    Integer numberOfWeeks;

}
