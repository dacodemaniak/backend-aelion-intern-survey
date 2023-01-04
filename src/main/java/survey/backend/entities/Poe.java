package survey.backend.entities;

import lombok.*;
import survey.backend.enums.PoeType;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString(exclude = "trainees")
@Entity
public class Poe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(length = 150, nullable = false)
    private String title;

    @Column(name="begin_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date beginDate;

    @Column(name="end_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "poe_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PoeType type;

    @Builder.Default
    @OneToMany // fetch lazy by default
    @JoinColumn(name="poe_id")
    private Set<Trainee> trainees = new HashSet<>();

}
