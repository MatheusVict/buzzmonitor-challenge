package br.com.buzzmonitor.book_e_commerce.domain.order.mocks;

import br.com.buzzmonitor.book_e_commerce.domain.Order;
import br.com.buzzmonitor.book_e_commerce.domain.OrderStatus;
import br.com.buzzmonitor.book_e_commerce.domain.books.mocks.BookMocks;
import br.com.buzzmonitor.book_e_commerce.domain.category.mocks.CategoryMocks;
import br.com.buzzmonitor.book_e_commerce.domain.order.OrderFactory;
import br.com.buzzmonitor.book_e_commerce.dto.order.OrderRequestDTO;
import com.github.javafaker.Faker;

import java.util.UUID;

public class OrderMocks {
    private static final Faker faker = new Faker();

    private static final Long id = faker.number().randomNumber();
    private static final UUID uuid = UUID.randomUUID();
    private static final Integer amount = faker.number().randomDigit();

    public static Order returnValidOrder() {
        return OrderFactory.
                INSTANCE.
                createOrder(
                        id,
                        uuid,
                        amount,
                        BookMocks.returnValidBook(CategoryMocks.returnValidCategory()),
                        OrderStatus.APPROVED
                );
    }

    public static Order returnValidOrderToCreate() {
        return OrderFactory.
                INSTANCE.
                createOrder(
                        amount,
                        BookMocks.returnValidBook(CategoryMocks.returnValidCategory())
                );
    }

    public static Order returnInvalidOrder() {
        return OrderFactory.
                INSTANCE.
                createOrder(
                        id,
                        uuid,
                        amount,
                        BookMocks.returnValidBook(CategoryMocks.returnValidCategory()),
                        OrderStatus.REJECTED
                );
    }

    public static OrderRequestDTO returnValidOrderDTO() {
        return new OrderRequestDTO(
                uuid,
                amount
        );
    }
}
