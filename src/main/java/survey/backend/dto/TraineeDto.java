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
    private String lastName;

    @NotBlank
    private String firstName;

    @NotNull
    @Email
    private String email;

    @Pattern(regexp = "^[\\+]?[(]?\\d{3}[)]?[-\\s\\.]?\\d{3}[-\\s\\.]?\\d{4,6}$")
    private String phoneNumber;

    @Past
    private LocalDate birthDate;

}
