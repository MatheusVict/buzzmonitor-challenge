package br.com.buzzmonitor.book_e_commerce.dto.order;

import java.util.UUID;

public record OrderRequestDTO(
        UUID bookId,
        Integer amount

) {
}
