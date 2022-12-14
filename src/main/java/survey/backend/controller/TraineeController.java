package survey.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.TraineeDto;
import survey.backend.entities.Trainee;
import survey.backend.error.BadRequestError;
import survey.backend.error.NoDataFoundError;
import survey.backend.service.TraineeService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("api/trainee")
public class TraineeController {

    @Autowired // DI (dependency Injection)
    private TraineeService traineeService;

    static final String ITEM_TYPE = "Trainee";

    /**
     * list of trainees
     * route: /api/trainee
     * @return list of trainees
     */
    @GetMapping
    public Iterable<Trainee> getAll(){
        return traineeService.findAll();
    }

    /**
     * a trainee by its id
     * route: /api/trainee/{id}
     * @return a trainee
     */
    @GetMapping("{id}")
    public Trainee getById(@PathVariable("id") int id){
        Optional<Trainee> optTrainee = traineeService.findById(id);
        if (optTrainee.isPresent()){
            return optTrainee.get();
        } else {
            throw NoDataFoundError.withId(ITEM_TYPE, id);
        }
    }

    /**
     * search trainees with criteria
     * route: /api/trainee/search?fn=some_firstName&ln=some_lastName
     * @param firstName (optional)
     * @param lastName (optional)
     * @return trainees corresponding
     */
    @GetMapping("search")
    public Iterable<Trainee> search(
            @RequestParam(name="ln", required = false) String lastName,
            @RequestParam(name="fn", required = false) String firstName
    ){

        // 1 ere methode
        if (lastName == null && firstName == null) {
            throw new BadRequestError("search with no args not permitted");
        }

        List<Trainee> trainees = StreamSupport.stream(
                traineeService.search(lastName, firstName).spliterator(), false
        ).toList();

        if (trainees.isEmpty()) {
            throw NoDataFoundError.noResults(ITEM_TYPE);
        }

        return traineeService.search(lastName, firstName);

    /*
        // Methode alternative
        int size = 0;

        if (lastName == null && firstName == null) {
            throw new BadRequestError("search with no args not permitted");
        }

        Iterable<Trainee> iTrainees = traineeService.search(lastName, firstName);
        if (iTrainees instanceof Collection) {
            size = ((Collection<?>) iTrainees).size();
        }


        if (size == 0) {
            throw NoDataFoundError.noResults(ITEM_TYPE);
        }

        return iTrainees;
    */
    }

    /**
     * add new trainee with data in json body
     * route: POST /api/trainee
     * @return trainee added/completed
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Trainee add(@Valid @RequestBody TraineeDto traineeDto){
        return traineeService.add(traineeDto);
    }

    /**
     * update trainee with data in json body
     * route: PUT /api/trainee
     * @return trainee or error
     */
    @PutMapping
    public Trainee update(@Valid @RequestBody TraineeDto traineeDto) {
        return traineeService.update(traineeDto)
                .orElseThrow(() -> NoDataFoundError.withId(ITEM_TYPE, Math.toIntExact(traineeDto.getId())));
    }

    /**
     * delete trainee with its id
     * route: DELETE /api/trainee/{id}
     */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        if (!traineeService.delete(id)) {
            throw NoDataFoundError.withId(ITEM_TYPE, id);
        }
    }

}