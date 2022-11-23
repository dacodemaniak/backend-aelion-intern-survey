package survey.backend.dto;

import lombok.*;

import java.time.LocalDate;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class TraineeDto {

    private Integer id;
    private String lastname;
    private String firstname;
    private String email;
    private String phoneNumber;
    private LocalDate birthdate;

}
