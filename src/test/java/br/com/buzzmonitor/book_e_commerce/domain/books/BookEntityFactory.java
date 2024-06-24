package br.com.buzzmonitor.book_e_commerce.domain.books;

import br.com.buzzmonitor.book_e_commerce.domain.Book;
import br.com.buzzmonitor.book_e_commerce.domain.Category;

import java.util.Date;
import java.util.UUID;

public class BookEntityFactory {
    public static final BookEntityFactory INSTANCE = new BookEntityFactory();

    private BookEntityFactory() {
    }

    public Book createBook() {
        return new Book();
    }

    public Book createBook(
            Long id,
            UUID uuid,
            String title,
            String description,
            String author,
            Date publicationDate,
            Integer stock,
            Category category
    ) {
        return Book
                .builder()
                .id(id)
                .uuid(uuid)
                .title(title)
                .description(description)
                .author(author)
                .publicationDate(publicationDate)
                .stock(stock)
                .category(category)
                .build();
    }

    public Book createBook(
            String title,
            String description,
            String author,
            Date publicationDate,
            Integer stock
    ) {
        return Book
                .builder()
                .title(title)
                .description(description)
                .author(author)
                .publicationDate(publicationDate)
                .stock(stock)
                .build();
    }

}
