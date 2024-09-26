package com.games.gameproject.entities;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "game")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Represents a game entity")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the game", example = "1")
    private Long id;

    @Column(nullable = true, length = 250, unique = false)
    @Schema(description = "Name of the game", example = "The Legend of Zelda: Breath of the Wild")
    private String name;

    @Column(nullable = true, length = 250, unique = false)
    @Schema(description = "Genre of the game", example = "Action-adventure")
    private String genre;

    @Column(nullable = true, length = 250, unique = false)
    @Schema(description = "Platform where the game is available", example = "Nintendo Switch")
    private String platform;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(nullable = true, unique = false)
    @Schema(description = "Release date of the game", example = "03/03/2017")
    private Timestamp releaseDate;

    @Column(nullable = true, length = 250, unique = false)
    @Schema(description = "Company that developed the game", example = "Nintendo")
    private String company;

    @Column(nullable = true, length = 2000, unique = false)
    @Schema(description = "Description of the game", example = "The Legend of Zelda: Breath of the Wild is an action-adventure game developed and published by Nintendo.")
    private String description;
}
