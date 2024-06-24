package br.com.buzzmonitor.book_e_commerce.dto.book;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


public record BookRequestDto(
        @NotBlank
        String title,
        @NotBlank
        String description,
        @NotBlank
        String author,
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
        Date publicationDate,
        @NotNull
        Integer stock,
        @NotNull
        UUID category_uuid
) {

}