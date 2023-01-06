package survey.backend.entities;

import lombok.Getter;
import lombok.Setter;
import survey.backend.entities.converter.BlankStringConverter;

import javax.persistence.*;
import java.util.Date;

@Getter @Setter
@Entity
@Table(name="trainee")
public class Trainee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="last_name", nullable = false)
    private String lastName;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name="birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(unique = true, nullable = false)
    private String email;

    @Convert(converter = BlankStringConverter.class)
    @Column(name="phone_number")
    private String phoneNumber;
}
