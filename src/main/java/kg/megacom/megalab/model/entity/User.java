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
@Table(name = "tb_user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    Long id;

    @Column(name = "photo_path", unique = true)
    String photoPath;

    @Column(name = "first_name", nullable = false)
    String firstName;

    @Column(name = "last_name", nullable = false)
    String lastName;

    @Column(name = "patronymic")
    String patronymic;

    @Column(name = "msisdn", nullable = false, unique = true)
    String msisdn;

    @Column(name = "email", nullable = false, unique = true)
    String email;

    @Column(name = "password", nullable = false)
    String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    Role role;

    @Column(name = "status")
    String status;

    @Column(name = "is_deleted", nullable = false)
    Boolean isDeleted;

}