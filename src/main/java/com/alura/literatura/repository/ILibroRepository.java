package com.alura.literatura.repository;

import com.alura.literatura.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILibroRepository extends JpaRepository<Libro, Long> {

  // Métodos adicionales personalizados si son necesarios
  // Método para verificar si el libro con el título ya existe
  boolean existsByTitle(String title);

  // Método para encontrar todos los libros por idioma
  List<Libro> findByLanguages(String languages);

}
