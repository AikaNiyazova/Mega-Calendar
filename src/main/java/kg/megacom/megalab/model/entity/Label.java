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
@Table(name = "tb_label")
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    Long id;

    @Column(name = "label_name", nullable = false)
    String labelName;

    @Column(name = "is_deleted", nullable = false)
    Boolean isDeleted;

}
