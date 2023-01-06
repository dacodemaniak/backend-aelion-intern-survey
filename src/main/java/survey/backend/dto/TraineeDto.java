package survey.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.*;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Past
    private Date birthDate;


}
