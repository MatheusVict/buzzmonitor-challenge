package br.com.buzzmonitor.book_e_commerce.dto.category;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDTO(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Description is required")
        String description
) {
}
