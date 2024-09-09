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

import com.games.gameproject.entities.Game;
import com.games.gameproject.service.GameService;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/find-all")
    public ResponseEntity<List<Game>> findAll() {
        List<Game> games = gameService.findAll();
        return ResponseEntity.ok(games);
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<Game> findById(@RequestParam Long id) {
        Game game = gameService.findById(id);
        return ResponseEntity.ok(game);
    }

    @PostMapping("/save")
    public ResponseEntity<Game> save(@RequestBody Game game) {
        Game savedGame = gameService.save(game);
        return ResponseEntity.ok(savedGame);
    }

    @DeleteMapping("/delete-by-id")
    public ResponseEntity<Void> deleteById(@RequestParam Long id) {
        gameService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/find-by-name")
    public ResponseEntity<List<Game>> findByNameContainingIgnoreCase(@RequestParam String name) {
        List<Game> games = gameService.findByNameContainingIgnoreCase(name);
        return ResponseEntity.ok(games);
    }

    @GetMapping("/find-by-genre")
    public ResponseEntity<List<Game>> findByGenreContainingIgnoreCase(@RequestParam String genre) {
        List<Game> games = gameService.findByGenreContainingIgnoreCase(genre);
        return ResponseEntity.ok(games);
    }

    @GetMapping("/find-by-platform")
    public ResponseEntity<List<Game>> findByPlatformContainingIgnoreCase(@RequestParam String platform) {
        List<Game> games = gameService.findByPlatformContainingIgnoreCase(platform);
        return ResponseEntity.ok(games);
    }

    @GetMapping("/find-by-company")
    public ResponseEntity<List<Game>> findByCompanyContainingIgnoreCase(@RequestParam String company) {
        List<Game> games = gameService.findByCompanyContainingIgnoreCase(company);
        return ResponseEntity.ok(games);
    }

    @GetMapping("/find-by-release-date")
    public ResponseEntity<List<Game>> findByReleaseDate(@RequestParam Timestamp releaseDate) {
        List<Game> games = gameService.findByReleaseDate(releaseDate);
        return ResponseEntity.ok(games);
    }

    @GetMapping("/find-by-description")
    public ResponseEntity<List<Game>> findByDescriptionContainingIgnoreCase(@RequestParam String description) {
        List<Game> games = gameService.findByDescriptionContainingIgnoreCase(description);
        return ResponseEntity.ok(games);
    }

    @PostMapping("/populate-database")
    public ResponseEntity<List<Game>> populateDatabase() {
        List<Game> games = gameService.populateDatabase();
        return ResponseEntity.ok(games);
    }
}