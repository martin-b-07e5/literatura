package com.alura.literatura.repository;

import com.alura.literatura.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILibroRepository extends JpaRepository<Libro, Long> {

  // Métodos adicionales personalizados si son necesarios
  // Método para verificar si el libro con el título ya existe
  boolean existsByTitle(String title);

}
