package com.games.gameproject;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {

	// @Autowired
	// private MockMvc mockMvc;

	// @Autowired
	// private ObjectMapper objectMapper;

	// @Test
	// public void testFindAll() throws Exception {
	// mockMvc.perform(get("/game/find-all"))
	// .andExpect(status().isOk())
	// .andExpect(content().contentType(MediaType.APPLICATION_JSON));
	// }

	// @Test
	// public void testFindById() throws Exception {
	// mockMvc.perform(get("/game/find-by-id").param("id", "1"))
	// .andExpect(status().isOk())
	// .andExpect(content().contentType(MediaType.APPLICATION_JSON));
	// }

	// @Test
	// public void testSave() throws Exception {
	// Game game = new Game();
	// game.setName("Test Game");
	// String gameJson = objectMapper.writeValueAsString(game);

	// mockMvc.perform(post("/game/save")
	// .contentType(MediaType.APPLICATION_JSON)
	// .content(gameJson))
	// .andExpect(status().isOk())
	// .andExpect(content().contentType(MediaType.APPLICATION_JSON));
	// }

	// @Test
	// public void testDeleteById() throws Exception {
	// mockMvc.perform(delete("/game/delete-by-id").param("id", "1"))
	// .andExpect(status().isNoContent());
	// }

	// @Test
	// public void testFindByNameContainingIgnoreCase() throws Exception {
	// mockMvc.perform(get("/game/find-by-name").param("name", "test"))
	// .andExpect(status().isOk())
	// .andExpect(content().contentType(MediaType.APPLICATION_JSON));
	// }

	// @Test
	// public void testFindByGenreContainingIgnoreCase() throws Exception {
	// mockMvc.perform(get("/game/find-by-genre").param("genre", "action"))
	// .andExpect(status().isOk())
	// .andExpect(content().contentType(MediaType.APPLICATION_JSON));
	// }

	// @Test
	// public void testFindByPlatformContainingIgnoreCase() throws Exception {
	// mockMvc.perform(get("/game/find-by-platform").param("platform", "pc"))
	// .andExpect(status().isOk())
	// .andExpect(content().contentType(MediaType.APPLICATION_JSON));
	// }

	// @Test
	// public void testFindByCompanyContainingIgnoreCase() throws Exception {
	// mockMvc.perform(get("/game/find-by-company").param("company", "company"))
	// .andExpect(status().isOk())
	// .andExpect(content().contentType(MediaType.APPLICATION_JSON));
	// }

	// @Test
	// public void testFindByReleaseDate() throws Exception {
	// Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	// mockMvc.perform(get("/game/find-by-release-date").param("releaseDate",
	// timestamp.toString()))
	// .andExpect(status().isOk())
	// .andExpect(content().contentType(MediaType.APPLICATION_JSON));
	// }

	// @Test
	// public void testFindByDescriptionContainingIgnoreCase() throws Exception {
	// mockMvc.perform(get("/game/find-by-description").param("description", "fun"))
	// .andExpect(status().isOk())
	// .andExpect(content().contentType(MediaType.APPLICATION_JSON));
	// }

	// @Test
	// public void testPopulateDatabase() throws Exception {
	// mockMvc.perform(post("/game/populate-database"))
	// .andExpect(status().isOk())
	// .andExpect(content().contentType(MediaType.APPLICATION_JSON));
	// }
}