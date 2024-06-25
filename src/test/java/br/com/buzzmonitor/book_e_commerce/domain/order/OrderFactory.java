package br.com.buzzmonitor.book_e_commerce.domain.order;

import br.com.buzzmonitor.book_e_commerce.domain.Book;
import br.com.buzzmonitor.book_e_commerce.domain.Category;
import br.com.buzzmonitor.book_e_commerce.domain.Order;
import br.com.buzzmonitor.book_e_commerce.domain.OrderStatus;

import java.util.Date;
import java.util.UUID;

public class OrderFactory {
    public static final OrderFactory INSTANCE = new OrderFactory();

    private OrderFactory() {
    }

    public Order createOrder() {
        return new Order();
    }

    public Order createOrder(
            Long id,
            UUID uuid,
            Integer amount,
            Book book,
            OrderStatus status
    ) {
        return Order
                .builder()
                .id(id)
                .orderId(uuid)
                .amount(amount)
                .book(book)
                .status(status)
                .build();
    }

    public Order createOrder(
            Integer amount,
            Book book
    ) {
        return Order
                .builder()
                .amount(amount)
                .book(book)
                .build();
    }
}
