package eu.olaf.example.web;

import eu.olaf.example.model.test.Case;
import eu.olaf.example.service.impl.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/cases")
public class CaseController {

    @Autowired
    private CaseService caseService;

    @GetMapping(path = "/{id}")
    public Case getCase(@PathVariable Long id) {
        return caseService.get(id);
    }

    @GetMapping(path = "/")
    public Page<Case> getCases(Pageable pageable) {
        return caseService.findAll(pageable);
    }

    @PostMapping
    public Case create(@RequestBody Case cas) {
        return caseService.save(cas);
    }

    @PutMapping(path = "/{id}")
    public Case update(@PathVariable Long id, @RequestBody Case cas) {
        return caseService.update(cas);
    }

    @DeleteMapping
    public void delete(@PathVariable  Long id) {
        caseService.delete(id);
    }
}
