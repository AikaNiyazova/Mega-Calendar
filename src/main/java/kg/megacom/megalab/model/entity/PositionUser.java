package kg.megacom.megalab.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_position_user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PositionUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    Position position;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

}
