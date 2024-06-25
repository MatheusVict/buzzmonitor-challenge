package br.com.buzzmonitor.book_e_commerce.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record OrderRequestDTO(
        @NotBlank(message = "Book UUID is mandatory")
        @Schema(description = "Book UUID", example = "123e4567-e89b-12d3-a456-426614174000")
        UUID bookId,
        @NotBlank(message = "Amount is mandatory")
        @Schema(description = "Amount", example = "1")
        Integer amount

) {
}
