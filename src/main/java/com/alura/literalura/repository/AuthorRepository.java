package com.alura.literalura.repository;

import com.alura.literalura.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    @Query("""
    SELECT a
    FROM   AuthorEntity a
    WHERE  :year <= a.deathYear AND :year >= a.birthYear
""")
    List<AuthorEntity> findAllAliveAt(@Param("year") Integer year);
}
