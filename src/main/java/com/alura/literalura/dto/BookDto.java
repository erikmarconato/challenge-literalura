package com.alura.literalura.dto;

import com.alura.literalura.entity.AuthorEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public record BookDto(

        String title,

        List<AuthorDto> authors,

        List<String> languages,

        @SerializedName("download_count")
        Integer downloadCount) {
}
