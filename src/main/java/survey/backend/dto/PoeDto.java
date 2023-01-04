package survey.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import survey.backend.enums.PoeType;

import java.util.Date;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class PoeDto {
    private Long id;
    private String title;
    private Date beginDate;
    private Date endDate;
    private PoeType poeType;
}
