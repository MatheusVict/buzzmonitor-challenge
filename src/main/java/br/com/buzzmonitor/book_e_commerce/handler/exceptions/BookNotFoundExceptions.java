package br.com.buzzmonitor.book_e_commerce.handler.exceptions;

public class BookNotFoundExceptions extends RuntimeException {
    public BookNotFoundExceptions(String message) {
        super(message);
    }
}
