package br.com.buzzmonitor.book_e_commerce.service;

import br.com.buzzmonitor.book_e_commerce.dto.category.CategoryRequestDTO;
import br.com.buzzmonitor.book_e_commerce.dto.category.CategoryResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    void createCategory(CategoryRequestDTO categoryRequest);

    Page<CategoryResponseDTO> getCategories(Pageable pageable);

    CategoryResponseDTO getCategory(Long id);

    void updateCategory(Long id, CategoryRequestDTO categoryRequest);

    void deleteCategory(Long id);
}
