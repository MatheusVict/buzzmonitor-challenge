package br.com.buzzmonitor.book_e_commerce.mapper.impl;

import br.com.buzzmonitor.book_e_commerce.domain.Book;
import br.com.buzzmonitor.book_e_commerce.dto.book.BookRequestDto;
import br.com.buzzmonitor.book_e_commerce.dto.book.BookResponseDTO;
import br.com.buzzmonitor.book_e_commerce.mapper.BookMapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapperImpl implements BookMapper {
    @Override
    public Book toEntity(BookRequestDto bookRequestDto) {
        if (bookRequestDto == null) {
            return null;
        }

        return Book
                .builder()
                .title(bookRequestDto.title())
                .description(bookRequestDto.description())
                .author(bookRequestDto.author())
                .publicationDate(bookRequestDto.publicationDate())
                .stock(bookRequestDto.stock())
                .build();
    }

    @Override
    public BookResponseDTO toDto(Book book) {
        if (book == null) {
            return null;
        }

        return new BookResponseDTO(
                book.getId(),
                book.getUuid(),
                book.getTitle(),
                book.getDescription(),
                book.getAuthor(),
                book.getPublicationDate().toString(),
                book.getStock(),
                book.getCategory().getName(),
                book.getCreatedAt(),
                book.getUpdatedAt()
        );
    }
}
