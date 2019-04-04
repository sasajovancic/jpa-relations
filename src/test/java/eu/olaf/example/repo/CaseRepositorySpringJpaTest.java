package eu.olaf.example.repo;

import eu.olaf.example.model.Case;
import org.hibernate.testing.transaction.TransactionUtil;
import org.hibernate.testing.transaction.TransactionUtil2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CaseRepositorySpringJpaTest {

    @Autowired
    CaseRepository repository;

    @Autowired
    EntityManagerFactory emf;

    public EntityManagerFactory emf() {
        return emf;
    }
    List<Long> ids = new ArrayList<>();

    long caseId;

    @Before
    @Transactional(readOnly = false)
    @Commit
    public void init(){
        TransactionUtil.doInJPA(this::emf, entityManager-> {
            Case a1 = Case.make().withRefNumber("ref123");
            a1.setTitle("Mr. Me");
            entityManager.persist(a1);
            caseId = a1.getId();
        });
    }

    @After
    public void after() {
        for (Long id : ids) {
            clean(id);
        }
    }

    @Test
    @Transactional(readOnly = false)
    @Commit
    public void test_save() {
        Case cas = new Case();
        cas.setTitle("test");
        cas.setRefNumber("123");

        Case res = repository.save(cas);
        assertNotNull(res);
        ids.add(res.getId());
        assertEquals("test", res.getTitle());

        res = repository.findById(res.getId()).get();
        assertNotNull(res);
        assertEquals("test", res.getTitle());

        // add hamcrest
        // assertThat(repository.findById(res.getId()).get()).has(cas);

    }

    @Test
    @Transactional(readOnly = false)
    @Commit
    public void test_delete() {

        Optional<Case> opt = repository.findById(caseId);
        assertTrue(opt.isPresent());
        repository.deleteById(caseId);
        opt = repository.findById(caseId);
        assertFalse(opt.isPresent());
    }

    @Test
    public void test_findById() {
        Case cas = new Case();
        cas.setTitle("test");
        cas.setRefNumber("123");

        Case res = repository.save(cas);
        assertNotNull(res);
        ids.add(res.getId());
        assertEquals("test", res.getTitle());

        res = repository.findById(res.getId()).get();
        assertNotNull(res);
        assertEquals("test", res.getTitle());
    }

    @Test
    public void test_find() {
        Case cas = new Case();
        cas.setTitle("test");
        cas.setRefNumber("123");

        Case res = repository.save(cas);
        assertNotNull(res);
        ids.add(res.getId());
        assertEquals("test", res.getTitle());

        cas = new Case();
        cas.setTitle("test2");
        cas.setRefNumber("1232");

        res = repository.save(cas);
        assertNotNull(res);
        ids.add(res.getId());
        assertEquals("test2", res.getTitle());

        Pageable pageable = PageRequest.of(0, 10);
        Page<Case> list = repository.findAll(pageable);
        assertNotNull(list);
        assertNotNull(list.getContent());
        assertTrue(list.getContent().size() > 0);
    }

    @Transactional(readOnly = false)
    @Commit
    void clean(Long id) {
        repository.deleteById(id);
    }
}
