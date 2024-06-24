package br.com.buzzmonitor.book_e_commerce.controller;

import br.com.buzzmonitor.book_e_commerce.domain.Order;
import br.com.buzzmonitor.book_e_commerce.domain.order.mocks.OrderMocks;
import br.com.buzzmonitor.book_e_commerce.dto.order.OrderRequestDTO;
import br.com.buzzmonitor.book_e_commerce.service.OrderService;
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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Order Controller Test")
class OrderControllerTest {

    @InjectMocks
    OrderController orderController;

    @Mock
    OrderService orderService;

    private final Order orderValid = OrderMocks.returnValidOrder();

    @BeforeEach
    void setUp() {
        List<Order> ords = List.of(orderValid);
        Pageable pageable = PageRequest.of(0, 10);
        Page<Order> orderPage = new PageImpl<>(ords, pageable, ords.size());

        when(orderService.getAllOrders(any(Pageable.class))).thenReturn(orderPage);
        when(orderService.getOrderById(any())).thenReturn(orderValid);
        when(orderService.createOrder(any())).thenReturn(orderValid);
        doNothing().when(orderService).updateOrder(any(UUID.class), any(OrderRequestDTO.class));
        doNothing().when(orderService).deleteOrder(any(UUID.class));
    }

    @AfterEach
    void tearDown() {
       reset(orderService);
    }

    @Test
    void getOrders() {
        Page<Order> orders = orderController.getOrders(PageRequest.of(0, 10)).getBody();
        assertNotNull(orders);
        assertEquals(1, orders.getTotalElements());
    }

    @Test
    void getOrderById() {
        Order order = orderController.getOrderById(orderValid.getOrderId()).getBody();
        assertNotNull(order);
        assertEquals(orderValid, order);
    }

    @Test
    void createOrder() {
        Order order = orderController.createOrder(OrderMocks.returnValidOrderDTO()).getBody();
        assertNotNull(order);
        assertEquals(orderValid, order);
    }

    @Test
    void updateOrder() {
        assertDoesNotThrow(() -> orderController.updateOrder(orderValid.getOrderId(), OrderMocks.returnValidOrderDTO()));
    }

    @Test
    void deleteOrder() {
        orderController.deleteOrder(orderValid.getOrderId());
        verify(orderService, times(1)).deleteOrder(any(UUID.class));
    }
}