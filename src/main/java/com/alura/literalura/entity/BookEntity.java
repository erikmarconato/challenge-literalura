package com.alura.literalura.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "livros")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
            name = "livros_autores",
            joinColumns = @JoinColumn(name = "livros_id"),
            inverseJoinColumns = @JoinColumn(name = "autores_id")
    )
    private List<AuthorEntity> authorEntity;

    private String language;

    private Integer downloadNumber;

    public BookEntity(Long id, String title, List<AuthorEntity> authorEntity, String language, Integer downloadNumber) {
        this.id = id;
        this.title = title;
        this.authorEntity = authorEntity;
        this.language = language;
        this.downloadNumber = downloadNumber;
    }

    public BookEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AuthorEntity> getAuthor() {
        return authorEntity;
    }

    public void setAuthor(List<AuthorEntity> authorEntity) {
        this.authorEntity = authorEntity;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getDownloadNumber() {
        return downloadNumber;
    }

    public void setDownloadNumber(Integer downloadNumber) {
        this.downloadNumber = downloadNumber;
    }
}
