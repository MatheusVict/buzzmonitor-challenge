package br.com.buzzmonitor.book_e_commerce.mapper.impl;

import br.com.buzzmonitor.book_e_commerce.domain.Category;
import br.com.buzzmonitor.book_e_commerce.dto.category.CategoryRequestDTO;
import br.com.buzzmonitor.book_e_commerce.mapper.CategoryMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapperImpl implements CategoryMapper {
    @Override
    public Category toCategory(CategoryRequestDTO categoryRequestDTO) {
        if (categoryRequestDTO == null)
            return null;

        return Category
                .builder()
                .name(categoryRequestDTO.name())
                .description(categoryRequestDTO.description())
                .build();
    }
}
