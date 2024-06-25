package br.com.buzzmonitor.book_e_commerce.swagger.annotations;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static br.com.buzzmonitor.book_e_commerce.swagger.codes.SwaggerResponses.CODE_200;
import static br.com.buzzmonitor.book_e_commerce.swagger.codes.SwaggerResponses.RESPONSE_200;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponse(responseCode = CODE_200, description = RESPONSE_200, useReturnTypeSchema = true)
public @interface OkResponse {
}
