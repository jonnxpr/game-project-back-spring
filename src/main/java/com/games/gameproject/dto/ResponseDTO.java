package com.games.gameproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Response Data Transfer Object")
public class ResponseDTO {
    @Schema(description = "Message of the response", example = "Game saved successfully!")
    private String message;

    public ResponseDTO(String message) {
        this.message = message;
    }
}
