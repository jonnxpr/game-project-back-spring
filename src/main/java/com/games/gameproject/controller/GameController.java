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
import com.games.gameproject.service.GameService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/gamepedia")
public class GameController {

    @Autowired
    private GameService gameService;

    @Operation(summary = "Get a list of all games")
    @GetMapping("/find-all")
    @Tag(name = "GET Endpoints")
    public ResponseEntity<List<Game>> findAll() {
        List<Game> games = gameService.findAll();

        // sort games by name in ascending order and release date in descending order
        games.sort((game1, game2) -> {
            int compareName = game1.getName().compareTo(game2.getName());
            if (compareName != 0) {
                return compareName;
            } else {
                return game2.getReleaseDate().compareTo(game1.getReleaseDate());
            }
        });

        return ResponseEntity.ok(games);
    }

    @Operation(summary = "Get a game by its ID")
    @GetMapping("/find-by-id")
    @Tag(name = "GET Endpoints")
    public ResponseEntity<Game> findById(@RequestParam Long id) {
        Game game = gameService.findById(id);
        return ResponseEntity.ok(game);
    }

    @Operation(summary = "Save a game")
    @PostMapping("/save")
    @Tag(name = "POST Endpoints")
    public ResponseEntity<ResponseDTO> save(@RequestBody Game game) {
        gameService.save(game);
        if (game.getId() != null) {
            return ResponseEntity.ok(new ResponseDTO("Game updated successfully!"));
        }
        return ResponseEntity.ok(new ResponseDTO("Game saved successfully!"));
    }

    @Operation(summary = "Delete a game by its ID")
    @DeleteMapping("/delete-by-id")
    @Tag(name = "DELETE Endpoints")
    public ResponseEntity<ResponseDTO> deleteById(@RequestBody List<Long> ids) {
        gameService.deleteById(ids);

        if (ids.size() == 1) {
            return ResponseEntity.ok(new ResponseDTO("Game deleted successfully!"));
        } else {
            return ResponseEntity.ok(new ResponseDTO("Games deleted successfully!"));
        }
    }

    @Operation(summary = "Delete all games")
    @DeleteMapping("/delete-all")
    @Tag(name = "DELETE Endpoints")
    public ResponseEntity<ResponseDTO> deleteAll() {
        gameService.deleteAll();
        return ResponseEntity.ok(new ResponseDTO("Games deleted successfully!"));
    }

    @Operation(summary = "Get a list of games by name")
    @GetMapping("/find-by-name")
    @Tag(name = "GET Endpoints")
    public ResponseEntity<List<Game>> findByNameContainingIgnoreCase(@RequestParam String name) {
        List<Game> games = gameService.findByNameContainingIgnoreCase(name);
        return ResponseEntity.ok(games);
    }

    @Operation(summary = "Get a list of games by genre")
    @GetMapping("/find-by-genre")
    @Tag(name = "GET Endpoints")
    public ResponseEntity<List<Game>> findByGenreContainingIgnoreCase(@RequestParam String genre) {
        List<Game> games = gameService.findByGenreContainingIgnoreCase(genre);
        return ResponseEntity.ok(games);
    }

    @Operation(summary = "Get a list of games by platform")
    @GetMapping("/find-by-platform")
    @Tag(name = "GET Endpoints")
    public ResponseEntity<List<Game>> findByPlatformContainingIgnoreCase(@RequestParam String platform) {
        List<Game> games = gameService.findByPlatformContainingIgnoreCase(platform);
        return ResponseEntity.ok(games);
    }

    @Operation(summary = "Get a list of games by company")
    @GetMapping("/find-by-company")
    @Tag(name = "GET Endpoints")
    public ResponseEntity<List<Game>> findByCompanyContainingIgnoreCase(@RequestParam String company) {
        List<Game> games = gameService.findByCompanyContainingIgnoreCase(company);
        return ResponseEntity.ok(games);
    }

    @Operation(summary = "Get a list of games by release date")
    @GetMapping("/find-by-release-date")
    @Tag(name = "GET Endpoints")
    public ResponseEntity<List<Game>> findByReleaseDate(@RequestParam Timestamp releaseDate) {
        List<Game> games = gameService.findByReleaseDate(releaseDate);
        return ResponseEntity.ok(games);
    }

    @Operation(summary = "Get a list of games by description")
    @GetMapping("/find-by-description")
    @Tag(name = "GET Endpoints")
    public ResponseEntity<List<Game>> findByDescriptionContainingIgnoreCase(@RequestParam String description) {
        List<Game> games = gameService.findByDescriptionContainingIgnoreCase(description);
        return ResponseEntity.ok(games);
    }

    @Operation(summary = "Populate the database with some games")
    @PostMapping("/populate-database")
    @Tag(name = "POST Endpoints")
    public ResponseEntity<ResponseDTO> populateDatabase() {
        gameService.populateDatabase();
        return ResponseEntity.ok(new ResponseDTO("Database populated successfully!"));
    }
}