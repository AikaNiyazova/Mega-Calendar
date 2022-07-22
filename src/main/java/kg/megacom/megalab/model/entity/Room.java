package kg.megacom.megalab.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    Long id;

    @Column(name = "room_name", nullable = false, unique = true)
    String roomName;

    @Column(name = "location", nullable = false)
    String location;

    @Column(name = "is_dashboard_available", nullable = false)
    Boolean isDashboardAvailable;

    @Column(name = "is_projector_available", nullable = false)
    Boolean isProjectorAvailable;

    @Column(name = "is_ac_available", nullable = false)
    Boolean isAcAvailable;

    @Column(name = "is_deleted", nullable = false)
    Boolean isDeleted;



}
