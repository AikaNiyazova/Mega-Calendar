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
@Table(name = "tb_department_user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DepartmentUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    Department department;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

}
