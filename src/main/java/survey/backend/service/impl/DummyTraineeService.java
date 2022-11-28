package survey.backend.service.impl;

import org.springframework.stereotype.Service;
import survey.backend.dto.TraineeDto;
import survey.backend.service.TraineeService;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.random.RandomGenerator;

@Service
public class DummyTraineeService implements TraineeService {

    private static final RandomGenerator RANDOM_ID_GENERATOR = RandomGenerator.getDefault();

    @Override
    public Set<TraineeDto> findAll() {
        return Set.of(
                TraineeDto.builder()
                        .id(1)
                        .lastName("Doe")
                        .firstName("John")
                        .birthDate(LocalDate.of(1900, 1, 12))
                        .build(),
                TraineeDto.builder()
                        .id(12)
                        .lastName("Doe")
                        .firstName("Jane")
                        .birthDate(LocalDate.of(1920, 7, 6))
                        .build(),
                TraineeDto.builder()
                        .id(57)
                        .lastName("Ligma")
                        .firstName("Balls")
                        .birthDate(LocalDate.of(1952, 2, 29))
                        .build(),
                TraineeDto.builder()
                        .id(78)
                        .lastName("Sukon")
                        .firstName("Diznuts")
                        .birthDate(LocalDate.of(1942, 1, 29))
                        .build()
        );
    }

    @Override
    public Optional<TraineeDto> findById(int id) {
        if (id % 2 == 0) {
            return Optional.empty();
        } else {
            return Optional.of(TraineeDto.builder()
                    .id(id)
                    .lastName("Bond")
                    .firstName("James")
                    .birthDate(LocalDate.of(1900, 10, 7))
                    .build());
        }
    }

    @Override
    public Set<TraineeDto> search(String lastName, String firstName) {
        final String DEFAULT_FAMILY = "found";
        String dummyLastName = Objects.isNull(lastName) ? DEFAULT_FAMILY : lastName;
        if (Objects.isNull(lastName) && Objects.isNull(firstName)){
            return Set.of();
        }
        return Set.of(
                TraineeDto.builder()
                        .id(1)
                        .lastName(dummyLastName)
                        .firstName(Objects.isNull(firstName) ? "John" : firstName)
                        .build(),
                TraineeDto.builder()
                        .id(12)
                        .lastName(dummyLastName)
                        .firstName(Objects.isNull(firstName) ? "Jane" : firstName)
                        .build(),
                TraineeDto.builder()
                        .id(57)
                        .lastName(dummyLastName)
                        .firstName(Objects.isNull(firstName) ? "Jimmy" : firstName)
                        .build()
        );
    }

    @Override
    public TraineeDto add(TraineeDto traineeDto) {
        traineeDto.setId(RANDOM_ID_GENERATOR.nextInt());
        return traineeDto;
    }

    @Override
    public Optional<TraineeDto> update(TraineeDto traineeDto) {
        if (traineeDto.getId() % 2 == 0) {
            return Optional.empty();
        } else {
            return Optional.of(traineeDto);
        }
    }

    @Override
    public boolean delete(int id) {
        return  id % 2 == 1;
    }
}
