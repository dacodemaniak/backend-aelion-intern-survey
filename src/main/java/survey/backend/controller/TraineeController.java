package survey.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.TraineeDto;

import survey.backend.error.BadRequestError;
import survey.backend.error.NoDataFoundError;
import survey.backend.service.TraineeService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("api/trainee")
public class TraineeController {

    @Autowired // DI (dependency Injection)
    private TraineeService traineeService;

    /**
     * list of trainees
     * route: /api/trainee
     * @return list of trainees
     */
    @GetMapping
    public Collection<TraineeDto> getAll(){
        return traineeService.findAll();
    }

    /**
     * a trainee by its id
     * route: /api/trainee/{id}
     * @param id
     * @return a trainee
     */
    @GetMapping("{id}")
    public TraineeDto getById(@PathVariable("id") long id){
        Optional<TraineeDto> optTrainee = traineeService.findById(id);
        if (optTrainee.isPresent()){
            return optTrainee.get();
        } else {
            throw NoDataFoundError.withId("Trainee", id);
        }
        // NB: can be refactored with Optional.orElseThrow
    }

    /**
     * search trainees with criteria
     * route: /api/trainee/search?fn=some_firstname&ln=some_lastname
     * @param firstname (optional)
     * @param lastname (optional)
     * @return trainees corresponding
     */
    @GetMapping("search")
    public Collection<TraineeDto> search(
            @RequestParam(name="ln", required = false) String lastname,
            @RequestParam(name="fn", required = false) String firstname
    ){
        if (lastname == null && firstname == null) {
            throw new BadRequestError("search with no args not permitted"); // 400 Bad Request
        }

        var traineesFound = traineeService.search(lastname, firstname); // Service results

        if (traineesFound.size() == 0) {
            throw NoDataFoundError.noResults("Trainee search",
                    String.format("lastname=%s, firstname=%s", lastname, firstname));
        }

        return traineesFound;
    }

    /**
     * add new trainee with data in json body
     * route: POST /api/trainee
     * @param traineeDto
     * @return trainee added/completed
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TraineeDto add(@Valid @RequestBody TraineeDto traineeDto){
        return  traineeService.add(traineeDto);
    }

    /**
     * update trainee with data in json body
     * route: PUT /api/trainee
     * @param traineeDto
     * @return
     */
    @PutMapping
    public TraineeDto update(@Valid @RequestBody TraineeDto traineeDto) {
        return traineeService.update(traineeDto)
                .orElseThrow(() -> NoDataFoundError.withId("Trainee", traineeDto.getId()));
    }

    /**
     * delete trainee with its id
     * route: DELETE /api/trainee/{id}
     * @param id
     */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable("id") long id) {
        if (!traineeService.remove(id)) {
            throw NoDataFoundError.withId("Trainee", id);
        }
    }

}
