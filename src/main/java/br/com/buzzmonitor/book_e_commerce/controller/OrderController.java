package br.com.buzzmonitor.book_e_commerce.controller;

import br.com.buzzmonitor.book_e_commerce.domain.Order;
import br.com.buzzmonitor.book_e_commerce.dto.order.OrderRequestDTO;
import br.com.buzzmonitor.book_e_commerce.service.OrderService;
import br.com.buzzmonitor.book_e_commerce.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Order", description = "Order API")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    @Operation(summary = "Get all orders", description = "Get all orders")
    @OkResponse
    public ResponseEntity<Page<Order>> getOrders(Pageable pageable) {
        return ResponseEntity.ok(orderService.getAllOrders(pageable));
    }

    @GetMapping("/{uuid}")
    @Operation(summary = "Get a order by its uuid", description = "Get a order by its uuid")
    @OkResponse
    @NotFoundResponse
    public ResponseEntity<Order> getOrderById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(orderService.getOrderById(uuid));
    }

    @PostMapping
    @Operation(summary = "Create a order", description = "Create a order")
    @CreatedResponse
    @BadRequestResponse
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDTO order) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(order));
    }

    @PutMapping("/{uuid}")
    @Operation(summary = "update a order by its uuid", description = "Update a order by its uuid")
    @NoContentResponse
    @BadRequestResponse
    @NotFoundResponse
    public ResponseEntity<Order> updateOrder(@PathVariable UUID uuid, @RequestBody OrderRequestDTO order) {
        orderService.updateOrder(uuid, order);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{uuid}")
    @Operation(summary = "Create a order", description = "Create a order")
    @NoContentResponse
    @BadRequestResponse
    @NotFoundResponse
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID uuid) {
        orderService.deleteOrder(uuid);
        return ResponseEntity.noContent().build();
    }
}
