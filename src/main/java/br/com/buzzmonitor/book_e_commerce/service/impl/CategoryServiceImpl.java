package br.com.buzzmonitor.book_e_commerce.service.impl;

import br.com.buzzmonitor.book_e_commerce.dto.category.CategoryRequestDTO;
import br.com.buzzmonitor.book_e_commerce.dto.category.CategoryResponseDTO;
import br.com.buzzmonitor.book_e_commerce.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Override
    public void createCategory(CategoryRequestDTO categoryRequest) {

    }

    @Override
    public Page<CategoryResponseDTO> getCategories(Pageable pageable) {
        return null;
    }

    @Override
    public CategoryResponseDTO getCategory(Long id) {
        return null;
    }

    @Override
    public void updateCategory(Long id, CategoryRequestDTO categoryRequest) {

    }

    @Override
    public void deleteCategory(Long id) {

    }
}
