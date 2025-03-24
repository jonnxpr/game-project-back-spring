package com.games.gameproject;

import java.sql.Timestamp;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.games.gameproject.constants.MessageConstants;
import com.games.gameproject.entities.Game;

@SpringBootTest(properties = { "spring.profiles.active=test" })
@AutoConfigureMockMvc
public class GameControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private Environment env;

	/**
	 * Popula o banco de dados com dados de teste antes de cada teste.
	 */
	@BeforeEach
	public void setup() throws Exception {
		System.out.println("Using database: " + env.getProperty("spring.datasource.url"));
		mockMvc.perform(post("/api/gamepedia/populate-database"))
				.andExpect(status().isOk());
	}

	/**
	 * Testa a busca de todos os jogos.
	 */
	@Test
	public void testFindAll() throws Exception {
		mockMvc.perform(get("/api/gamepedia/find-all"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray());
	}

	/**
	 * Testa a busca de um jogo pelo ID.
	 */
	@Test
	public void testFindById() throws Exception {
		// Adiciona um jogo de teste com ID 1
		Game game = new Game();
		game.setName("Test Game");
		game.setReleaseDate(new Timestamp(System.currentTimeMillis()));

		// Salva o jogo de teste
		mockMvc.perform(post("/api/gamepedia/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(game)))
				.andExpect(status().isOk());

		// Obtém o ID do jogo salvo
		Long savedGameId = objectMapper.readValue(
				mockMvc.perform(get("/api/gamepedia/find-all"))
						.andExpect(status().isOk())
						.andReturn()
						.getResponse()
						.getContentAsString(),
				Game[].class)[0].getId();

		// Testa a busca pelo ID
		mockMvc.perform(get("/api/gamepedia/find-by-id")
				.param("id", savedGameId.toString()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(savedGameId));
	}

	/**
	 * Testa a criação de um novo jogo.
	 */
	@Test
	public void testSave() throws Exception {
		Game game = new Game();
		game.setName("Test Game");
		game.setReleaseDate(new Timestamp(System.currentTimeMillis()));

		mockMvc.perform(post("/api/gamepedia/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(game)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message").value(MessageConstants.GAME_SAVED_SUCCESSFULLY));
	}

	/**
	 * Testa a exclusão de um jogo pelo ID.
	 */
	@Test
	public void testDeleteById() throws Exception {
		// Adiciona um jogo de teste
		Game game = new Game();
		game.setName("Test Game");
		game.setReleaseDate(new Timestamp(System.currentTimeMillis()));

		// Salva o jogo de teste
		mockMvc.perform(post("/api/gamepedia/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(game)))
				.andExpect(status().isOk());

		// Obtém o ID do jogo salvo
		Long savedGameId = objectMapper.readValue(
				mockMvc.perform(get("/api/gamepedia/find-all"))
						.andExpect(status().isOk())
						.andReturn()
						.getResponse()
						.getContentAsString(),
				Game[].class)[0].getId();

		// Testa a exclusão pelo ID
		mockMvc.perform(delete("/api/gamepedia/delete-by-id")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(Arrays.asList(savedGameId))))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message").value(MessageConstants.GAME_DELETED_SUCCESSFULLY));
	}

	/**
	 * Testa a exclusão de todos os jogos.
	 */
	@Test
	public void testDeleteAll() throws Exception {
		mockMvc.perform(delete("/api/gamepedia/delete-all"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message").value(MessageConstants.GAMES_DELETED_SUCCESSFULLY));
	}

	/**
	 * Testa a busca de jogos pelo nome, ignorando maiúsculas e minúsculas.
	 */
	@Test
	public void testFindByNameContainingIgnoreCase() throws Exception {
		mockMvc.perform(get("/api/gamepedia/find-by-name")
				.param("name", "test"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray());
	}

	/**
	 * Testa a busca de jogos pelo gênero, ignorando maiúsculas e minúsculas.
	 */
	@Test
	public void testFindByGenreContainingIgnoreCase() throws Exception {
		mockMvc.perform(get("/api/gamepedia/find-by-genre")
				.param("genre", "action"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray());
	}

	/**
	 * Testa a busca de jogos pela plataforma, ignorando maiúsculas e minúsculas.
	 */
	@Test
	public void testFindByPlatformContainingIgnoreCase() throws Exception {
		mockMvc.perform(get("/api/gamepedia/find-by-platform")
				.param("platform", "PC"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray());
	}

	/**
	 * Testa a busca de jogos pela empresa, ignorando maiúsculas e minúsculas.
	 */
	@Test
	public void testFindByCompanyContainingIgnoreCase() throws Exception {
		mockMvc.perform(get("/api/gamepedia/find-by-company")
				.param("company", "Test Company"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray());
	}

	/**
	 * Testa a busca de jogos pela data de lançamento.
	 */
	@Test
	public void testFindByReleaseDate() throws Exception {
		mockMvc.perform(get("/api/gamepedia/find-by-release-date")
				.param("releaseDate", "2023-01-01 00:00:00"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray());
	}

	/**
	 * Testa a busca de jogos pela descrição, ignorando maiúsculas e minúsculas.
	 */
	@Test
	public void testFindByDescriptionContainingIgnoreCase() throws Exception {
		mockMvc.perform(get("/api/gamepedia/find-by-description")
				.param("description", "test description"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray());
	}

	/**
	 * Testa a população do banco de dados com dados de teste.
	 */
	@Test
	public void testPopulateDatabase() throws Exception {
		mockMvc.perform(post("/api/gamepedia/populate-database"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message").value(MessageConstants.DATABASE_POPULATED_SUCCESSFULLY));
	}
}