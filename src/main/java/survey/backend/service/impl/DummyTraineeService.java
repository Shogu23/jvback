package survey.backend.service.impl;

import org.springframework.stereotype.Service;
import survey.backend.dto.TraineeDto;
import survey.backend.repository.TraineeRepository;
import survey.backend.service.TraineeService;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.random.RandomGenerator;

@Service
public class DummyTraineeService implements TraineeService {

    private final TraineeRepository repository = new TraineeRepository();

    @Override
    public Set<TraineeDto> findAll() {
        return this.repository.getTrainees();
    }

    @Override
    public Optional<TraineeDto> findById(int id) {
        return this.repository.findById(id);
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
        return this.repository.add(traineeDto);
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
        Optional<TraineeDto> oTrainee = this.repository.findById(id);
/*
        if (oTrainee.isPresent()) {
            return this.repository.delete(oTrainee.get());
        }
        return false;
        EQUIVALENT DE -->
        return oTrainee.filter(traineeDto -> this.repository.delete(traineeDto)).isPresent();
        EQUIVALENT DE -->
*/
        return oTrainee.filter(this.repository::delete).isPresent();
    }
}
