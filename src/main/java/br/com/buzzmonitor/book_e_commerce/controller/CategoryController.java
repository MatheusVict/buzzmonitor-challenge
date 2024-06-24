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

import java.util.UUID;

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

    @GetMapping("/{uuid}")
    public ResponseEntity<Category> getById(@PathVariable UUID uuid) {
        Category category = this.categoryService.getCategory(uuid);

        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity createCategory(@RequestBody CategoryRequestDTO categoryBody) {
        this.categoryService.createCategory(categoryBody);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{uuid}")
    public ResponseEntity updateCategory(@PathVariable UUID uuid, @RequestBody CategoryRequestDTO categoryBody) {
        this.categoryService.updateCategory(uuid, categoryBody);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity deleteCategory(@PathVariable UUID uuid) {
        this.categoryService.deleteCategory(uuid);

        return ResponseEntity.noContent().build();
    }
}
