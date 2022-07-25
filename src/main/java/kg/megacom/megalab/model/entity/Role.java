package kg.megacom.megalab.model.entity;

import kg.megacom.megalab.model.enums.Authority;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_role")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    Long id;

    @Column(name = "role_name", nullable = false, unique = true)
    String roleName;

    @ElementCollection
    @CollectionTable(name = "role_has_authorities", joinColumns = @JoinColumn(name = "role_id"))
    @Column(name = "authority_id", nullable = false)
    List<Authority> authorities;

    @Column(name = "is_deleted", columnDefinition = "boolean default false")
    Boolean isDeleted;

}