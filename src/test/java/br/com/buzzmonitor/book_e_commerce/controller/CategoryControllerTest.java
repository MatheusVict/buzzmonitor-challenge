package br.com.buzzmonitor.book_e_commerce.controller;

import br.com.buzzmonitor.book_e_commerce.domain.Category;
import br.com.buzzmonitor.book_e_commerce.domain.category.mocks.CategoryMocks;
import br.com.buzzmonitor.book_e_commerce.dto.category.CategoryRequestDTO;
import br.com.buzzmonitor.book_e_commerce.service.CategoryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Category Controller Test")
class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        List<Category> categories = List.of(CategoryMocks.returnValidCategoryToCreate());
        PageImpl<Category> categoryPages = new PageImpl<>(categories, Pageable.unpaged(), categories.size());

        when(categoryService.getCategories(any(Pageable.class))).thenReturn(categoryPages);
        when(categoryService.getCategory(any(UUID.class))).thenReturn(new Category());
        doNothing().when(categoryService).createCategory(any(CategoryRequestDTO.class));
        doNothing().when(categoryService).updateCategory(any(UUID.class), any(CategoryRequestDTO.class));
        doNothing().when(categoryService).deleteCategory(any(UUID.class));

    }

    @AfterEach
    void tearDown() {
        reset(categoryService);
    }

    @Test
    void getAllPageableCategory() {
        assertNotNull(categoryController.getAllPageableCategory(Pageable.unpaged()));
        verify(categoryService, times(1)).getCategories(any(Pageable.class));
    }

    @Test
    void getById() {
        Category category = categoryController.getById(UUID.randomUUID()).getBody();

        assertNotNull(category);
        verify(categoryService, times(1)).getCategory(any(UUID.class));
    }

    @Test
    void createCategory() {
        categoryController.createCategory(CategoryMocks.returnValidCategoryDTO());

        verify(categoryService, times(1)).createCategory(any(CategoryRequestDTO.class));
    }

    @Test
    void updateCategory() {
        categoryController.updateCategory(UUID.randomUUID(), CategoryMocks.returnValidCategoryDTO());

        verify(categoryService, times(1)).updateCategory(any(UUID.class), any(CategoryRequestDTO.class));
    }

    @Test
    void deleteCategory() {
        categoryController.deleteCategory(UUID.randomUUID());

        verify(categoryService, times(1)).deleteCategory(any(UUID.class));
    }
}