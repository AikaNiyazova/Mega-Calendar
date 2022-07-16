package kg.megacom.megalab.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_organization")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    Long id;

    @Column(name = "organization_name", nullable = false, unique = true)
    String organizationName;

    @OneToOne
    @JoinColumn(name = "admin_user_id")
    User admin;

    @Column(name = "is_deleted", nullable = false)
    Boolean isDeleted;

}