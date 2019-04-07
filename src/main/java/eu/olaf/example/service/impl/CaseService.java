package eu.olaf.example.service.impl;

import eu.olaf.example.model.test.Case;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CaseService {
    Case get(Long id);
    Page<Case> findAll(Pageable pageable);
    Case save(Case cas);
    Case update(Case cas);
    void delete(Long id);
}
