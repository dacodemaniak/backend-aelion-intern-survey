package survey.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.TraineeDto;
import survey.backend.service.TraineeService;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

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
    public Set<TraineeDto> list(){
        return traineeService.findAll();
//        return Set.of(
//                TraineeDto.builder()
//                        .id(1)
//                        .lastName("Doe")
//                        .firstName("John")
//                        .birthDate(LocalDate.of(1900, 1, 12))
//                        .build(),
//                TraineeDto.builder()
//                        .id(12)
//                        .lastName("Doe")
//                        .firstName("Jane")
//                        .birthDate(LocalDate.of(1920, 7, 6))
//                        .build(),
//                TraineeDto.builder()
//                        .id(57)
//                        .lastName("Unknown")
//                        .firstName("John")
//                        .birthDate(LocalDate.of(1952, 2, 29))
//                        .build()
//        );
    }

    /**
     * a trainee by its id
     * route: /api/trainee/{id}
     * @param id
     * @return a trainee
     */
    @GetMapping("{id}")
    public TraineeDto one(@PathVariable("id") int id){
        Optional<TraineeDto> optTraineeDto = traineeService.findById(id);
        if (optTraineeDto.isPresent()){
            return optTraineeDto.get();
        } else {
            throw new IllegalArgumentException(
                    "Trainee with id " + id + " not found");
        }
//        return Optional.empty();
//        return Optional.of(TraineeDto.builder()
//                .id(id)
//                .lastName("Doe")
//                .firstName("John")
//                .birthDate(LocalDate.of(1900, 7, 1))
//                .build());
    }

    /**
     * search trainees with criteria
     * route: /api/trainee/search?fn=some_firstname&ln=some_lastname
     * @param firstname (optional)
     * @param lastname (optional)
     * @return trainees corresponding
     */
    @GetMapping("search")
    public Set<TraineeDto> search(
            @RequestParam(name="fn", required = false) String firstname,
            @RequestParam(name="ln", required = false) String lastname
    ){
        return Set.of(
                TraineeDto.builder()
                        .id(1)
                        .lastname(Objects.isNull(lastname) ? "Found" : lastname)
                        .firstname(Objects.isNull(firstname) ? "John" : firstname)
                        .build(),
                TraineeDto.builder()
                        .id(12)
                        .lastname("Found")
                        .firstname("Jane")
                        .build(),
                TraineeDto.builder()
                        .id(57)
                        .lastname("Found")
                        .firstname("Jim")
                        .build()
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TraineeDto add(@RequestBody TraineeDto traineeDto){
       return traineeService.add(traineeDto);
    }

    /**
     *
     * @param id
     */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        // TODO: delete this object if exists
    }

    @PutMapping
    public TraineeDto update(@RequestBody TraineeDto traineeDto) {
        // TODO: update this object if exists and return it
        return traineeDto;
    }
}
