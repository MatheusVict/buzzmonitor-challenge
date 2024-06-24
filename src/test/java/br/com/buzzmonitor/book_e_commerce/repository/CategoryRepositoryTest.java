package br.com.buzzmonitor.book_e_commerce.repository;

import br.com.buzzmonitor.book_e_commerce.domain.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@DisplayName("Category Repository Test")
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @AfterEach
    void tearDown() {
        categoryRepository.deleteAll();
    }

    @Test
    @DisplayName("Find by UUID")
    void findByUuid() {

        UUID uuid = UUID.randomUUID();
        Category category = new Category();
        category.setUuid(uuid);
        category.setName("Category");
        category.setDescription("Category");
        categoryRepository.save(category);

        Optional<Category> optionalCategory = categoryRepository.findByUuid(uuid);

        assertTrue(optionalCategory.isPresent());
        assertEquals(uuid, optionalCategory.get().getUuid());
    }

}