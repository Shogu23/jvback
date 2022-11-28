package survey.backend.service;

import survey.backend.dto.TraineeDto;

import java.util.Optional;
import java.util.Set;

public interface TraineeService {

    /**
     * find all trainees
     * @return all trainees
     */
    Set<TraineeDto> findAll();

    /**
     * find trainee with its id
     * @param id
     * @return optional with trainee if found else optional empty
     */
    Optional<TraineeDto> findById(int id);

    /**
     * search trainees with criteria lastName, firstName ;
     * one criteria can be null, not both
     * @param lastName
     * @param firstName
     * @return trainee set with this lastName (if not null) and this firstName (if not null) ;
     * empty set if no trainee found with these criteria or both criteria are null
     */
    Set<TraineeDto> search(String lastName, String firstName);

    /**
     * add new trainee
     * @param traineeDto
     * @return trainee completed (id, default values)
     */
    TraineeDto add(TraineeDto traineeDto);

    /**
     * update trainee
     * @param traineeDto
     * @return trainee updated if found, else optional empty
     */
    Optional<TraineeDto> update(TraineeDto traineeDto);

    /**
     * delete trainee with its id
     * @param id
     * @return true if found and deleted, false if not found
     */
    boolean delete(int id);
}