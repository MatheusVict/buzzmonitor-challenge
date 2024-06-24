package br.com.buzzmonitor.book_e_commerce.mapper.impl;

import br.com.buzzmonitor.book_e_commerce.domain.Order;
import br.com.buzzmonitor.book_e_commerce.dto.order.OrderRequestDTO;
import br.com.buzzmonitor.book_e_commerce.mapper.OrderMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderMapperImpl implements OrderMapper {
    @Override
    public Order toOrder(OrderRequestDTO orderRequestDTO) {
        return Order
                .builder()
                .amount(orderRequestDTO.amount())
                .build();
    }

    @Override
    public OrderRequestDTO toOrderRequestDTO(Order order) {
        return new OrderRequestDTO(
                order.getBook().getUuid(),
                order.getAmount()
        );
    }
}
