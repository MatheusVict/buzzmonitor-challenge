package br.com.buzzmonitor.book_e_commerce.dto.book;

import java.time.LocalDateTime;
import java.util.UUID;

public record BookResponseDTO(
        Long id,
        UUID uuid,
        String title,
        String description,
        String author,
        String publicationDate,
        Integer stock,
        String category,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
