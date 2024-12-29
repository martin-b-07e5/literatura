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

  @Autowired
  ILibroRepository ILibroRepository;

  @Autowired
  IAutorRepository IAutorRepository;


  public String saveBook(DatosLibros libroBuscado) {
    // Guardar autores relacionados
    List<Author> autoresGuardados = libroBuscado.authors().stream()
        .map(autor -> IAutorRepository.findByName(autor.getName())
            .orElseGet(() -> IAutorRepository.save(autor)))
        .toList();

    // Crear entidad Libro
    Libro libro = new Libro();
    libro.setTitle(libroBuscado.title());
    libro.setNumeroDeDescargas(libroBuscado.download_count().intValue());
    libro.setAuthor(autoresGuardados.get(0)); // Ejemplo: tomar el primer autor.

    // Guardar libro en la base de datos
    ILibroRepository.save(libro);

    return "Libro guardado exitosamente: " + libro.getTitle();
  }

}
