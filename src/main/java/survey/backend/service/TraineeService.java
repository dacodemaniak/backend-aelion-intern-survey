package survey.backend.service;

import survey.backend.dto.TraineeDto;


import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TraineeService {

    /**
     * find all trainees
     * @return all trainees
     */
    Iterable<TraineeDto> findAll();

    /**
     * find trainee with its id
     * @param id
     * @return optional with trainee if found else optional empty
     */
    Optional<TraineeDto> findById(long id);

    /**
     * search trainees with criteria lastname, firstname ;
     * one criteria can be null, not both
     * @param lastname
     * @param firstname
     * @return trainee set with this lastname (if not null) and this firstname (if not null) ;
     * empty set if no trainee found with these criteria or both criteria are null
     */
    Iterable<TraineeDto> search(String lastname, String firstname);

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
    boolean remove(long id);
}
