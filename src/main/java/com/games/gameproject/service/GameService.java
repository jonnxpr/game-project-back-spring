package com.games.gameproject.service;

import java.sql.Timestamp;
import java.util.List;

import com.games.gameproject.entities.Game;

public interface GameService {
        List<Game> findAll();

        Game findById(Long id);

        Game save(Game game);

        void deleteById(List<Long> ids);

        void deleteAll();

        List<Game> findByNameContainingIgnoreCase(String name);

        List<Game> findByGenreContainingIgnoreCase(String genre);

        List<Game> findByPlatformContainingIgnoreCase(String platform);

        List<Game> findByCompanyContainingIgnoreCase(String company);

        List<Game> findByReleaseDate(Timestamp releaseDate);

        List<Game> findByDescriptionContainingIgnoreCase(String description);

        void populateDatabase();
}
