package eu.olaf.example.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.olaf.example.service.impl.CaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(CaseController.class)
public class CaseControllerMvcTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CaseService caseService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void test_get() {
        Long id = 11L;
        // given()

//        mockMvc.perform(get("/cases/" + id.toString())
//                .contentType(MediaType.APPLICATION_JSON)
//                //.content(objectMapper.writeValueAsBytes(new Case())))
//                // .andExpect()

    }

    @Test
    public void test_save() {
        Long id = 11L;
        // given()

//        mockMvc.perform(post("/cases")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsBytes(new Case())))
//                .andExpect()

    }
}
