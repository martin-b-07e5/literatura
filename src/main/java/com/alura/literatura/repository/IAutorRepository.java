package com.alura.literatura.repository;

import com.alura.literatura.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAutorRepository extends JpaRepository<Author, Long> {

  // Método personalizado para encontrar un autor por nombre
  Optional<Author> findByName(String name);


}
