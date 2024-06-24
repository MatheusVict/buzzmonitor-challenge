package br.com.buzzmonitor.book_e_commerce.controller;

import br.com.buzzmonitor.book_e_commerce.domain.Book;
import br.com.buzzmonitor.book_e_commerce.domain.books.mocks.BookMocks;
import br.com.buzzmonitor.book_e_commerce.domain.category.mocks.CategoryMocks;
import br.com.buzzmonitor.book_e_commerce.dto.book.BookRequestDto;
import br.com.buzzmonitor.book_e_commerce.dto.book.BookResponseDTO;
import br.com.buzzmonitor.book_e_commerce.service.BookService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @InjectMocks
    BookController bookController;

    @Mock
    BookService bookService;

    private final Book validBook = BookMocks.returnValidBook(CategoryMocks.returnValidCategory());


    @BeforeEach
    void setUp() {
        BookResponseDTO bookResponseDTO = BookMocks.returnValidBookResponseDTO(validBook.getCategory().getName());
        List<BookResponseDTO> books = List.of(bookResponseDTO);
        Pageable pageable = PageRequest.of(0, 10);
        Page<BookResponseDTO> categoryPage = new PageImpl<>(books, pageable, books.size());

        when(bookService.findAll(any(Pageable.class))).thenReturn(categoryPage);
        when(bookService.getBooksByCategory(any(String.class)))
                .thenReturn(List.of(validBook));
        when(bookService.getBooksByTitle(any(String.class))).thenReturn(List.of(bookResponseDTO));
        when(bookService.getBookByUuid(any(UUID.class))).thenReturn(new Book());
        doNothing().when(bookService).create(any(BookRequestDto.class));
        doNothing().when(bookService).update(any(UUID.class), any(BookRequestDto.class));
        doNothing().when(bookService).delete(any(UUID.class));
    }

    @AfterEach
    void tearDown() {
        reset(bookService);
    }

    @Test
    @DisplayName("Should return a page of books")
    void getAllPageable() {
        Page<BookResponseDTO> books = bookController.getAllPageable(PageRequest.of(0, 10).first()).getBody();
        assertNotNull(books);
        assertEquals(1, books.getTotalElements());
    }

    @Test
    void getBooksByCategory() {
        List<Book> books = bookController.getBooksByCategory(validBook.getCategory().getName()).getBody();
        assertNotNull(books);
        assertEquals(1, books.size());
    }

    @Test
    void getBooksByTitle() {
        List<BookResponseDTO> books = bookController.getBooksByTitle(validBook.getTitle()).getBody();
        assertNotNull(books);
        assertEquals(1, books.size());
    }

    @Test
    void getBookByUuid() {
        Book book = bookController.getBookByUuid(validBook.getUuid()).getBody();
        assertNotNull(book);
    }

    @Test
    void createBook() {
        BookRequestDto bookRequestDto = BookMocks.returnValidBookRequestDto();
        assertDoesNotThrow(() -> bookController.createBook(bookRequestDto));
    }

    @Test
    void updateBook() {
        BookRequestDto bookRequestDto = BookMocks.returnValidBookRequestDto();
        assertDoesNotThrow(() -> bookController.updateBook(validBook.getUuid(), bookRequestDto));
    }

    @Test
    void deleteBook() {
        assertDoesNotThrow(() -> bookController.deleteBook(validBook.getUuid()));
    }
}