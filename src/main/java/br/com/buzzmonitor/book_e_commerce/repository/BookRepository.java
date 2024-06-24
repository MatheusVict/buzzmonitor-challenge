package br.com.buzzmonitor.book_e_commerce.repository;

import br.com.buzzmonitor.book_e_commerce.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByCategoryName(String categoryName);

    @Query("SELECT b FROM book b WHERE b.title LIKE %?1%")
    List<Book> findAllByTitle(String title);

    Optional<Book> findBookByUuid(UUID uuid);
}