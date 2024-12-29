package com.alura.literatura.service;

import com.alura.literatura.entity.Author;
import com.alura.literatura.entity.Datos;
import com.alura.literatura.entity.DatosLibros;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Utility class for searching books by title and saving them to the database.
 */
@Component
public class BuscarPorTitulo {

  private final LibroService libroService;

  // Dependency injection through constructor
  public BuscarPorTitulo(LibroService libroService) {
    this.libroService = libroService;
  }

  /**
   * Searches for a book by its title, retrieves information from the API, and saves it to the database if it is found.
   *
   * @param scanner    The scanner used to read user input.
   * @param consumoAPI The service that fetches data from the API.
   * @param urlSearch  The base URL for the API search endpoint.
   * @param conversor  The service that converts JSON to Java objects.
   */
  public void buscarPorTitulo(Scanner scanner, ConsumoAPI consumoAPI, String urlSearch, ConvierteDatos conversor) {

    // Prompt user to input the book title
    System.out.print("Enter the book title: ");
    var bookTitle = scanner.nextLine();
    String bookTitleFormatted = bookTitle.replace(" ", "+");

    // Fetch JSON data from the API
    var json = consumoAPI.obtenerDatos(urlSearch + bookTitle);

    // Convert the JSON to a DatosLibros object
    Datos datosBusqueda = conversor.obtenerDatosx(json, Datos.class);

    // Search for the book in the JSON data
    Optional<DatosLibros> libroBuscado = datosBusqueda.results().stream()
        .filter(libro -> libro.title().toLowerCase().contains(bookTitleFormatted.toLowerCase()))
        .findFirst();

    if (libroBuscado.isPresent()) {

      // Print book details
      System.out.println("------------------------------------------------");
      System.out.println("Book title: " + libroBuscado.get().title());
      System.out.println("----------");
      System.out.println("Authors: " + libroBuscado.get().authors().stream()
          .map(Author::getName)
          .collect(Collectors.joining(", "))
      );

      libroBuscado.get().authors().stream()
          .map(Author::getBirth_year)
          .findFirst()
          .ifPresentOrElse(
              birthYear -> System.out.println("Birth year: " + birthYear),
              () -> System.out.println("Birth year: No birth date found")
          );

      libroBuscado.get().authors().stream()
          .map(Author::getDeath_year)
          .findFirst()
          .ifPresentOrElse(
              deathYear -> System.out.println("Death year: " + deathYear),
              () -> System.out.println("Death year: No death date found")
          );

      System.out.println("----------");
      System.out.println("Languages: " + libroBuscado.get().languages().stream().collect(Collectors.joining(", ")));
      System.out.println("Downloads: " + libroBuscado.get().download_count());
      System.out.println("------------------------------------------------");

      // Save the book using the LibroService
      String resultado = libroService.saveBook(libroBuscado.get());
      System.out.println("\nResult of saving the book: " + resultado);

    } else {
      System.out.println("BOOK WITH THE GIVEN TITLE NOT FOUND");
    }

  } // end buscarPorTitulo

}
