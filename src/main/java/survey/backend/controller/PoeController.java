package survey.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import survey.backend.dto.PoeDto;
import survey.backend.dto.PoeFullDto;
import survey.backend.entities.Poe;
import survey.backend.error.NoDataFoundError;
import survey.backend.service.PoeService;
import survey.backend.service.impl.PoeServiceImplJpa;

import java.util.Collection;

@RestController
@RequestMapping("api/poe")
public class PoeController {
    @Autowired
    private PoeService service;
    @GetMapping
    public Collection<PoeDto> findAll() {
        return this.service.findAll();
    }

    @GetMapping("/{id}")
    public PoeFullDto findById(@PathVariable("id") long id){
        return service.findById(id)
                .orElseThrow(() -> {throw NoDataFoundError.withId("Poe", id);});
    }
}
