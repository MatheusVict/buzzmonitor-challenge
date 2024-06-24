package br.com.buzzmonitor.book_e_commerce.domain.category.mocks;

import br.com.buzzmonitor.book_e_commerce.domain.Category;
import br.com.buzzmonitor.book_e_commerce.domain.category.CategoryEntityFactory;
import br.com.buzzmonitor.book_e_commerce.dto.category.CategoryRequestDTO;
import com.github.javafaker.Faker;

import java.time.LocalDateTime;
import java.util.UUID;

public class CategoryMocks {
    private static final Faker faker = new Faker();

    private static final Long id = faker.number().randomNumber();
    private static final UUID uuid = UUID.randomUUID();
    private static final String name = faker.lorem().word();
    private static final String description = faker.lorem().sentence();
    private static final LocalDateTime createdAt = LocalDateTime.now();
    private static final LocalDateTime updatedAt = LocalDateTime.now();

    public static Category returnValidCategory() {
        return CategoryEntityFactory.
                INSTANCE.
                createCategory(
                        id,
                        uuid,
                        name,
                        description,
                        createdAt,
                        updatedAt
                );
    }

    public static Category returnValidCategoryToCreate() {
        return CategoryEntityFactory.
                INSTANCE.
                createCategory(
                        uuid,
                        name,
                        description
                );
    }

    public static CategoryRequestDTO returnValidCategoryDTO() {
        return CategoryEntityFactory.
                INSTANCE.
                createCategoryRequestDTO(
                        name,
                        description
                );
    }
}
