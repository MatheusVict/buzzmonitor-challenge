package br.com.buzzmonitor.book_e_commerce.mapper;

import br.com.buzzmonitor.book_e_commerce.domain.Order;
import br.com.buzzmonitor.book_e_commerce.dto.order.OrderRequestDTO;

public interface OrderMapper {
    Order toOrder(OrderRequestDTO orderRequestDTO);

    OrderRequestDTO toOrderRequestDTO(Order order);
}
