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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.springframework.http.HttpMethod.GET;


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
        {
            Case cas = new Case().withName("case1").withSeizure(new Seizure().withDesc("seizure1"))
                    .addPerson(Person.make().withName("Matt").withId(1l));
            ResponseEntity<Case> res = restTmpl.postForEntity(getRootUrl() + "/cases", (Object) cas, Case.class);
            assertNotNull(res);
            assertEquals(200, res.getStatusCode().value());
        }
        {
            Case cas = new Case().withName("case2").withSeizure(new Seizure().withDesc("seizure2"));
            ResponseEntity<Case> res = restTmpl.postForEntity(getRootUrl() + "/cases", (Object) cas, Case.class);
            assertNotNull(res);
            assertEquals(200, res.getStatusCode().value());
        }
        {
            ParameterizedTypeReference<RestResponsePage<Case>> ptr = new ParameterizedTypeReference<RestResponsePage<Case>>() { };

            ResponseEntity<RestResponsePage<Case>> res = restTmpl.exchange(getRootUrl() + "/cases/", GET, null, ptr);
            assertNotNull(res);
            assertEquals(200, res.getStatusCode().value());
            assertTrue(res.getBody().getContent().size() == 2);

            System.out.println(res.getBody().getContent());
        }
//
//        ResponseEntity<String> resString = restTmpl.getForEntity(getRootUrl()+ "/cases" , String.class);
//
//        System.out.println(resString.toString());
//
//
//        Page res = restTmpl.getForObject(getRootUrl()+"/cases" , Page.class);
//        assertNotNull(res);
//        System.out.println(res.toString());



    }
}
