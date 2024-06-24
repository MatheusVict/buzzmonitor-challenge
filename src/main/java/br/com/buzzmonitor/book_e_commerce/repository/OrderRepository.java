package br.com.buzzmonitor.book_e_commerce.repository;

import br.com.buzzmonitor.book_e_commerce.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderId(UUID orderId);
}