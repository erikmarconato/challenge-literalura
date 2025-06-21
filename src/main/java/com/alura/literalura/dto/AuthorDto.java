package com.alura.literalura.dto;

import com.google.gson.annotations.SerializedName;

public record AuthorDto(

        @SerializedName("name")
        String name,

        @SerializedName("birth_year")
        Integer birthYear,

        @SerializedName("death_year")
        Integer deathYear
) {
}
