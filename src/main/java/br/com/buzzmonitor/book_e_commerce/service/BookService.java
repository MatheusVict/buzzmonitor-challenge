package br.com.buzzmonitor.book_e_commerce.service;

import br.com.buzzmonitor.book_e_commerce.domain.Book;
import br.com.buzzmonitor.book_e_commerce.dto.book.BookRequestDto;
import br.com.buzzmonitor.book_e_commerce.dto.book.BookResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface BookService {
    void create(BookRequestDto bookRequestDto);

    Page<BookResponseDTO> findAll(Pageable pageable);

    List<Book> getBooksByCategory(String categoryName);

    List<BookResponseDTO> getBooksByTitle(String title);

    Book getBookByUuid(UUID uuid);

    void update(UUID uuid, BookRequestDto bookRequestDto);

    void delete(UUID uuid);
}
