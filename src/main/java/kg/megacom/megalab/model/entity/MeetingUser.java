package kg.megacom.megalab.model.entity;

import kg.megacom.megalab.model.enums.MemberType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_meeting_user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MeetingUser {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "meeting_user_sequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )    @Column(name = "id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "meeting_id", nullable = false)
    Meeting meeting;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_type", nullable = false)
    MemberType memberType;

    @ManyToOne
    @JoinColumn(name = "delegate_user_id")
    User delegate; //todo ???

    @ManyToOne
    @JoinColumn(name = "label_id")
    Label label;

//    @Column(name = "is_declined"/*, nullable = false*/) // todo ???
//    Boolean isDeclined;
//
//    @Column(name = "reason_for_declining"/*, nullable = false*/) //todo ???
//    String reasonForDeclining;

}
