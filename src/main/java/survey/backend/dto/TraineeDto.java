package survey.backend.dto;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class TraineeDto {

    private Integer id;

    @NotBlank // include @NonNull
    private String lastname;

    @NotBlank
    private String firstname;

    @NotNull
    @Email
    private String email;

    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$")
    private String phoneNumber;

    @Past
    private LocalDate birthdate;

}
