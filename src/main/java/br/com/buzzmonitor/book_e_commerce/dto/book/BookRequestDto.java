package br.com.buzzmonitor.book_e_commerce.dto.book;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


public record BookRequestDto(
        @NotBlank
        @Schema(description = "Book title", example = "The Lord of the Rings")
        String title,
        @NotBlank
        @Schema(description = "Book description", example = "The Lord of the Rings is an epic high-fantasy novel written by English author and scholar J. R. R. Tolkien.")
        String description,
        @NotBlank
        @Schema(description = "Book author", example = "J. R. R. Tolkien")
        String author,
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
        @Schema(description = "Book publication date", example = "01/01/1954")
        Date publicationDate,
        @NotNull
        @Schema(description = "Book quantity", example = "100")
        Integer stock,
        @NotNull
        @Schema(description = "Category uuid", example = "123e4567-e89b-12d3-a456-426614174000")
        UUID category_uuid
) {

}