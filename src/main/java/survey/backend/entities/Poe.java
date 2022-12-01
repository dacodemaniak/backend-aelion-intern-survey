package survey.backend.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import survey.backend.tools.PoeType;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
public class Poe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(length = 150, nullable = false)
    private String title;

    @Column(name="begin_date", nullable = false)
    private Date beginDate;

    @Column(name="end_date", nullable = false)
    private Date endDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PoeType type;

    @OneToMany
    @JoinColumn(name="poe_id")
    private Set<Trainee> trainees;

}
