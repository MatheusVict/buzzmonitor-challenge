package br.com.buzzmonitor.book_e_commerce.service;

import br.com.buzzmonitor.book_e_commerce.domain.Category;
import br.com.buzzmonitor.book_e_commerce.dto.category.CategoryRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CategoryService {
    void createCategory(CategoryRequestDTO categoryRequest);

    Page<Category> getCategories(Pageable pageable);

    Category getCategory(UUID uuid);

    void updateCategory(UUID uuid, CategoryRequestDTO categoryRequest);

    void deleteCategory(UUID uuid);
}
