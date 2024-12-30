package com.alura.literatura.service;

import com.alura.literatura.entity.Author;
import com.alura.literatura.entity.DatosLibros;
import com.alura.literatura.entity.Libro;
import com.alura.literatura.repository.IAutorRepository;
import com.alura.literatura.repository.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class that handles the logic for saving and managing books in the system.
 */
@Service
public class LibroService {

  // Dependency Injection for repository interfaces
  @Autowired
  ILibroRepository iLibroRepository;

  @Autowired
  IAutorRepository iAutorRepository;


  /**
   * Saves a book to the database if it doesn't already exist.
   * If the book is already in the database, it returns a message stating that the book already exists.
   * Also saves the authors of the book if they are not already in the database.
   *
   * @param libroBuscado The book data to be saved.
   * @return A message indicating whether the book was saved or already exists.
   */
  public String saveBook(DatosLibros libroBuscado) {

    // Check if the book already exists in the database by its title
    if (iLibroRepository.existsByTitle(libroBuscado.title())) {
      return "*** The book \"" + libroBuscado.title() + "\" is already registered in the database. ***";
    }

    // Save related authors (if they don't exist, they are saved)
    List<Author> autoresGuardados = libroBuscado.authors().stream()
        .map(autor -> iAutorRepository.findByName(autor.getName())
            .orElseGet(() -> iAutorRepository.save(autor)))
        .toList();

    // Check if authors were found
    if (autoresGuardados.isEmpty()) {
      return "No author found for the book \"" + libroBuscado.title() + "\". \nThe book cannot be saved.";
    }

    // Create a new Book entity
    Libro libro = new Libro();
    libro.setTitle(libroBuscado.title());
    libro.setNumeroDeDescargas(libroBuscado.download_count().intValue());
    libro.setAuthor(autoresGuardados.get(0)); // Example: take the first author.

    // Set the first language from the languages list (if available)
    if (libroBuscado.languages() != null && !libroBuscado.languages().isEmpty()) {
      libro.setLanguages(libroBuscado.getFirstLanguage());  // Assign the first language
    }

    // Save the book in the database
    iLibroRepository.save(libro);

    return "Book successfully saved: " + libro.getTitle();
  }

  public List<Libro> listarTodosLosLibros() {
    return iLibroRepository.findAll();
  }

  public List<Libro> listarLibrosPorIdioma(String idioma) {
//    return iLibroRepository.findByLanguage(idioma);
    if (idioma == null || idioma.isEmpty()) {
      return iLibroRepository.findAll();  // O una lógica para manejar búsquedas sin idioma
    }
    return iLibroRepository.findByLanguages(idioma);  // Buscar por idioma
  }

  // Método para obtener libros por autor
  public List<Libro> listarLibrosPorIdAuthor(Long idAuthor) {
    // Buscar autor por su ID
    Author autor = iAutorRepository.findById(idAuthor).orElse(null);

    // Si el autor no existe, retornar una lista vacía o manejar el error
    if (autor == null) {
      return List.of(); // Retornar lista vacía si el autor no se encuentra
    }

    // Buscar libros por el autor encontrado
    return iLibroRepository.findByAuthor(autor);
  }


}
