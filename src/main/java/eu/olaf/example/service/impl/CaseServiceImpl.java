package eu.olaf.example.service.impl;

import eu.olaf.example.model.test.Case;
import eu.olaf.example.model.test.CompositeId;
import eu.olaf.example.model.test.Person;
import eu.olaf.example.model.test.Seizure;
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
        Case cNew = new Case();

        cNew.setId(cas.getId());
        cNew.setName(cas.getName());

        if (cas.getSeizure() != null) {
            Seizure sNew = new Seizure();

            sNew.setId(cas.getSeizure().getId());
            sNew.setDesc(cas.getSeizure().getDesc());

            cNew.setSeizure(sNew);
        }

        if (cas.getPersons() != null) {
            for (Person p : cas.getPersons()) {
                Person pNew = new Person();

                pNew.setName(p.getName());
                pNew.setNationalNumber(p.getNationalNumber());

//                if (p.getCompositeId() != null) {
//                    CompositeId newId = new CompositeId();
//                    newId.setId(p.getCompositeId().getId());
//                    pNew.setCompositeId(newId);
//                }
                pNew.setCas(cas);
                cNew.addPerson(pNew);
            }
        }

        System.out.println(cNew);
        return cNew;

//        if (cas.getPersons() != null) {
//            for(Person p : cas.getPersons()) {
//                if (p.getCompositeId() == null) {
//                    p.setCompositeId(new CompositeId());
//                }
//                if (p.getCompositeId().getCas() == null) {
//                    p.getCompositeId().setCas(cas);
//                }
//            }
//        }
//        return cas;
    }
}
