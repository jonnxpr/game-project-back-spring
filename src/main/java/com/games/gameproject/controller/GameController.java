package com.games.gameproject.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private GameService gameService;

    @Operation(summary = "Get a list of all games")
    @GetMapping("/find-all")
    @Tag(name = "GET Endpoints")
    public ResponseEntity<?> findAll() {
        try {
            List<Game> games = gameService.findAll();
            return ResponseEntity.ok(games);
        } catch (CustomException e) {
            return ResponseEntity.status(500).body(new ResponseDTO(e.getMessage()));
        }
    }

    @Operation(summary = "Get a game by its ID")
    @GetMapping("/find-by-id")
    @Tag(name = "GET Endpoints")
    public ResponseEntity<?> findById(@RequestParam Long id) {
        try {
            Game game = gameService.findById(id);
            return ResponseEntity.ok(game);
        } catch (CustomException e) {
            return ResponseEntity.status(500).body(new ResponseDTO(e.getMessage()));
        }
    }

    @Operation(summary = "Save a game")
    @PostMapping("/save")
    @Tag(name = "POST Endpoints")
    public ResponseEntity<?> save(@RequestBody Game game) {
        try {
            gameService.save(game);
            return ResponseEntity.ok(new ResponseDTO("Game stored successfully!"));
        } catch (CustomException e) {
            return ResponseEntity.status(500).body(new ResponseDTO(e.getMessage()));
        }
    }

    @Operation(summary = "Delete a game by its ID")
    @DeleteMapping("/delete-by-id")
    @Tag(name = "DELETE Endpoints")
    public ResponseEntity<?> deleteById(@RequestBody List<Long> ids) {
        try {
            gameService.deleteById(ids);
            if (ids.size() == 1) {
                return ResponseEntity.ok(new ResponseDTO("Game deleted successfully!"));
            } else {
                return ResponseEntity.ok(new ResponseDTO("Games deleted successfully!"));
            }
        } catch (CustomException e) {
            return ResponseEntity.status(500).body(new ResponseDTO(e.getMessage()));
        }
    }

    @Operation(summary = "Delete all games")
    @DeleteMapping("/delete-all")
    @Tag(name = "DELETE Endpoints")
    public ResponseEntity<?> deleteAll() {
        try {
            gameService.deleteAll();
            return ResponseEntity.ok(new ResponseDTO("Games deleted successfully!"));
        } catch (CustomException e) {
            return ResponseEntity.status(500).body(new ResponseDTO(e.getMessage()));
        }
    }

    @Operation(summary = "Get a list of games by name")
    @GetMapping("/find-by-name")
    @Tag(name = "GET Endpoints")
    public ResponseEntity<?> findByNameContainingIgnoreCase(@RequestParam String name) {
        try {
            List<Game> games = gameService.findByNameContainingIgnoreCase(name);
            return ResponseEntity.ok(games);
        } catch (CustomException e) {
            return ResponseEntity.status(500).body(new ResponseDTO(e.getMessage()));
        }
    }

    @Operation(summary = "Get a list of games by genre")
    @GetMapping("/find-by-genre")
    @Tag(name = "GET Endpoints")
    public ResponseEntity<?> findByGenreContainingIgnoreCase(@RequestParam String genre) {
        try {
            List<Game> games = gameService.findByGenreContainingIgnoreCase(genre);
            return ResponseEntity.ok(games);
        } catch (CustomException e) {
            return ResponseEntity.status(500).body(new ResponseDTO(e.getMessage()));
        }
    }

    @Operation(summary = "Get a list of games by platform")
    @GetMapping("/find-by-platform")
    @Tag(name = "GET Endpoints")
    public ResponseEntity<?> findByPlatformContainingIgnoreCase(@RequestParam String platform) {
        try {
            List<Game> games = gameService.findByPlatformContainingIgnoreCase(platform);
            return ResponseEntity.ok(games);
        } catch (CustomException e) {
            return ResponseEntity.status(500).body(new ResponseDTO(e.getMessage()));
        }
    }

    @Operation(summary = "Get a list of games by company")
    @GetMapping("/find-by-company")
    @Tag(name = "GET Endpoints")
    public ResponseEntity<?> findByCompanyContainingIgnoreCase(@RequestParam String company) {
        try {
            List<Game> games = gameService.findByCompanyContainingIgnoreCase(company);
            return ResponseEntity.ok(games);
        } catch (CustomException e) {
            return ResponseEntity.status(500).body(new ResponseDTO(e.getMessage()));
        }
    }

    @Operation(summary = "Get a list of games by release date")
    @GetMapping("/find-by-release-date")
    @Tag(name = "GET Endpoints")
    public ResponseEntity<?> findByReleaseDate(@RequestParam Timestamp releaseDate) {
        try {
            List<Game> games = gameService.findByReleaseDate(releaseDate);
            return ResponseEntity.ok(games);
        } catch (CustomException e) {
            return ResponseEntity.status(500).body(new ResponseDTO(e.getMessage()));
        }
    }

    @Operation(summary = "Get a list of games by description")
    @GetMapping("/find-by-description")
    @Tag(name = "GET Endpoints")
    public ResponseEntity<?> findByDescriptionContainingIgnoreCase(@RequestParam String description) {
        try {
            List<Game> games = gameService.findByDescriptionContainingIgnoreCase(description);
            return ResponseEntity.ok(games);
        } catch (CustomException e) {
            return ResponseEntity.status(500).body(new ResponseDTO(e.getMessage()));
        }
    }

    @Operation(summary = "Populate the database with some games")
    @PostMapping("/populate-database")
    @Tag(name = "POST Endpoints")
    public ResponseEntity<?> populateDatabase() {
        try {
            gameService.populateDatabase();
            return ResponseEntity.ok(new ResponseDTO("Database populated successfully!"));
        } catch (CustomException e) {
            return ResponseEntity.status(500).body(new ResponseDTO(e.getMessage()));
        }
    }
}