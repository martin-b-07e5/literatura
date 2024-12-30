package com.alura.literatura.repository;

import com.alura.literatura.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAutorRepository extends JpaRepository<Author, Long> {

  // Método personalizado para encontrar un autor por nombre
  Optional<Author> findByName(String name);

  // case 4
  @Query("SELECT a FROM Author a WHERE a.birth_year <= :year AND (a.death_year IS NULL OR a.death_year >= :year)")
  List<Author> findAutoresVivosDurante(@Param("year") int year);

}
