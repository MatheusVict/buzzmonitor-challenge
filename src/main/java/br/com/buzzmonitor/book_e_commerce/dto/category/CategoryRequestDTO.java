package br.com.buzzmonitor.book_e_commerce.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDTO(
        @NotBlank(message = "Name is required")
        @Schema(description = "Category name", example = "Fantasy")
        String name,

        @NotBlank(message = "Description is required")
        @Schema(description = "Category description", example = "Books that contain elements of fantasy")
        String description
) {
}
