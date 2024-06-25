package br.com.buzzmonitor.book_e_commerce.service.impl;

import br.com.buzzmonitor.book_e_commerce.domain.Book;
import br.com.buzzmonitor.book_e_commerce.domain.Category;
import br.com.buzzmonitor.book_e_commerce.domain.books.mocks.BookMocks;
import br.com.buzzmonitor.book_e_commerce.domain.category.mocks.CategoryMocks;
import br.com.buzzmonitor.book_e_commerce.dto.book.BookRequestDto;
import br.com.buzzmonitor.book_e_commerce.dto.book.BookResponseDTO;
import br.com.buzzmonitor.book_e_commerce.mapper.BookMapper;
import br.com.buzzmonitor.book_e_commerce.repository.BookRepository;
import br.com.buzzmonitor.book_e_commerce.service.CategoryService;
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
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests for BookServiceImpl")
class BookServiceImplTest {

    @InjectMocks
    BookServiceImpl bookService;

    @Mock
    BookRepository bookRepository;

    @Mock
    CategoryService categoryService;

    @Mock
    BookMapper bookMapper;

    private final  Book validBook = BookMocks.returnValidBook(CategoryMocks.returnValidCategory());


    @BeforeEach
    void setUp() {

        List<Book> books = List.of(validBook);
        Pageable pageable = PageRequest.of(0, 10);
        Page<Book> categoryPage = new PageImpl<>(books, pageable, books.size());

        when(bookMapper.toEntity(any())).thenReturn(validBook);
        when(bookRepository.save(any(Book.class)))
                .thenReturn(validBook);
        when(bookRepository.findAll(any(Pageable.class))).thenReturn(categoryPage);
        when(bookRepository.findAllByCategoryName(any())).thenReturn(List.of(validBook));
        when(bookRepository.findAllByTitle(any())).thenReturn(List.of(validBook));

        when(bookRepository.findBookByUuid(any(UUID.class)))
                .thenReturn(Optional.ofNullable(validBook));
    }

    @AfterEach
    void tearDown() {
        reset(bookRepository, categoryService, bookMapper);
    }

    @Test
    @DisplayName("Create a book when successful")
    void create() {
        BookRequestDto bookToCreate = BookMocks.returnValidBookRequestDto();
        assertDoesNotThrow(() -> bookService.create(bookToCreate));
    }

    @Test
    @DisplayName("Find all books when successful")
    void findAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<BookResponseDTO> allBooks = bookService.findAll(pageable);
        assertNotNull(allBooks);
        assertEquals(allBooks.getContent().size(), 1);
    }

    @Test
    @DisplayName("Get books by category when successful")
    void getBooksByCategory() {
        List<Book> books = bookService.getBooksByCategory(CategoryMocks.returnValidCategory().getName());
        assertNotNull(books);
        assertFalse(books.isEmpty());
    }

    @Test
    @DisplayName("Get books by title when successful")
    void getBooksByTitle() {
        List<BookResponseDTO> books = bookService.getBooksByTitle(validBook.getTitle());
        assertNotNull(books);
        assertFalse(books.isEmpty());
    }

    @Test
    @DisplayName("Get book by uuid when successful")
    void getBookByUuid() {
        Book book = bookService.getBookByUuid(validBook.getUuid());
        assertNotNull(book);
        assertEquals(validBook.getUuid(), book.getUuid());
    }

    @Test
    @DisplayName("Update book when successful")
    void update() {
        BookRequestDto bookToUpdate = BookMocks.returnValidBookRequestDto();
        assertDoesNotThrow(() -> bookService.update(validBook.getUuid(), bookToUpdate));
    }

    @Test
    @DisplayName("Delete book when successful")
    void delete() {
        assertDoesNotThrow(() -> bookService.delete(validBook.getUuid()));
    }
}