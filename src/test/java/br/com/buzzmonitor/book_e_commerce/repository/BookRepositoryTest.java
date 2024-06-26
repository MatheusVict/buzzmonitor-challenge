package br.com.buzzmonitor.book_e_commerce.repository;

import br.com.buzzmonitor.book_e_commerce.domain.Book;
import br.com.buzzmonitor.book_e_commerce.domain.Category;
import br.com.buzzmonitor.book_e_commerce.domain.books.mocks.BookMocks;
import br.com.buzzmonitor.book_e_commerce.domain.category.mocks.CategoryMocks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Tests for Book Repository")
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private final Category validCategory = CategoryMocks.returnValidCategory();
    private UUID uuid = UUID.randomUUID();
    private Category categorySaved;

    @BeforeEach
    void setUp() {

        Category categoryCreated = this.categoryRepository.save(validCategory);
        categorySaved = categoryCreated;
        Book book = BookMocks.returnValidBookToCreate(categoryCreated);


        Book bookSaved = bookRepository.save(book);
        uuid = bookSaved.getUuid();

    }

    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
    }

    @Test
    @DisplayName("Find all by category name, successfully")
    void findAllByCategoryName() {
        String categoryName = validCategory.getName();
        List<Book> allByCategoryName = bookRepository.findAllByCategoryName(categoryName);

        assertFalse(allByCategoryName.isEmpty());
        assertEquals(categoryName, allByCategoryName.get(0).getCategory().getName());
    }

    @Test
    @DisplayName("Find all by title, successfully")
    void findAllByTitle() {
        String title = BookMocks.returnValidBookToCreate().getTitle();
        List<Book> allByTitle = bookRepository.findAllByTitle(title);

        assertFalse(allByTitle.isEmpty());
        assertEquals(title, allByTitle.get(0).getTitle());
    }

    @Test
    @DisplayName("Find book by UUID, successfully")
    void findBookByUuid() {
        UUID uuid = this.uuid;
        Book book = BookMocks.returnValidBookToCreate(this.categorySaved);
        bookRepository.save(book);

        Book bookByUuid = bookRepository.findBookByUuid(uuid).orElse(null);

        assertNotNull(bookByUuid);
        assertEquals(uuid, bookByUuid.getUuid());
    }
}