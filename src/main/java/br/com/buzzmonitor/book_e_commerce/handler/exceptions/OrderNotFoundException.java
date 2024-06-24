package br.com.buzzmonitor.book_e_commerce.handler.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
