package br.com.buzzmonitor.book_e_commerce.service.impl;

import br.com.buzzmonitor.book_e_commerce.domain.Book;
import br.com.buzzmonitor.book_e_commerce.domain.Order;
import br.com.buzzmonitor.book_e_commerce.domain.OrderRepository;
import br.com.buzzmonitor.book_e_commerce.domain.OrderStatus;
import br.com.buzzmonitor.book_e_commerce.dto.order.OrderRequestDTO;
import br.com.buzzmonitor.book_e_commerce.mapper.BookMapper;
import br.com.buzzmonitor.book_e_commerce.mapper.OrderMapper;
import br.com.buzzmonitor.book_e_commerce.service.BookService;
import br.com.buzzmonitor.book_e_commerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final BookService bookService;
    private final OrderMapper orderMapper;
    private final BookMapper bookMapper;

    @Override
    public Order createOrder(OrderRequestDTO order) {
        Book bookByUuid = this.getBookByUuid(order.bookId());
        Order orderToSave = orderMapper.toOrder(order);
        orderToSave.setBook(bookByUuid);

        if (verifyBookAvailability(order.bookId(), order.amount())) {
            orderToSave.setStatus(OrderStatus.APPROVED);
            bookByUuid.setStock(bookByUuid.getStock() - order.amount());
            bookService.update(bookByUuid.getUuid(), bookMapper.toRequestDto(bookByUuid));
        } else {
            orderToSave.setStatus(OrderStatus.REJECTED);
        }
        return orderRepository.save(orderToSave);
    }

    @Override
    public Order getOrderById(UUID uuid) {
        return orderRepository.findByOrderId(uuid)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public Page<Order> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public void updateOrder(UUID uuid, OrderRequestDTO order) {
        Order orderToUpdate = this.getOrderById(uuid);
        Book bookByUuid = this.getBookByUuid(order.bookId());

        orderToUpdate.setAmount(order.amount());
        orderToUpdate.setBook(bookByUuid);

        if (verifyBookAvailability(order.bookId(), order.amount())) {
            bookByUuid.setStock(bookByUuid.getStock() - order.amount());
            orderToUpdate.setStatus(OrderStatus.APPROVED);
        } else {
            orderToUpdate.setStatus(OrderStatus.REJECTED);
        }

        this.orderRepository.save(orderToUpdate);

    }

    @Override
    public void deleteOrder(UUID uuid) {
        Order order = this.getOrderById(uuid);
        orderRepository.delete(order);

    }

    private boolean verifyBookAvailability(UUID bookId, Integer amount) {
        return bookService.getBookByUuid(bookId).getStock() >= amount;
    }

    private Book getBookByUuid(UUID bookId) {
        return bookService.getBookByUuid(bookId);
    }
}
