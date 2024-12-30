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

}
