package survey.backend.entities;

import lombok.Getter;
import lombok.Setter;
import survey.backend.enums.AnswerType;
import survey.backend.enums.Level;
import survey.backend.enums.PoeType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@Entity
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Level level;

    @Enumerated(EnumType.STRING)
    @Column(length = 4)
    private PoeType poeType;

    @ManyToMany
    @JoinTable(name = "survey_contains_question",
            joinColumns = @JoinColumn(name = "survey_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private Set<Question> questions = new HashSet<>();
}
