package br.com.buzzmonitor.book_e_commerce.controller;

import br.com.buzzmonitor.book_e_commerce.domain.Book;
import br.com.buzzmonitor.book_e_commerce.dto.book.BookRequestDto;
import br.com.buzzmonitor.book_e_commerce.dto.book.BookResponseDTO;
import br.com.buzzmonitor.book_e_commerce.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<Page<BookResponseDTO>> getAllPageable(Pageable pageable) {
        return ResponseEntity.ok(this.bookService.findAll(pageable));
    }

    @GetMapping("/category")
    public ResponseEntity<List<Book>> getBooksByCategory(@RequestParam("name") String categoryName) {
        return ResponseEntity.ok(this.bookService.getBooksByCategory(categoryName));
    }


    @GetMapping("/title")
    public ResponseEntity<List<BookResponseDTO>> getBooksByTitle(@RequestParam("title") String title) {
        return ResponseEntity.ok(this.bookService.getBooksByTitle(title));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Book> getBookByUuid(@PathVariable UUID uuid) {
        return ResponseEntity.ok(this.bookService.getBookByUuid(uuid));
    }

    @PostMapping
    public ResponseEntity<Void> createBook(@RequestBody BookRequestDto bookRequestDto) {
        this.bookService.create(bookRequestDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Void> updateBook(@PathVariable UUID uuid, @RequestBody BookRequestDto bookRequestDto) {
        this.bookService.update(uuid, bookRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID uuid) {
        this.bookService.delete(uuid);
        return ResponseEntity.ok().build();
    }
}
