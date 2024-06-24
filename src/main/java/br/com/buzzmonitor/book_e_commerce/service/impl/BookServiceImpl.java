package br.com.buzzmonitor.book_e_commerce.service.impl;

import br.com.buzzmonitor.book_e_commerce.domain.Book;
import br.com.buzzmonitor.book_e_commerce.domain.Category;
import br.com.buzzmonitor.book_e_commerce.dto.book.BookRequestDto;
import br.com.buzzmonitor.book_e_commerce.dto.book.BookResponseDTO;
import br.com.buzzmonitor.book_e_commerce.mapper.BookMapper;
import br.com.buzzmonitor.book_e_commerce.repository.BookRepository;
import br.com.buzzmonitor.book_e_commerce.service.BookService;
import br.com.buzzmonitor.book_e_commerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryService categoryService;
    private final BookMapper bookMapper;

    @Transactional
    @Override
    public void create(BookRequestDto bookRequestDto) {
        UUID categoryUuid = bookRequestDto.category_uuid();

        Category bookCategory = this.categoryService.getCategory(categoryUuid);

        Book book = bookMapper.toEntity(bookRequestDto);
        book.setCategory(bookCategory);

        this.bookRepository.save(book);
    }

    @Override
    public Page<BookResponseDTO> findAll(Pageable pageable) {
        Page<Book> allBooks = this.bookRepository.findAll(pageable);

        return allBooks.map(bookMapper::toDto);
    }

    @Override
    public List<Book> getBooksByCategory(String categoryName) {
        return this.bookRepository.findAllByCategoryName(categoryName);
    }

    @Override
    public List<BookResponseDTO> getBooksByTitle(String title) {
        List<Book> allByTitle = this.bookRepository.findAllByTitle(title);

        return allByTitle.stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public Book getBookByUuid(UUID uuid) {
        return this.bookRepository.findBookByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Transactional
    @Override
    public void update(UUID uuid, BookRequestDto bookRequestDto) {
        Book bookToUpdate = this.getBookByUuid(uuid);

        UUID categoryUuid = bookRequestDto.category_uuid();
        Category categoryFound = this.categoryService.getCategory(categoryUuid);

        BeanUtils.copyProperties(bookRequestDto, bookToUpdate);
        bookToUpdate.setCategory(categoryFound);

        this.bookRepository.save(bookToUpdate);
    }


    @Override
    public void delete(UUID uuid) {
        Book bookFound = this.getBookByUuid(uuid);

        this.bookRepository.delete(bookFound);
    }
}
