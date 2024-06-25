package br.com.buzzmonitor.book_e_commerce.swagger.annotations;

import br.com.buzzmonitor.book_e_commerce.handler.exceptions.pattern.ExceptionGlobalPattern;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static br.com.buzzmonitor.book_e_commerce.swagger.codes.SwaggerResponses.CODE_404;
import static br.com.buzzmonitor.book_e_commerce.swagger.codes.SwaggerResponses.RESPONSE_404;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponse(responseCode = CODE_404, description = RESPONSE_404,
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionGlobalPattern.class)))

public @interface NotFoundResponse {
}