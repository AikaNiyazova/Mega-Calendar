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
@Table(name = "tb_position")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    Department department;

    @Column(name = "position_name", nullable = false)
    String positionName;

    @Column(name = "is_deleted", nullable = false)
    Boolean isDeleted;

}
