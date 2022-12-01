package survey.backend.dto;

import lombok.*;
import survey.backend.entities.Trainee;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class TraineeDto {

    private Long id;

    @NotBlank // include @NonNull
    private String lastName;

    @NotBlank
    private String firstName;

    @NotNull
    @Email
    private String email;

    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$")
    private String phoneNumber;

    @Past
    private Date birthDate;

    public Trainee toTrainee() {
        Trainee trainee = new Trainee();

        trainee.setId(this.id);
        trainee.setLastName(this.lastName);
        trainee.setFirstName(this.firstName);
        trainee.setPhoneNumber(this.phoneNumber);
        trainee.setEmail(this.email);
        trainee.setBirthDate(this.birthDate);

        return trainee;
    }
}
