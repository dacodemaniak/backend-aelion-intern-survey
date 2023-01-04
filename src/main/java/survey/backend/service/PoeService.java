package survey.backend.service;

import survey.backend.dto.PoeDto;
import survey.backend.dto.PoeFullDto;

import java.util.Collection;
import java.util.Optional;

public interface PoeService {

    /**
     * find all poe
     * @return collection of poe found
     */
    Collection<PoeDto> findAll();

    /**
     * find poe by its id
     * @param id id of poe to find
     * @return optional with poe found or optional empty if not found
     */
    Optional<PoeFullDto> findById(long id);

    /**
     * add new poe
     * @param poeDto poe to add
     * @return poe added with its id
     */
    PoeDto add(PoeDto poeDto);

    /**
     * update poe (direct fields only)
     * @param poeDto poe to update with its id
     * @return optional with poe updated or optional empty if poe not found
     */
    Optional<PoeFullDto> update(PoeDto poeDto);

    /**
     * add trainee in a poe
     * @param poeId id of poe to modify
     * @param traineeId id of trainee to add in the poe
     * @return optional with poe updated with this new trainee
     * or optional empty if poe or trainee not found
     */
    Optional<PoeFullDto> addTrainee(long poeId, long traineeId);

    /**
     * add trainees in a poe
     * @param poeId id of poe to modify
     * @param traineeIds ids of trainees to add in the poe
     * @return optional with poe updated with this new trainees
     * or optional empty if poe or trainees not found
     */
    Optional<PoeFullDto> addTrainees(long poeId, Collection<Long> traineeIds);

    /**
     * remove trainee from a poe
     * @param poeId id of poe to modify
     * @param traineeId id of trainee to remove from the poe
     * @return optional with poe updated without the trainee
     * or optional empty if poe or trainee not found
     */
    Optional<PoeFullDto> removeTrainee(long poeId, long traineeId);

    /**
     * remove trainees from a poe
     * @param poeId id of poe to modify
     * @return optional with poe updated with no trainees
     * or optional empty if poe not found
     */
    Optional<PoeFullDto> clearTrainees(long poeId);

    /**
     * remove a poe
     * @param poeId id of poe to remove
     * @return true if found and removed, false if not found
     */
    boolean remove(long poeId);

}
