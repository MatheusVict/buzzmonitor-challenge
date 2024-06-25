package br.com.buzzmonitor.book_e_commerce.swagger.annotations;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static br.com.buzzmonitor.book_e_commerce.swagger.codes.SwaggerResponses.CODE_204;
import static br.com.buzzmonitor.book_e_commerce.swagger.codes.SwaggerResponses.RESPONSE_404;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponse(responseCode = CODE_204, description = RESPONSE_404,
        useReturnTypeSchema = true
)
public @interface NoContentResponse {
}