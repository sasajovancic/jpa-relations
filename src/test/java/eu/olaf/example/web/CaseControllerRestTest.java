package eu.olaf.example.web;

import eu.olaf.example.SpringBootCrudRestApplication;
import eu.olaf.example.model.Case;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;


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
        Case cas = new Case();
        cas.setRefNumber("test");
        ResponseEntity<Case> res = restTmpl.postForEntity(getRootUrl() + "/cases", (Object) cas, Case.class);
        assertNotNull(res);
        assertEquals(200, res.getStatusCode().value());
    }
}
