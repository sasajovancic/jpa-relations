package eu.olaf.example.repo;

import eu.olaf.example.model.Case;
import org.hibernate.testing.transaction.TransactionUtil;
import org.hibernate.testing.transaction.TransactionUtil2;
import org.junit.After;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CaseRepositorySpringJpaTest {

    @Autowired
    CaseRepository repository;

    List<Long> ids = new ArrayList<>();

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
        Case cas = new Case();
        cas.setTitle("test");
        cas.setRefNumber("123");

        Case res = repository.save(cas);
        assertNotNull(res);
        ids.add(res.getId());
        assertEquals("test", res.getTitle());

        repository.deleteById(res.getId());
        Optional<Case> opt = repository.findById(res.getId());
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
