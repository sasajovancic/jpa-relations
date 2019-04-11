package eu.olaf.example.service.impl;

import eu.olaf.example.model.test.Case;
import eu.olaf.example.repo.CaseRepository;
import eu.olaf.example.service.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CaseServiceImpl implements CaseService {

    @Autowired
    private CaseRepository caseRepo;

    @Override
    @Transactional(readOnly = true)
    public Case get(Long id) {
        Optional<Case> optCase = caseRepo.findById(id);
        if (optCase.isPresent()) {
            return optCase.get();
        } else {
            throw new NotFoundException("Not found obj by id:" + id);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Case> findAll(Pageable pageable) {
        return caseRepo.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = false)
    public Case save(Case cas) {
        return caseRepo.save(fix(cas));
    }

    @Override
    @Transactional(readOnly = false)
    public Case update(Case cas) {
        return caseRepo.save(fix(cas));
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        caseRepo.deleteById(id);
    }

    private Case fix(Case cas) {
        if (cas.getSeizure() != null) {
            cas.getSeizure().setCas(cas);
        }
        return cas;
    }
}
