package com.games.gameproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.games.gameproject.entities.Game;
import com.games.gameproject.repository.GameRepository;
import com.games.gameproject.service.GameService;

@SpringBootTest(properties = { "spring.profiles.active=test" })
public class GameServiceTest {

        @Autowired
        private GameService gameService;

        @MockBean
        private GameRepository gameRepository;

        private Game game;

        /**
         * Configura um jogo de teste antes de cada teste.
         */
        @BeforeEach
        public void setup() {
                game = new Game(1L, "Test Game", "Action", "PC", Timestamp.valueOf("2023-01-01 00:00:00"),
                                "Test Company", "Test Description");
        }

        /**
         * Testa a busca de todos os jogos.
         */
        @Test
        public void testFindAll() {
                when(gameRepository.findAll()).thenReturn(Arrays.asList(game));
                List<Game> games = gameService.findAll();
                assertNotNull(games);
                assertEquals(1, games.size());
        }

        /**
         * Testa a busca de um jogo pelo ID.
         */
        @Test
        public void testFindById() {
                when(gameRepository.findById(anyLong())).thenReturn(Optional.of(game));
                Game foundGame = gameService.findById(1L);
                assertNotNull(foundGame);
                assertEquals(game.getId(), foundGame.getId());
        }

        /**
         * Testa a criação de um novo jogo.
         */
        @Test
        public void testSave() {
                when(gameRepository.save(any(Game.class))).thenReturn(game);
                Game savedGame = gameService.save(game);
                assertNotNull(savedGame);
                assertEquals(game.getName(), savedGame.getName());
        }

        /**
         * Testa a exclusão de jogos pelo ID.
         */
        @Test
        public void testDeleteById() {
                gameService.deleteById(Arrays.asList(1L, 2L));
                verify(gameRepository, times(1)).deleteById(1L);
                verify(gameRepository, times(1)).deleteById(2L);
        }

        /**
         * Testa a exclusão de todos os jogos.
         */
        @Test
        public void testDeleteAll() {
                gameService.deleteAll();
                verify(gameRepository, times(1)).deleteAll();
        }

        /**
         * Testa a busca de jogos pelo nome, ignorando maiúsculas e minúsculas.
         */
        @Test
        public void testFindByNameContainingIgnoreCase() {
                when(gameRepository.findByNameContainingIgnoreCase(any(String.class))).thenReturn(Arrays.asList(game));
                List<Game> games = gameService.findByNameContainingIgnoreCase("test");
                assertNotNull(games);
                assertEquals(1, games.size());
        }

        /**
         * Testa a busca de jogos pelo gênero, ignorando maiúsculas e minúsculas.
         */
        @Test
        public void testFindByGenreContainingIgnoreCase() {
                when(gameRepository.findByGenreContainingIgnoreCase(any(String.class))).thenReturn(Arrays.asList(game));
                List<Game> games = gameService.findByGenreContainingIgnoreCase("action");
                assertNotNull(games);
                assertEquals(1, games.size());
        }

        /**
         * Testa a busca de jogos pela plataforma, ignorando maiúsculas e minúsculas.
         */
        @Test
        public void testFindByPlatformContainingIgnoreCase() {
                when(gameRepository.findByPlatformContainingIgnoreCase(any(String.class)))
                                .thenReturn(Arrays.asList(game));
                List<Game> games = gameService.findByPlatformContainingIgnoreCase("PC");
                assertNotNull(games);
                assertEquals(1, games.size());
        }

        /**
         * Testa a busca de jogos pela empresa, ignorando maiúsculas e minúsculas.
         */
        @Test
        public void testFindByCompanyContainingIgnoreCase() {
                when(gameRepository.findByCompanyContainingIgnoreCase(any(String.class)))
                                .thenReturn(Arrays.asList(game));
                List<Game> games = gameService.findByCompanyContainingIgnoreCase("Test Company");
                assertNotNull(games);
                assertEquals(1, games.size());
        }

        /**
         * Testa a busca de jogos pela data de lançamento.
         */
        @Test
        public void testFindByReleaseDate() {
                when(gameRepository.findByReleaseDate(any(Timestamp.class))).thenReturn(Arrays.asList(game));
                List<Game> games = gameService.findByReleaseDate(Timestamp.valueOf("2023-01-01 00:00:00"));
                assertNotNull(games);
                assertEquals(1, games.size());
        }

        /**
         * Testa a busca de jogos pela descrição, ignorando maiúsculas e minúsculas.
         */
        @Test
        public void testFindByDescriptionContainingIgnoreCase() {
                when(gameRepository.findByDescriptionContainingIgnoreCase(any(String.class)))
                                .thenReturn(Arrays.asList(game));
                List<Game> games = gameService.findByDescriptionContainingIgnoreCase("test description");
                assertNotNull(games);
                assertEquals(1, games.size());
        }

        /**
         * Testa a população do banco de dados com dados de teste.
         */
        @SuppressWarnings("unchecked")
        @Test
        public void testPopulateDatabase() {
                gameService.populateDatabase();
                verify(gameRepository, times(1)).deleteAll();
                verify(gameRepository, times(1)).saveAll(any(Iterable.class));
        }
}