package com.alura.literalura.service;

import com.alura.literalura.dto.AuthorsListDto;
import com.alura.literalura.entity.AuthorEntity;
import com.alura.literalura.entity.BookEntity;
import com.alura.literalura.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorsListDto> listAllAuthors() {

        return authorRepository.findAll().stream()
                .map(a -> new AuthorsListDto(
                        a.getName(),
                        a.getBirthYear(),
                        a.getDeathYear(),
                        a.getBooks().stream()
                                .map(BookEntity::getTitle)
                                .toList()
                ))
                .toList();
    }

    public List<AuthorsListDto> listLivingAuthors (Integer year){
        List<AuthorEntity> authorEntityList = authorRepository.findAllAliveAt(year);


        return authorEntityList.stream()
                .map(a -> new AuthorsListDto(
                        a.getName(),
                        a.getBirthYear(),
                        a.getDeathYear(),
                        a.getBooks().stream()
                                .map(BookEntity::getTitle)
                                .toList()
                ))
                .toList();
    }
}
