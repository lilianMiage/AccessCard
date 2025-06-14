package fr.miage.lroux.accesscard.controller;

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
class AccessCardControllerTests {

	/**
	 * MockMvc instance for testing the controller.
	 */
	@Autowired
	private MockMvc mvc;

	/**
	 * Repository for AccessCard operations.
	 * This repository is used to interact with the database for AccessCard entities.
	 */
	@Autowired
	private RepoAccessCard repoAccessCard;

	/**
	 * AccessCard instance used for testing.
	 * This instance is created before each test to ensure a consistent state.
	 */
	private AccessCard accessCard;

	/**
	 * Sets up the test environment by creating a new AccessCard instance.
	 * This method is called before each test to ensure a fresh state.
	 */
	@BeforeEach
	public void setUp(){
		accessCard = new AccessCard(123);
		accessCard = repoAccessCard.save(accessCard);
	}

	/**
	 * Tests the retrieval of an AccessCard by its ID.
	 * This test checks if the correct AccessCard is returned with the expected password.
	 */
	@Test
	public void getAccessCardByid() throws Exception {
		mvc.perform(get("/api/accessCard/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.password", is(accessCard.getPassword())));
	}

	/**
	 * Tests the creation of a new AccessCard.
	 * This test checks if the AccessCard is created successfully and the returned password matches.
	 */
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

	/**
	 * Tests the deletion of an AccessCard by its ID.
	 * This test checks if the AccessCard is deleted successfully.
	 */
	@Test
	public void deleteAccessByid() throws Exception {
		mvc.perform(delete("/api/accessCard/1"))
				.andExpect(status().isOk());
	}

	/**
	 * Tests the retrieval of an AccessCard by user ID.
	 * This test checks if the correct AccessCard is returned when searching by user ID.
	 */
	@Test
	public void getAccessCardByUserId() throws Exception {
		accessCard.setUserId(1L);
		repoAccessCard.save(accessCard);
		mvc.perform(get("/api/accessCard/user/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.userId", is(1)))
				.andExpect(jsonPath("$.password", is(accessCard.getPassword())));
	}


}
