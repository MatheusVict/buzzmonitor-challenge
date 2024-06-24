package br.com.buzzmonitor.book_e_commerce.service.impl;

import br.com.buzzmonitor.book_e_commerce.domain.Category;
import br.com.buzzmonitor.book_e_commerce.domain.category.mocks.CategoryMocks;
import br.com.buzzmonitor.book_e_commerce.dto.category.CategoryRequestDTO;
import br.com.buzzmonitor.book_e_commerce.mapper.CategoryMapper;
import br.com.buzzmonitor.book_e_commerce.repository.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Category Service Test")
class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    private final Category validCategory = CategoryMocks.returnValidCategory();

    @BeforeEach
    void setUp() {
        List<Category> categories = List.of(validCategory);
        Pageable pageable = PageRequest.of(0, 10);
        Page<Category> categoryPage = new PageImpl<>(categories, pageable, categories.size());

        when(categoryRepository.findAll(any(Pageable.class)))
                .thenReturn(categoryPage);

        when(categoryRepository.findByUuid(any(UUID.class)))
                .thenReturn(Optional.ofNullable(validCategory));
        when(categoryMapper.toCategory(any(CategoryRequestDTO.class)))
                .thenReturn(validCategory);

        when(categoryRepository.save(any(Category.class)))
                .thenReturn(validCategory);
    }

    @AfterEach
    void tearDown() {
        reset(categoryRepository, categoryMapper);
    }

    @Test
    @DisplayName("Should create a category, successfully")
    void createCategory() {
        CategoryRequestDTO categoryToCreate = CategoryMocks.returnValidCategoryDTO();

        assertDoesNotThrow(() -> categoryService.createCategory(categoryToCreate));
    }

    @Test
    @DisplayName("Should return a list of categories, successfully")
    void getCategories() {
        Page<Category> categories = this.categoryService.getCategories(PageRequest.of(0, 10));

        assertFalse(categories.isEmpty());
        assertNotNull(categories.getContent().get(0));
        assertEquals(validCategory.getUuid(), categories.getContent().get(0).getUuid());
    }

    @Test
    @DisplayName("Should return a category by uuid, successfully")
    void getCategory() {
        UUID uuidExpected = validCategory.getUuid();

        Category category = categoryService.getCategory(uuidExpected);

        assertEquals(uuidExpected, category.getUuid());
    }

    @Test
    @DisplayName("Should update a category, successfully")
    void updateCategory() {
        UUID uuidExpected = validCategory.getUuid();
        CategoryRequestDTO categoryToUpdate = CategoryMocks.returnValidCategoryDTO();

        assertDoesNotThrow(() -> categoryService.updateCategory(uuidExpected, categoryToUpdate));
    }

    @Test
    @DisplayName("Should delete a category, successfully")
    void deleteCategory() {
        UUID uuidExpected = validCategory.getUuid();

        assertDoesNotThrow(() -> categoryService.deleteCategory(uuidExpected));
    }
}