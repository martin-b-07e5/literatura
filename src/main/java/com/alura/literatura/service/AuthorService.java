package com.alura.literatura.service;

import com.alura.literatura.entity.Author;
import com.alura.literatura.repository.IAutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

  // dependency injection
  @Autowired
  private IAutorRepository iAutorRepository;

  /**
   * Retrieves a list of all registered authors from the database.
   *
   * @return List of authors
   */
  public List<Author> listarTodosLosAutores() {
    return iAutorRepository.findAll();
  }

  /**
   * Retrieves a list of authors who were alive during a specific year.
   *
   * @param year The year to check
   * @return List of authors alive during the year
   */
//  public List<Author> listarAutoresVivosDurante(int year) {
//    return iAutorRepository.findAll().stream()
//        .filter(autor -> autor.getBirth_year() <= year && (autor.getDeath_year() == null || autor.getDeath_year() >= year))
//        .toList();
//  }
  public List<Author> listarAutoresVivosDurante(int year) {
    return iAutorRepository.findAutoresVivosDurante(year);
  }

  public List<Author> buscarAutoresPorNombre(String nombre) {
    return iAutorRepository.findByNameContainingIgnoreCase(nombre);
  }


}
