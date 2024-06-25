package br.com.buzzmonitor.book_e_commerce.domain.books.mocks;

import br.com.buzzmonitor.book_e_commerce.domain.Book;
import br.com.buzzmonitor.book_e_commerce.domain.Category;
import br.com.buzzmonitor.book_e_commerce.domain.books.BookEntityFactory;
import br.com.buzzmonitor.book_e_commerce.dto.book.BookRequestDto;
import br.com.buzzmonitor.book_e_commerce.dto.book.BookResponseDTO;
import com.github.javafaker.Faker;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class BookMocks {
    private static final Faker faker = new Faker();

    private static final Long id = faker.number().randomNumber();
    private static final UUID uuid = UUID.randomUUID();
    private static final String title = faker.lorem().word();
    private static final String description = faker.lorem().sentence();
    private static final String author = faker.lorem().word();
    private static final Integer stock = faker.number().randomDigit();
    private static final Date publicationDate = faker.date().birthday();

    public static Book returnValidBook(Category category) {
        return BookEntityFactory.
                INSTANCE.
                createBook(
                        id,
                        uuid,
                        title,
                        description,
                        author,
                        publicationDate,
                        stock,
                        category
                );
    }

    public static Book returnValidBookToCreate() {
        return BookEntityFactory.
                INSTANCE.
                createBook(
                        title,
                        description,
                        author,
                        publicationDate,
                        stock
                );
    }

    public static Book returnValidBookToCreate(Category category) {
        Book bookToCreate = BookEntityFactory.
                INSTANCE.
                createBook(
                        title,
                        description,
                        author,
                        publicationDate,
                        stock
                );
        bookToCreate.setCategory(category);
        return bookToCreate;
    }

    public static BookRequestDto returnValidBookRequestDto() {
        return new BookRequestDto(
                title,
                description,
                author,
                publicationDate,
                stock,
                uuid
        );
    }

    public static BookResponseDTO returnValidBookResponseDTO(String categoryName) {
        return new BookResponseDTO(
                id,
                uuid,
                title,
                description,
                author,
                publicationDate.toString(),
                stock,
                categoryName,
                LocalDateTime.now(),
                LocalDateTime.now()

        );
    }
}
