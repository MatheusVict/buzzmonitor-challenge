package br.com.buzzmonitor.book_e_commerce.handler;

import br.com.buzzmonitor.book_e_commerce.handler.exceptions.BookNotFoundExceptions;
import br.com.buzzmonitor.book_e_commerce.handler.exceptions.CategoryNotFoundException;
import br.com.buzzmonitor.book_e_commerce.handler.exceptions.OrderNotFoundException;
import br.com.buzzmonitor.book_e_commerce.handler.exceptions.pattern.ExceptionGlobalPattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerHandler {


    @ExceptionHandler(BookNotFoundExceptions.class)
    public ResponseEntity<ExceptionGlobalPattern> handlerBookNotFoundException(BookNotFoundExceptions exception) {
        LocalDateTime timestamp = LocalDateTime.now();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ExceptionGlobalPattern.builder()
                        .title("Book Not Found Exception")
                        .status(HttpStatus.NOT_FOUND.value())
                        .details(exception.getMessage())
                        .timestamp(timestamp)
                        .developerMessage(exception.getClass().getName())
                        .build()
        );
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ExceptionGlobalPattern> handlerCategoryNotFoundException(BookNotFoundExceptions exception) {
        LocalDateTime timestamp = LocalDateTime.now();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ExceptionGlobalPattern.builder()
                        .title("Category Not Found Exception")
                        .status(HttpStatus.NOT_FOUND.value())
                        .details(exception.getMessage())
                        .timestamp(timestamp)
                        .developerMessage(exception.getClass().getName())
                        .build()
        );
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ExceptionGlobalPattern> handlerOrderNotFoundException(OrderNotFoundException exception) {
        LocalDateTime timestamp = LocalDateTime.now();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ExceptionGlobalPattern.builder()
                        .title("Order Not Found Exception")
                        .status(HttpStatus.NOT_FOUND.value())
                        .details(exception.getMessage())
                        .timestamp(timestamp)
                        .developerMessage(exception.getClass().getName())
                        .build()
        );
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionGlobalPattern> handlerMethodArgumentNotValidExceptionException(MethodArgumentNotValidException exception) {
        List<FieldError> fieldError = exception.getBindingResult().getFieldErrors();

        String fields = fieldError.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldMessage = fieldError.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
        LocalDateTime timestamp = LocalDateTime.now();

        return ResponseEntity.badRequest().body(
                ExceptionGlobalPattern.builder()
                        .title("Bad Request Exception, Invalid Fields")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .details("Check the field(s) error")
                        .timestamp(timestamp)
                        .developerMessage(exception.getClass().getName())
                        .fields(fields)
                        .fieldsMessage(fieldMessage)
                        .build()
        );
    }
}
