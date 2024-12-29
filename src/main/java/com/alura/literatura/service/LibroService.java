package com.alura.literatura.service;

import com.alura.literatura.entity.Author;
import com.alura.literatura.entity.DatosLibros;
import com.alura.literatura.entity.Libro;
import com.alura.literatura.repository.IAutorRepository;
import com.alura.literatura.repository.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

  // dependency injection
  @Autowired
  ILibroRepository iLibroRepository;
  @Autowired
  IAutorRepository iAutorRepository;

  public String saveBook(DatosLibros libroBuscado) {

    // Verificar si el libro ya existe en la base de datos por su título
    if (iLibroRepository.existsByTitle(libroBuscado.title())) {
      return "*** El libro \"" + libroBuscado.title() + "\" ya está registrado en la base de datos.***";
    }

    // Guardar autores relacionados (si no existen, se guardan)
    List<Author> autoresGuardados = libroBuscado.authors().stream()
        .map(autor -> iAutorRepository.findByName(autor.getName())
            .orElseGet(() -> iAutorRepository.save(autor)))
        .toList();

    // Verificar si hay autores encontrados
    if (autoresGuardados.isEmpty()) {
      return "No se encontró autor para el libro \"" + libroBuscado.title() + "\". \nNo se puede guardar el libro.";
    }

    // Crear entidad Libro
    Libro libro = new Libro();
    libro.setTitle(libroBuscado.title());
    libro.setNumeroDeDescargas(libroBuscado.download_count().intValue());
    libro.setAuthor(autoresGuardados.get(0)); // Ejemplo: tomar el primer autor.

    // Guardar libro en la base de datos
    iLibroRepository.save(libro);

    return "Libro guardado exitosamente: " + libro.getTitle();
  }

}
