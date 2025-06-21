package com.alura.literalura.repository;

import com.alura.literalura.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long>{

    @Query("""
   SELECT b
   FROM   BookEntity b
   WHERE  b.language = :language
""")
    List<BookEntity> findByLanguage(@Param("language") String language);
}
