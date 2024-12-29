package com.alura.literatura.util;

import com.alura.literatura.entity.Author;
import com.alura.literatura.entity.Datos;
import com.alura.literatura.entity.DatosLibros;
import com.alura.literatura.service.ConsumoAPI;
import com.alura.literatura.service.ConvierteDatos;
import com.alura.literatura.service.LibroService;
import org.springframework.stereotype.Component;

import java.util.DoubleSummaryStatistics;
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
    System.out.println("\n1. Input String: " + bookTitle);

    // Fetch JSON data from the API
    var json = consumoAPI.obtenerDatos(urlSearch + bookTitle);
//    System.out.println("JSON: " + json);

    // *** Convert the JSON to a DatosLibros object ***
    Datos datosBusqueda = conversor.obtenerDatosx(json, Datos.class);
//    System.out.println("\ndatosBusqueda: " + datosBusqueda);

    // *** Search for the book in the JSON data ***
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

      try {
        System.out.println("Birth year: " + libroBuscado.get().authors().stream()
            .map(Author::getBirth_year)
            .findFirst()
            .orElse(0)
        );
      } catch (NullPointerException e) {
        System.out.println("Birth year: No birth date found");
      }

      try {
        System.out.println("Death year: " + libroBuscado.get().authors().stream()
            .map(Author::getDeath_year)
            .findFirst()
            .orElse(0) // findFirst() returns an Optional, so we need to unbox the value before printing it.
        );
      } catch (NullPointerException e) {
        System.out.println("Death year: No death date found");
      }

      System.out.println("----------");
      System.out.println("Languages: " + libroBuscado.get().languages().stream().collect(Collectors.joining(", ")));
      System.out.println("Downloads: " + libroBuscado.get().download_count());
      System.out.println("------------------------------------------------");
    } /*else {
      System.out.println("\nNo book found with the given title");
    }*/

    // Handling statistics
//    DoubleSummaryStatistics statsByTeacher = datosBusqueda.results().stream()
//        .filter(d -> d.download_count() > 0)
//        .collect(Collectors.summarizingDouble(DatosLibros::download_count));
//    System.out.println("\nDownload statistics: ");
//    System.out.println("Min: " + statsByTeacher.getMin());
//    System.out.println("Max: " + statsByTeacher.getMax());
//    System.out.printf("Average: %.2f%n", statsByTeacher.getAverage());

//    DoubleSummaryStatistics stats = datosBusqueda.results().stream()
//        .mapToDouble(DatosLibros::download_count)
//        .summaryStatistics();
//    System.out.println("\nDownload statistics:");
//    System.out.printf("Min: %.0f%n", stats.getMin());
//    System.out.printf("Max: %.0f%n", stats.getMax());
//    System.out.printf("Average: %.0f%n", stats.getAverage());
//    System.out.printf("Number of records evaluated for statistics: %d%n", stats.getCount());

    /*Call the save function from LibroService*/
    if (libroBuscado.isPresent()) {
      DatosLibros libro = libroBuscado.get(); // Unbox the Optional
//      System.out.println("Book found: " + libro.title());

      // Call saveBook with the unboxed object
      String resultado = libroService.saveBook(libro);
      System.out.println("\nResult of saving the book: " + resultado);
    } else {
      System.out.println("BOOK WITH THE GIVEN TITLE NOT FOUND");
    }

  } // end buscarPorTitulo

}
