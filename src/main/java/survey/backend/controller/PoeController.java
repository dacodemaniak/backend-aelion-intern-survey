package survey.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.PoeDto;
import survey.backend.dto.PoeFullDto;
import survey.backend.error.NoDataFoundError;
import survey.backend.service.PoeService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/poe")
public class PoeController {
    @Autowired
    private PoeService poeService;
    @GetMapping
    public Collection<PoeDto> findAll() {
        return this.poeService.findAll();
    }

    @GetMapping("/{id}")
    public PoeFullDto findById(@PathVariable("id") long id){
        return poeService.findById(id)
                .orElseThrow(() -> NoDataFoundError.withId("poe", id));
    }

    @PatchMapping("/{poeId}/addTrainee/{traineeId}")
    public PoeFullDto addTrainee(
            @PathVariable("poeId") long poeId,
            @PathVariable("traineeId") long traineeId)
    {
        return poeService.addTrainee(poeId, traineeId)
                .orElseThrow(() -> NoDataFoundError.withIds(
                        Map.of("poe", poeId, "trainee", traineeId)));
    }

    @PatchMapping("/{poeId}/addTrainees")
    public PoeFullDto addTrainees(
            @PathVariable("poeId") long poeId,
            @RequestBody List<Long> traineeIds
    ){
        return poeService.addTrainees(poeId, traineeIds)
                .orElseThrow(() -> NoDataFoundError.withIds(
                        Map.of("poe",poeId),
                        Map.of("trainees", traineeIds)
                ));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        poeService.remove(id);
    }
}
