package br.com.buzzmonitor.book_e_commerce.mapper;

import br.com.buzzmonitor.book_e_commerce.domain.Book;
import br.com.buzzmonitor.book_e_commerce.dto.book.BookRequestDto;
import br.com.buzzmonitor.book_e_commerce.dto.book.BookResponseDTO;

public interface BookMapper {
    Book toEntity(BookRequestDto bookRequestDto);

    BookResponseDTO toDto(Book book);
    BookRequestDto toRequestDto(Book book);
}
