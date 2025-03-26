package com.games.gameproject.controller;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.games.gameproject.constants.MessageConstants;
import com.games.gameproject.dto.ResponseDTO;
import com.games.gameproject.entities.Game;
import com.games.gameproject.exception.CustomException;
import com.games.gameproject.service.GameService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/gamepedia")
@Tag(name = "Game Controller", description = "Endpoints for managing games")
public class GameController {

    private static final Logger logger = LoggerFactory.getLogger(GameController.class);

    @Autowired
    private GameService gameService;

    @Operation(summary = "Get a list of all games")
    @GetMapping("/find-all")
    @Tag(name = "GET Endpoints")
    public ResponseEntity<List<Game>> findAll() {
        try {
            logger.info("Fetching all games");
            List<Game> games = gameService.findAll();
            return ResponseEntity.ok(games);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException(MessageConstants.ERROR_FETCHING_ALL_GAMES, e);
        }
    }

    @Operation(summary = "Get a game by its ID")
    @GetMapping("/find-by-id")
    @Tag(name = "GET Endpoints")
    public ResponseEntity<Game> findById(@RequestParam Long id) {
        try {
            logger.info("Fetching game with ID: {}", id);
            Game game = gameService.findById(id);
            return ResponseEntity.ok(game);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException(MessageConstants.ERROR_FETCHING_GAME_BY_ID, e);
        }
    }

    @Operation(summary = "Save a game")
    @PostMapping("/save")
    @Tag(name = "POST Endpoints")
    public ResponseEntity<ResponseDTO> save(@RequestBody Game game) {
        try {
            logger.info("Saving game: {}", game.getName());
            gameService.save(game);
            return ResponseEntity.ok(new ResponseDTO(MessageConstants.GAME_SAVED_SUCCESSFULLY));
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException(MessageConstants.ERROR_SAVING_GAME, e);
        }
    }

    @Operation(summary = "Delete a game by its ID")
    @DeleteMapping("/delete-by-id")
    @Tag(name = "DELETE Endpoints")
    public ResponseEntity<ResponseDTO> deleteById(@RequestBody List<Long> ids) {
        try {
            logger.info("Deleting games with IDs: {}", ids);
            gameService.deleteById(ids);
            if (ids.size() == 1) {
                return ResponseEntity.ok(new ResponseDTO(MessageConstants.GAME_DELETED_SUCCESSFULLY));
            } else {
                return ResponseEntity.ok(new ResponseDTO(MessageConstants.GAMES_DELETED_SUCCESSFULLY));
            }
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException(MessageConstants.ERROR_DELETING_ALL_GAMES, e);
        }
    }

    @Operation(summary = "Delete all games")
    @DeleteMapping("/delete-all")
    @Tag(name = "DELETE Endpoints")
    public ResponseEntity<ResponseDTO> deleteAll() {
        try {
            logger.info("Deleting all games");
            gameService.deleteAll();
            return ResponseEntity.ok(new ResponseDTO(MessageConstants.GAMES_DELETED_SUCCESSFULLY));
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException(MessageConstants.ERROR_DELETING_ALL_GAMES, e);
        }
    }

    @Operation(summary = "Get a list of games by name")
    @GetMapping("/find-by-name")
    @Tag(name = "GET Endpoints")
    public ResponseEntity<List<Game>> findByNameContainingIgnoreCase(@RequestParam String name) {
        try {
            logger.info("Fetching games by name containing: {}", name);
            List<Game> games = gameService.findByNameContainingIgnoreCase(name);
            return ResponseEntity.ok(games);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException(MessageConstants.ERROR_FINDING_GAMES_BY_NAME, e);
        }
    }

    @Operation(summary = "Get a list of games by genre")
    @GetMapping("/find-by-genre")
    @Tag(name = "GET Endpoints")
    public ResponseEntity<List<Game>> findByGenreContainingIgnoreCase(@RequestParam String genre) {
        try {
            logger.info("Fetching games by genre containing: {}", genre);
            List<Game> games = gameService.findByGenreContainingIgnoreCase(genre);
            return ResponseEntity.ok(games);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException(MessageConstants.ERROR_FINDING_GAMES_BY_GENRE, e);
        }
    }

    @Operation(summary = "Get a list of games by platform")
    @GetMapping("/find-by-platform")
    @Tag(name = "GET Endpoints")
    public ResponseEntity<List<Game>> findByPlatformContainingIgnoreCase(@RequestParam String platform) {
        try {
            logger.info("Fetching games by platform containing: {}", platform);
            List<Game> games = gameService.findByPlatformContainingIgnoreCase(platform);
            return ResponseEntity.ok(games);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException(MessageConstants.ERROR_FINDING_GAMES_BY_PLATFORM, e);
        }
    }

    @Operation(summary = "Get a list of games by company")
    @GetMapping("/find-by-company")
    @Tag(name = "GET Endpoints")
    public ResponseEntity<List<Game>> findByCompanyContainingIgnoreCase(@RequestParam String company) {
        try {
            logger.info("Fetching games by company containing: {}", company);
            List<Game> games = gameService.findByCompanyContainingIgnoreCase(company);
            return ResponseEntity.ok(games);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException(MessageConstants.ERROR_FINDING_GAMES_BY_COMPANY, e);
        }
    }

    @Operation(summary = "Get a list of games by release date")
    @GetMapping("/find-by-release-date")
    @Tag(name = "GET Endpoints")
    public ResponseEntity<List<Game>> findByReleaseDate(@RequestParam Timestamp releaseDate) {
        try {
            logger.info("Fetching games by release date: {}", releaseDate);
            List<Game> games = gameService.findByReleaseDate(releaseDate);
            return ResponseEntity.ok(games);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException(MessageConstants.ERROR_FINDING_GAMES_BY_RELEASE_DATE, e);
        }
    }

    @Operation(summary = "Get a list of games by description")
    @GetMapping("/find-by-description")
    @Tag(name = "GET Endpoints")
    public ResponseEntity<List<Game>> findByDescriptionContainingIgnoreCase(@RequestParam String description) {
        try {
            logger.info("Fetching games by description containing: {}", description);
            List<Game> games = gameService.findByDescriptionContainingIgnoreCase(description);
            return ResponseEntity.ok(games);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException(MessageConstants.ERROR_FINDING_GAMES_BY_DESCRIPTION, e);
        }
    }

    @Operation(summary = "Populate the database with some games")
    @PostMapping("/populate-database")
    @Tag(name = "POST Endpoints")
    public ResponseEntity<ResponseDTO> populateDatabase() {
        try {
            logger.info("Populating database with sample games");
            gameService.populateDatabase();
            return ResponseEntity.ok(new ResponseDTO(MessageConstants.DATABASE_POPULATED_SUCCESSFULLY));
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException(MessageConstants.ERROR_POPULATING_DATABASE, e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {
        Game game = gameService.getGameById(id);
        return ResponseEntity.ok(game);
    }
}