package br.com.buzzmonitor.book_e_commerce.controller;

import br.com.buzzmonitor.book_e_commerce.domain.Category;
import br.com.buzzmonitor.book_e_commerce.dto.category.CategoryRequestDTO;
import br.com.buzzmonitor.book_e_commerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Page<Category>> getAllPageableCategory(Pageable pageable) {
        Page<Category> categoriesPageable = this.categoryService.getCategories(pageable);

        return ResponseEntity.ok(categoriesPageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id) {
        Category category = this.categoryService.getCategory(id);

        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity createCategory(@RequestBody CategoryRequestDTO categoryBody) {
        this.categoryService.createCategory(categoryBody);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCategory(@PathVariable Long id, @RequestBody CategoryRequestDTO categoryBody) {
        this.categoryService.updateCategory(id, categoryBody);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable Long id) {
        this.categoryService.deleteCategory(id);

        return ResponseEntity.noContent().build();
    }
}
