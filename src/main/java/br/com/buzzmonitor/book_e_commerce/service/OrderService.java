package br.com.buzzmonitor.book_e_commerce.service;

import br.com.buzzmonitor.book_e_commerce.domain.Order;
import br.com.buzzmonitor.book_e_commerce.dto.order.OrderRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface OrderService {
    Order createOrder(OrderRequestDTO order);
    Order getOrderById(UUID uuid);
    Page<Order> getAllOrders(Pageable pageable);
    void updateOrder(UUID uuid, OrderRequestDTO order);
    void deleteOrder(UUID uuid);
}
