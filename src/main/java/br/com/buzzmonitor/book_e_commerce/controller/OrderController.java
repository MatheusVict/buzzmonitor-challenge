package br.com.buzzmonitor.book_e_commerce.controller;

import br.com.buzzmonitor.book_e_commerce.domain.Order;
import br.com.buzzmonitor.book_e_commerce.dto.order.OrderRequestDTO;
import br.com.buzzmonitor.book_e_commerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<Page<Order>> getOrders(Pageable pageable) {
        return ResponseEntity.ok(orderService.getAllOrders(pageable));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Order> getOrderById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(orderService.getOrderById(uuid));
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDTO order) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(order));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Order> updateOrder(@PathVariable UUID uuid, @RequestBody OrderRequestDTO order) {
        orderService.updateOrder(uuid, order);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID uuid) {
        orderService.deleteOrder(uuid);
        return ResponseEntity.noContent().build();
    }
}
