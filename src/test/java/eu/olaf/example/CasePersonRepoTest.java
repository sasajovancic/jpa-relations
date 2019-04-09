package eu.olaf.example;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.olaf.example.model.test.Case;
import eu.olaf.example.model.test.CompositeId;
import eu.olaf.example.model.test.Person;
import eu.olaf.example.service.impl.CaseService;
import eu.olaf.example.service.impl.CaseServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CasePersonRepoTest {

    @Autowired
    CaseService caseService;

    ObjectMapper mapper = new ObjectMapper();

    @TestConfiguration
    static class TestContextConfiguration {

        @Bean
        public CaseService caseService() {
            return new CaseServiceImpl();
        }
    }

    @Test
    @Transactional(readOnly = false)
    @Commit
    public void test_save() throws IOException {
        // Case cas = Case.make().withName("AAA").addPerson(Person.make().withName("PERSON_10")).addPerson(Person.make().withName("PERSON_11"));

        String caseStr = "{\n" +
                "\t\"name\": \"AAA\",\n" +
                "    \"seizure\": {\n" +
                "    \t\"desc\": \"111\"\n" +
                "\t},\n" +
                "\t\"persons\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"name_10\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"name\": \"name_11\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";
        Case casNew = mapper.readValue(caseStr, Case.class);

        Case res = caseService.save(fix(casNew));
        assertNotNull(res);
    }

    @Test
    @Transactional(readOnly = false)
    @Commit
    public void test_update() throws IOException {
        Case cas = Case.make().withName("AAA").addPerson(Person.make().withName("PERSON_10")).addPerson(Person.make().withName("PERSON_11"));
        Case res = caseService.save(cas);
        assertNotNull(res);

        cas = caseService.findAll(PageRequest.of(0, 10)).getContent().get(0);
        String casStr = mapper.writeValueAsString(cas);

        Case casNew = mapper.readValue(casStr, Case.class);

        res = caseService.update(fix(casNew));
        assertNotNull(res);
    }

    private Case fix(Case cas) {
        if (cas.getPersons() != null) {
            cas.getPersons().stream().forEach(p -> {
                if (p.getCompositeId() == null) {
                    p.setCompositeId(new CompositeId());
                }
                p.getCompositeId().setCas(cas);
            });
        }
        return cas;
    }
}
