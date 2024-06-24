package br.com.buzzmonitor.book_e_commerce.service.impl;

import br.com.buzzmonitor.book_e_commerce.domain.Category;
import br.com.buzzmonitor.book_e_commerce.dto.category.CategoryRequestDTO;
import br.com.buzzmonitor.book_e_commerce.dto.category.CategoryResponseDTO;
import br.com.buzzmonitor.book_e_commerce.mapper.CategoryMapper;
import br.com.buzzmonitor.book_e_commerce.repository.CategoryRepository;
import br.com.buzzmonitor.book_e_commerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public void createCategory(CategoryRequestDTO categoryRequest) {
        Category categoryToSave = categoryMapper.toCategory(categoryRequest);

        this.categoryRepository.save(categoryToSave);
    }

    @Override
    public Page<Category> getCategories(Pageable pageable) {
        return this.categoryRepository.findAll(pageable);
    }

    @Override
    public Category getCategory(Long id) {
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category Not Found"));
    }

    @Transactional
    @Override
    public void updateCategory(Long id, CategoryRequestDTO categoryRequest) {
        Category categoryFound = this.getCategory(id);

        Category categoryToUpdate = categoryMapper.toCategory(categoryRequest);

        categoryToUpdate.setId(categoryFound.getId());
        categoryToUpdate.setCreatedAt(categoryFound.getCreatedAt());
        BeanUtils.copyProperties(categoryToUpdate, categoryFound);

        this.categoryRepository.save(categoryFound);
    }

    @Override
    public void deleteCategory(Long id) {
        Category categoryToDelete = this.getCategory(id);

        this.categoryRepository.delete(categoryToDelete);
    }
}
