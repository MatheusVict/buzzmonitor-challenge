package br.com.buzzmonitor.book_e_commerce.domain.category;

import br.com.buzzmonitor.book_e_commerce.domain.Category;
import br.com.buzzmonitor.book_e_commerce.dto.category.CategoryRequestDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public class CategoryEntityFactory {
    public static CategoryEntityFactory INSTANCE = new CategoryEntityFactory();

    private CategoryEntityFactory() {
    }

    public Category createCategory() {
        return new Category();
    }

    public Category createCategory(
            Long id,
            UUID uuid,
            String name,
            String description,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        return Category
                .builder()
                .id(id)
                .uuid(uuid)
                .name(name)
                .description(description)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

    public Category createCategory(
            UUID uuid,
            String name,
            String description
    ) {
        return Category
                .builder()
                .uuid(uuid)
                .name(name)
                .description(description)
                .build();
    }

    public CategoryRequestDTO createCategoryRequestDTO(
            String name,
            String description
    ) {
        return new CategoryRequestDTO(
                name,
                description
        );
    }
}
