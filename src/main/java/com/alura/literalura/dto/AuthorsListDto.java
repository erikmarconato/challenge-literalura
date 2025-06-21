package com.alura.literalura.dto;

import com.alura.literalura.entity.BookEntity;

import java.util.List;

public record AuthorsListDto(
        String name,

        Integer birthYear,

        Integer deathYear,

        List<String> book
) {
}
