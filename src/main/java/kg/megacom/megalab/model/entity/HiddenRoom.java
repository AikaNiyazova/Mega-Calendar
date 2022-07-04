package kg.megacom.megalab.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_hidden_room")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HiddenRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    Room room;

    @Column(name = "hiding_start_date", nullable = false)
    LocalDate hidingStartDate;

    @Column(name = "hiding_end_date", nullable = false)
    LocalDate hidingEndDate;

    @Column(name = "reason_for_hiding", nullable = false)
    String reasonForHiding;

}
