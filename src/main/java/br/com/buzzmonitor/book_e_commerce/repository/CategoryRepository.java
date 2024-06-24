package br.com.buzzmonitor.book_e_commerce.repository;

import br.com.buzzmonitor.book_e_commerce.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByUuid(UUID uuid);
}
