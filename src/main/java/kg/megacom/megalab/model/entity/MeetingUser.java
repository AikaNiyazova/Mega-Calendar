package kg.megacom.megalab.model.entity;

import kg.megacom.megalab.model.enums.MemberType;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_meeting_user")
public class MeetingUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "meeting_id", nullable = false)
    Meeting meeting;

    @ManyToOne
    @JoinColumn(name = "participant_user_id", nullable = false)
    User participant;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_type", nullable = false)
    MemberType memberType;

    @ManyToOne
    @JoinColumn(name = "delegate_user_id")
    User delegate;

    @ManyToOne
    @JoinColumn(name = "label_id", nullable = false)
    Label label;

    @Column(name = "is_declined", nullable = false)
    Boolean isDeclined;

    @Column(name = "reason_for_declining", nullable = false)
    String reasonForDeclining;

}
