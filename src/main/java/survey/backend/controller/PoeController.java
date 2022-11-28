package survey.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import survey.backend.dto.PoeDto;

import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping("api/poe")
public class PoeController {
//    : find all, find by id, add one
    @GetMapping
    public Set<PoeDto> list() {
        return Set.of(
                PoeDto.builder()
                        .id(1)
                        .title("POE DES VRAI")
                        .beginDate(LocalDate.of(2022, 10, 24))
                        .endDate(LocalDate.of(2023, 1, 27))
                        .build(),
                PoeDto.builder()
                        .id(2)
                        .title("POE DES FAUX")
                        .beginDate(LocalDate.of(2022, 10, 31))
                        .endDate(LocalDate.of(2023, 2, 3))
                        .build()

        );
    }
}
