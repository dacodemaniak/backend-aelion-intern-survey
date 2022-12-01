package survey.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import survey.backend.entities.Poe;
import survey.backend.service.impl.PoeService;

@RestController
@RequestMapping("api/poe")
public class PoeController {
    @Autowired
    private PoeService service;
    @GetMapping
    public Iterable<Poe> findAll() {
        return this.service.findAll();
    }
}
