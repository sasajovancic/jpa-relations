package eu.olaf.example.web;

import eu.olaf.example.SpringBootCrudRestApplication;
import eu.olaf.example.model.test.Case;
import eu.olaf.example.model.test.Person;
import eu.olaf.example.model.test.Seizure;
import eu.olaf.example.util.RestResponsePage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.PUT;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootCrudRestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CaseControllerRestTest {

    @Autowired
    private TestRestTemplate restTmpl;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void test_get() {
        Long id = 11L;

        Case res = restTmpl.getForObject(getRootUrl() + "/cases" + "/" + id.toString(), Case.class);
        assertNotNull(res);
    }

    @Test
    public void test_save() {
        Case cas = new Case().withName("case1").withSeizure(new Seizure().withDesc("seizure1"));
//        cas.setRefNumber("test");
        ResponseEntity<Case> res = restTmpl.postForEntity(getRootUrl() + "/cases", (Object) cas, Case.class);
        assertNotNull(res);
        assertEquals(200, res.getStatusCode().value());
    }


    @Test
    public void test_scenario_entity_issue() {
        ResponseEntity<Case> resCas1;
        {
            Case cas = new Case().withName("case1").withSeizure(new Seizure().withDesc("seizure1")).addPerson(Person.make().withName("Mark").withNationalNumber("BE01")).addPerson(Person.make().withName("Steve").withNationalNumber("BE02"));
            resCas1 = restTmpl.postForEntity(getRootUrl() + "/cases", (Object) cas, Case.class);
            assertNotNull(resCas1);
            assertEquals(200, resCas1.getStatusCode().value());
        }

        ResponseEntity<Case> resCas2;
        {
            Case cas = new Case().withName("case2").withSeizure(new Seizure().withDesc("seizure2"));
            resCas2 = restTmpl.postForEntity(getRootUrl() + "/cases", (Object) cas, Case.class);
            assertNotNull(resCas2);
            assertEquals(200, resCas2.getStatusCode().value());
        }
        {
            ParameterizedTypeReference<RestResponsePage<Case>> ptr = new ParameterizedTypeReference<RestResponsePage<Case>>() { };

            ResponseEntity<RestResponsePage<Case>> res = restTmpl.exchange(getRootUrl() + "/cases/", GET, null, ptr);
            assertNotNull(res);
            assertEquals(200, res.getStatusCode().value());
            assertTrue(res.getBody().getContent().size() == 2);
            System.out.println(res.getBody().getContent());
        }

        Case cas2WithId = resCas2.getBody();
        Case cas1WithId = resCas1.getBody();

        //update case2 by adding person1 from case1
        cas2WithId.addPerson(cas1WithId.getPersons().get(0));
        HttpEntity<Case> requestEntity = new HttpEntity<>(cas2WithId);
        //String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType

        ResponseEntity<Case> resUpdateCas2 = restTmpl.exchange(getRootUrl() + "/cases/" + cas2WithId.getId().toString(), PUT, requestEntity, Case.class);
        Case cas2WithIdUpdated = resUpdateCas2.getBody();
        assertNotNull(resUpdateCas2);
        assertEquals(200, resUpdateCas2.getStatusCode().value());

        System.out.println("----------After Update------------");


        {
            ParameterizedTypeReference<RestResponsePage<Case>> ptr = new ParameterizedTypeReference<RestResponsePage<Case>>() { };

            ResponseEntity<RestResponsePage<Case>> res = restTmpl.exchange(getRootUrl() + "/cases/", GET, null, ptr);
            assertNotNull(res);
            assertEquals(200, res.getStatusCode().value());
            assertTrue(res.getBody().getContent().size() == 2);
            System.out.println(res.getBody().getContent());
        }




    }
}
