package br.com.buzzmonitor.book_e_commerce.dto.category;

public record CategoryResponseDTO(
        Long id,
        String name,
        String description,
        String createdAt,
        String updatedAt
) {
}
