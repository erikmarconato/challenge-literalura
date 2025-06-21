package com.alura.literalura.dto;

public record BooksListDto(
        String title,
        String author,
        String language,
        Integer downloadNumber
) {
}
