package br.com.buzzmonitor.book_e_commerce.service.impl;

import br.com.buzzmonitor.book_e_commerce.domain.Book;
import br.com.buzzmonitor.book_e_commerce.domain.Order;
import br.com.buzzmonitor.book_e_commerce.domain.books.mocks.BookMocks;
import br.com.buzzmonitor.book_e_commerce.domain.category.mocks.CategoryMocks;
import br.com.buzzmonitor.book_e_commerce.domain.order.mocks.OrderMocks;
import br.com.buzzmonitor.book_e_commerce.dto.book.BookRequestDto;
import br.com.buzzmonitor.book_e_commerce.mapper.BookMapper;
import br.com.buzzmonitor.book_e_commerce.mapper.OrderMapper;
import br.com.buzzmonitor.book_e_commerce.repository.OrderRepository;
import br.com.buzzmonitor.book_e_commerce.service.BookService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private BookService bookService;
    @Mock
    private OrderMapper orderMapper;
    @Mock
    private BookMapper bookMapper;

    private final Order orderValid = OrderMocks.returnValidOrder();

    @BeforeEach
    void setUp() {

        List<Order> ords = List.of(orderValid);
        Pageable pageable = PageRequest.of(0, 10);
        Page<Order> orderPage = new PageImpl<>(ords, pageable, ords.size());

        when(orderRepository.save(any())).thenReturn(orderValid);
        when(orderRepository.findByOrderId(any(UUID.class))).thenReturn(Optional.ofNullable(orderValid));
        when(orderRepository.findAll(any(Pageable.class))).thenReturn(orderPage);
        doNothing().when(orderRepository).delete(any(Order.class));

        when(orderMapper.toOrder(any())).thenReturn(orderValid);
        when(bookMapper.toRequestDto(any(Book.class))).thenReturn(BookMocks.returnValidBookRequestDto());
        when(bookService.getBookByUuid(any(UUID.class))).thenReturn(orderValid.getBook());
        doNothing().when(bookService).update(any(UUID.class), any(BookRequestDto.class));


    }

    @AfterEach
    void tearDown() {
        reset(orderRepository, bookService, orderMapper, bookMapper);
    }

    @Test
    @DisplayName("Should create a new order")
    void createOrder() {
        Order order = orderService.createOrder(OrderMocks.returnValidOrderDTO());
        assertNotNull(order);
        assertEquals(orderValid, order);
        assertEquals(order.getStatus(), orderValid.getStatus());
    }

    @Test
    @DisplayName("Should save as error status")
    void createOrderError() {
        Book bookReturned = BookMocks.returnValidBook(CategoryMocks.returnValidCategory());
        bookReturned.setStock(0);

        when(bookService.getBookByUuid(any(UUID.class))).thenReturn(bookReturned);
        when(orderRepository.save(any())).thenReturn(OrderMocks.returnInvalidOrder());

        Order order = orderService.createOrder(OrderMocks.returnValidOrderDTO());
        assertNotNull(order);
        assertEquals(order.getStatus(), orderValid.getStatus());
    }

    @Test
    void getOrderById() {
        Order order = orderService.getOrderById(orderValid.getOrderId());
        assertNotNull(order);
        assertEquals(orderValid, order);
    }

    @Test
    void getAllOrders() {
        Page<Order> orders = orderService.getAllOrders(PageRequest.of(0, 10));
        assertNotNull(orders);
        assertEquals(orders.getContent().size(), 1);
    }

    @Test
    void updateOrder() {
        Order order = orderService.createOrder(OrderMocks.returnValidOrderDTO());
        orderService.updateOrder(order.getOrderId(), OrderMocks.returnValidOrderDTO());

        assertNotNull(order);
        assertEquals(orderValid, order);
        assertEquals(order.getStatus(), orderValid.getStatus());
    }

    @Test
    void deleteOrder() {
        Order order = orderService.createOrder(OrderMocks.returnValidOrderDTO());
        orderService.deleteOrder(order.getOrderId());
        verify(orderRepository, times(1)).delete(any());
    }
}