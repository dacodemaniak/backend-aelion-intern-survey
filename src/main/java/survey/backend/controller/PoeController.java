package survey.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.PoeDto;
import survey.backend.dto.PoeFullDto;
import survey.backend.error.NoDataFoundError;
import survey.backend.service.PoeService;

import java.util.Collection;
import java.util.List;

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
                .orElseThrow(() -> NoDataFoundError.withId("Poe", id));
    }

    @PatchMapping("/{poeId}/addTrainee/{traineeId}")
    public PoeFullDto addTrainee(
            @PathVariable("poeId") long poeId,
            @PathVariable("traineeId") long traineeId)
    {
        return poeService.addTrainee(poeId, traineeId)
                .orElseThrow(() -> NoDataFoundError.withIds("Poe or Trainee", poeId, traineeId));
    }

    @PatchMapping("/{poeId}/addTrainees")
    public PoeFullDto addTrainees(
            @PathVariable("poeId") long poeId,
            @RequestBody List<Long> traineeIds
    ){
        return poeService.addTrainees(poeId, traineeIds)
                .orElseThrow(() -> NoDataFoundError.withIds("Poe or trainees",poeId));
    }
}
