package br.com.buzzmonitor.book_e_commerce.mapper;

import br.com.buzzmonitor.book_e_commerce.domain.Category;
import br.com.buzzmonitor.book_e_commerce.dto.category.CategoryRequestDTO;

public interface CategoryMapper {

    Category toCategory(CategoryRequestDTO categoryRequestDTO);
}
