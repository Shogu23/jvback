package survey.backend.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class PoeDto {

    private Integer id;
    private String title;
    private LocalDate beginDate;
    private LocalDate endDate;
}
