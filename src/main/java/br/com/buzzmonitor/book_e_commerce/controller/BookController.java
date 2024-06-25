package br.com.buzzmonitor.book_e_commerce.controller;

import br.com.buzzmonitor.book_e_commerce.domain.Book;
import br.com.buzzmonitor.book_e_commerce.dto.book.BookRequestDto;
import br.com.buzzmonitor.book_e_commerce.dto.book.BookResponseDTO;
import br.com.buzzmonitor.book_e_commerce.service.BookService;
import br.com.buzzmonitor.book_e_commerce.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/books")
@RequiredArgsConstructor
@Tag(name = "Book", description = "Book API")
public class BookController {
    private final BookService bookService;

    @GetMapping
    @Operation(summary = "Get all books pageable", description = "Get all books pageable")
    @OkResponse
    public ResponseEntity<Page<BookResponseDTO>> getAllPageable(Pageable pageable) {
        return ResponseEntity.ok(this.bookService.findAll(pageable));
    }

    @GetMapping("/category")
    @Operation(summary = "Get all books by its category", description = "Get all books by its category")
    @OkResponse
    public ResponseEntity<List<Book>> getBooksByCategory(@RequestParam("name") String categoryName) {
        return ResponseEntity.ok(this.bookService.getBooksByCategory(categoryName));
    }


    @GetMapping("/title")
    @Operation(summary = "Get all books by its title", description = "Get all books by its title")
    @OkResponse
    public ResponseEntity<List<BookResponseDTO>> getBooksByTitle(@RequestParam("title") String title) {
        return ResponseEntity.ok(this.bookService.getBooksByTitle(title));
    }

    @GetMapping("/{uuid}")
    @Operation(summary = "Get a book by its uuid", description = "Get a book by its uuid")
    @OkResponse
    @NotFoundResponse
    public ResponseEntity<Book> getBookByUuid(@PathVariable UUID uuid) {
        return ResponseEntity.ok(this.bookService.getBookByUuid(uuid));
    }

    @PostMapping
    @Operation(summary = "Create a book", description = "Create a book")
    @CreatedResponse
    @BadRequestResponse
    public ResponseEntity<Void> createBook(@RequestBody BookRequestDto bookRequestDto) {
        this.bookService.create(bookRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{uuid}")
    @Operation(summary = "Update a book", description = "Update a book")
    @NoContentResponse
    @BadRequestResponse
    @NotFoundResponse
    public ResponseEntity<Void> updateBook(@PathVariable UUID uuid, @RequestBody BookRequestDto bookRequestDto) {
        this.bookService.update(uuid, bookRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{uuid}")
    @Operation(summary = "Update a book", description = "Update a book")
    @NoContentResponse
    @BadRequestResponse
    @NotFoundResponse
    public ResponseEntity<Void> deleteBook(@PathVariable UUID uuid) {
        this.bookService.delete(uuid);
        return ResponseEntity.ok().build();
    }
}
