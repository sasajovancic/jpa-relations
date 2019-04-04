package eu.olaf.example.service.impl;

import eu.olaf.example.model.Case;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CaseServiceJpaTest {

    @TestConfiguration
    static class CaseServiceImplTestContextConfiguration {
        @Bean
        public CaseService caseService() { return new CaseServiceImpl(); }
    }

    @Autowired
    CaseService caseService;

    @Test
    public void test_get() {
        Case cas = createInDb(11L, "123");

        Case res = caseService.get(cas.getId());
        assertNotNull(res);
        assertEquals(cas.getId(), res.getId());
    }

    Case createInDb(Long id, String ref) {
        Case cas = Case.make().withId(id).withRefNumber(ref);
        return caseService.save(cas);
    }
}
