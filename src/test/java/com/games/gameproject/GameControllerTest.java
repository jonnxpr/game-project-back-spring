package com.games.gameproject;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.games.gameproject.entities.Game;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@BeforeEach
	public void setup() throws Exception {
		// Popula o banco de dados com dados de teste antes de cada teste
		mockMvc.perform(post("/api/gamepedia/populate-database"))
				.andExpect(status().isOk());
	}

	@Test
	public void testFindAll() throws Exception {
		mockMvc.perform(get("/api/gamepedia/find-all"))
				.andExpect(status().isOk());
	}

	@Test
	public void testFindById() throws Exception {
		mockMvc.perform(get("/api/gamepedia/find-by-id")
				.param("id", "1"))
				.andExpect(status().isOk());
	}

	@Test
	public void testSave() throws Exception {
		Game game = new Game();
		game.setName("Test Game");
		game.setReleaseDate(new Timestamp(System.currentTimeMillis()));

		mockMvc.perform(post("/api/gamepedia/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(game)))
				.andExpect(status().isOk());
	}

	@Test
	public void testDeleteById() throws Exception {
		List<Long> ids = Arrays.asList(1L, 2L);

		mockMvc.perform(delete("/api/gamepedia/delete-by-id")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(ids)))
				.andExpect(status().isOk());
	}

	@Test
	public void testDeleteAll() throws Exception {
		mockMvc.perform(delete("/api/gamepedia/delete-all"))
				.andExpect(status().isOk());
	}

	@Test
	public void testFindByNameContainingIgnoreCase() throws Exception {
		mockMvc.perform(get("/api/gamepedia/find-by-name")
				.param("name", "test"))
				.andExpect(status().isOk());
	}

	@Test
	public void testFindByGenreContainingIgnoreCase() throws Exception {
		mockMvc.perform(get("/api/gamepedia/find-by-genre")
				.param("genre", "action"))
				.andExpect(status().isOk());
	}

	@Test
	public void testFindByPlatformContainingIgnoreCase() throws Exception {
		mockMvc.perform(get("/api/gamepedia/find-by-platform")
				.param("platform", "PC"))
				.andExpect(status().isOk());
	}

	@Test
	public void testFindByCompanyContainingIgnoreCase() throws Exception {
		mockMvc.perform(get("/api/gamepedia/find-by-company")
				.param("company", "Test Company"))
				.andExpect(status().isOk());
	}

	@Test
	public void testFindByReleaseDate() throws Exception {
		mockMvc.perform(get("/api/gamepedia/find-by-release-date")
				.param("releaseDate", "2023-01-01 00:00:00"))
				.andExpect(status().isOk());
	}

	@Test
	public void testFindByDescriptionContainingIgnoreCase() throws Exception {
		mockMvc.perform(get("/api/gamepedia/find-by-description")
				.param("description", "test description"))
				.andExpect(status().isOk());
	}

	@Test
	public void testPopulateDatabase() throws Exception {
		mockMvc.perform(post("/api/gamepedia/populate-database"))
				.andExpect(status().isOk());
	}
}