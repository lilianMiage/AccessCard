package fr.miage.lroux.accesscard;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.miage.lroux.accesscard.entity.AccessCard;
import fr.miage.lroux.accesscard.repository.RepoAccessCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
class AccessCardApplicationTests {


    @Autowired
    private MockMvc mvc;

    @Autowired
    private RepoAccessCard repoAccessCard;
    private AccessCard accessCard;

    @BeforeEach
    public void setUp(){
        accessCard = new AccessCard(new Random().nextInt());
        accessCard = repoAccessCard.save(accessCard);
    }

    @Test
    public void getAccessCardByid() throws Exception {
        mvc.perform(get("/api/accessCard/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.password", is(accessCard.getPassword())));
    }

    @Test
    public void createAccessCard() throws Exception{
        AccessCard accessCardObject = new AccessCard(new Random().nextInt());
        ObjectMapper om = new ObjectMapper();
        String accessCardJson = om.writeValueAsString(accessCardObject);
        mvc.perform(post("/api/accessCard/create")
                        .contentType("application/json")
                        .content(accessCardJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.password", is(accessCardObject.getPassword())));
    }

    @Test
    public void deleteAccessByid() throws Exception {
        mvc.perform(delete("/api/accessCard/delete/1"))
                .andExpect(status().isOk());
    }
}
