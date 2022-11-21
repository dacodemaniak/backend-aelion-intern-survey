package survey.backend.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.TraineeDto;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/trainee")
public class TraineeController {

    /**
     * list of trainees
     * route: /api/trainee
     * @return list of trainees
     */
    @GetMapping
    public Set<TraineeDto> list(){
        return Set.of(
                TraineeDto.builder()
                        .id(1)
                        .lastName("Doe")
                        .firstName("John")
                        .build(),
                TraineeDto.builder()
                        .id(12)
                        .lastName("Doe")
                        .firstName("Jane")
                        .build(),
                TraineeDto.builder()
                        .id(57)
                        .lastName("Unknown")
                        .firstName("John")
                        .build()
        );
    }

    /**
     * a trainee by its id
     * route: /api/trainee/{id}
     * @param id
     * @return a trainee
     */
    @GetMapping("{id}")
    public Optional<TraineeDto> one(@PathVariable("id") int id){
//        return Optional.empty();
        return Optional.of(TraineeDto.builder()
                .id(id)
                .lastName("Doe")
                .firstName("John")
                .birthDate(LocalDate.of(1900, 7, 1))
                .build());
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
                        .lastName(Objects.isNull(lastname) ? "Found" : lastname)
                        .firstName(Objects.isNull(firstname) ? "John" : firstname)
                        .build(),
                TraineeDto.builder()
                        .id(12)
                        .lastName("Found")
                        .firstName("Jane")
                        .build(),
                TraineeDto.builder()
                        .id(57)
                        .lastName("Found")
                        .firstName("Jim")
                        .build()
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TraineeDto add(@RequestBody TraineeDto traineeDto){
        // TODO: add in under layer
        traineeDto.setId(54321);
        return traineeDto;
    }




}
