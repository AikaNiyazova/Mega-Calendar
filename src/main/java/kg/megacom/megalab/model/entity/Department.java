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
@Table(name = "tb_department")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    Organization organization;

    @Column(name = "department_name", nullable = false)
    String departmentName;

    @OneToOne
    @JoinColumn(name = "head_user_id", nullable = false, unique = true)
    User head;

    @Column(name = "is_deleted", nullable = false)
    Boolean isDeleted;

}
