package com.alura.literalura.service;

import com.alura.literalura.client.GutendexClient;
import com.alura.literalura.dto.AuthorDto;
import com.alura.literalura.dto.BookDto;
import com.alura.literalura.dto.BooksListDto;
import com.alura.literalura.dto.GutendexDto;
import com.alura.literalura.entity.AuthorEntity;
import com.alura.literalura.entity.BookEntity;
import com.alura.literalura.repository.BookRepository;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final GutendexClient gutendexClient;
    private final Gson gson;
    private final BookRepository bookRepository;

    public BookService(GutendexClient gutendexClient, Gson gson, BookRepository bookRepository) {
        this.gutendexClient = gutendexClient;
        this.gson = gson;
        this.bookRepository = bookRepository;
    }

    public BookDto saveData(String bookName) throws IOException, InterruptedException {
        var response = gutendexClient.ResponseApi(bookName);
        GutendexDto gutendexDto = gson.fromJson(response.body(), GutendexDto.class);

        if (gutendexDto.results() == null || gutendexDto.results().isEmpty()) {
            return null;
        }

        BookDto bookDto = gutendexDto.results().get(0);

        List<AuthorDto> authors = bookDto.authors();
        AuthorEntity authorEntity = null;

        if (authors != null && !authors.isEmpty()) {
            AuthorDto authorDto = authors.get(0);
            authorEntity = new AuthorEntity();
            authorEntity.setName(authorDto.name());
            authorEntity.setBirthYear(authorDto.birthYear());
            authorEntity.setDeathYear(authorDto.deathYear());
        }

        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(bookDto.title());
        if (authorEntity != null)
            bookEntity.setAuthor(List.of(authorEntity));
        else
            bookEntity.setAuthor(List.of());

        if (bookDto.languages() != null && !bookDto.languages().isEmpty()) {
            bookEntity.setLanguage(bookDto.languages().get(0));
        }

        bookEntity.setDownloadNumber(bookDto.downloadCount());

        bookRepository.save(bookEntity);

        return bookDto;
    }

    public List<BooksListDto> listAllBooks() {
        List<BookEntity> bookEntityList = bookRepository.findAll();

        return bookEntityList.stream().map(b ->
                new BooksListDto(
                        b.getTitle(),
                        b.getAuthor().stream()
                                .map(AuthorEntity::getName)
                                .collect(Collectors.joining(", ")),
                        b.getLanguage(),
                        b.getDownloadNumber()
                )).collect(Collectors.toList());
    }

    public List<BooksListDto> listAllBooksWithGivenLanguage (String language){
         List<BookEntity> bookEntityList = bookRepository.findByLanguage(language);

        return bookEntityList.stream().map(b ->
                new BooksListDto(
                        b.getTitle(),
                        b.getAuthor().stream()
                                .map(AuthorEntity::getName)
                                .collect(Collectors.joining(", ")),
                        b.getLanguage(),
                        b.getDownloadNumber()
                )).collect(Collectors.toList());
    }
}
