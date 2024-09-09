package com.games.gameproject.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.games.gameproject.entities.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByNameContainingIgnoreCase(String name);

    List<Game> findByGenreContainingIgnoreCase(String genre);

    List<Game> findByPlatformContainingIgnoreCase(String platform);

    List<Game> findByCompanyContainingIgnoreCase(String company);

    List<Game> findByReleaseDate(Timestamp releaseDate);

    List<Game> findByDescriptionContainingIgnoreCase(String description);
}
