package kg.megacom.megalab.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_room")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Room {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "room_sequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    Long id;

    @Column(name = "room_name", nullable = false, unique = true)
    String roomName;

    @Column(name = "room_capacity", nullable = false)
    Integer roomCapacity;

    @Column(name = "location", nullable = false)
    String location;

    @Column(name = "is_dashboard_available", nullable = false)
    Boolean isDashboardAvailable;

    @Column(name = "is_projector_available", nullable = false)
    Boolean isProjectorAvailable;

    @Column(name = "is_ac_available", nullable = false)
    Boolean isAcAvailable;

}
