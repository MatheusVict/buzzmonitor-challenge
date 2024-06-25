package br.com.buzzmonitor.book_e_commerce.controller;

import br.com.buzzmonitor.book_e_commerce.domain.Category;
import br.com.buzzmonitor.book_e_commerce.dto.category.CategoryRequestDTO;
import br.com.buzzmonitor.book_e_commerce.service.CategoryService;
import br.com.buzzmonitor.book_e_commerce.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Category", description = "Category API")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    @Operation(summary = "Get all categories", description = "Get all categories pageable")
    @OkResponse
    public ResponseEntity<Page<Category>> getAllPageableCategory(Pageable pageable) {
        Page<Category> categoriesPageable = this.categoryService.getCategories(pageable);

        return ResponseEntity.ok(categoriesPageable);
    }

    @GetMapping("/{uuid}")
    @Operation(summary = "Get a  category by id", description = "Get a category by id")
    @OkResponse
    @NotFoundResponse
    public ResponseEntity<Category> getById(@PathVariable UUID uuid) {
        Category category = this.categoryService.getCategory(uuid);

        return ResponseEntity.ok(category);
    }

    @PostMapping
    @Operation(summary = "Create a category", description = "Create a category")
    @CreatedResponse
    @BadRequestResponse
    public ResponseEntity createCategory(@RequestBody CategoryRequestDTO categoryBody) {
        this.categoryService.createCategory(categoryBody);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{uuid}")
    @Operation(summary = "Update a category", description = "Update a category")
    @NoContentResponse
    @BadRequestResponse
    @NotFoundResponse
    public ResponseEntity updateCategory(@PathVariable UUID uuid, @RequestBody CategoryRequestDTO categoryBody) {
        this.categoryService.updateCategory(uuid, categoryBody);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{uuid}")
    @Operation(summary = "delete a category", description = "delete a category")
    @NoContentResponse
    @NotFoundResponse
    public ResponseEntity deleteCategory(@PathVariable UUID uuid) {
        this.categoryService.deleteCategory(uuid);

        return ResponseEntity.noContent().build();
    }
}
