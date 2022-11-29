package survey.backend.repository;

import survey.backend.dto.TraineeDto;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;

public class TraineeRepository {

    private Set<TraineeDto> trainees;
    private static final RandomGenerator RANDOM_ID_GENERATOR = RandomGenerator.getDefault();

    public TraineeRepository() {
        this.createFakeRepository();
    }

    public Set<TraineeDto> getTrainees() {
        return this.trainees;
    }

    public Optional<TraineeDto> findById(int id) {
        return this.trainees.stream()
                .filter(trainee -> trainee.getId() == id)
                .findFirst();
    }

    public TraineeDto add(TraineeDto trainee) {
        trainee.setId(RANDOM_ID_GENERATOR.nextInt());
        this.trainees.add(trainee);
        return trainee;
    }

    public boolean delete(TraineeDto trainee) {
        return this.trainees.remove(trainee);
    }

    private void createFakeRepository() {
        this.trainees = Set.of(
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
        ).stream().collect(Collectors.toSet());
    }
}
