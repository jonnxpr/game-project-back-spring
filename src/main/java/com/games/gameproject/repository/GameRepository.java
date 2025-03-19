package com.games.gameproject.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.games.gameproject.entities.Game;
import com.games.gameproject.exception.CustomException;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    /**
     * Find games by name containing the specified string, ignoring case.
     * 
     * @param name the name to search for
     * @return a list of games matching the name
     */
    List<Game> findByNameContainingIgnoreCase(String name);

    /**
     * Find games by genre containing the specified string, ignoring case.
     * 
     * @param genre the genre to search for
     * @return a list of games matching the genre
     */
    List<Game> findByGenreContainingIgnoreCase(String genre);

    /**
     * Find games by platform containing the specified string, ignoring case.
     * 
     * @param platform the platform to search for
     * @return a list of games matching the platform
     */
    List<Game> findByPlatformContainingIgnoreCase(String platform);

    /**
     * Find games by company containing the specified string, ignoring case.
     * 
     * @param company the company to search for
     * @return a list of games matching the company
     */
    List<Game> findByCompanyContainingIgnoreCase(String company);

    /**
     * Find games by release date.
     * 
     * @param releaseDate the release date to search for
     * @return a list of games matching the release date
     */
    List<Game> findByReleaseDate(Timestamp releaseDate);

    /**
     * Find games by description containing the specified string, ignoring case.
     * 
     * @param description the description to search for
     * @return a list of games matching the description
     */
    List<Game> findByDescriptionContainingIgnoreCase(String description);

    default Game findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new CustomException("Game not found with id: " + id));
    }
}
