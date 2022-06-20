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
@Table(name = "tb_organization_user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrganizationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    Organization organization;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

}
