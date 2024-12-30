package com.alura.literatura.main;

import com.alura.literatura.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Main {

  private static final String URL_BASE = "https://gutendex.com/books/";
  private static final String URL_SEARCH = "http://gutendex.com/books/?search=";

  @Autowired
  private ConsumoAPI consumoAPI;

  @Autowired
  private ConvierteDatos conversor;

  @Autowired
  private LibroService libroService;

  @Autowired
  private BuscarPorTitulo buscarPorTitulo;

  @Autowired
  private AuthorService authorService;

  private final Scanner scanner = new Scanner(System.in);

  public void muestraElMenu() {
    System.out.println("*** BIENVENIDOS A LITERATURA! ***");

    int option = -1;
    while (option != 99) {
      mostrarMenu();
      option = obtenerOpcion();

      switch (option) {
        case 1:
          buscarLibroPorTitulo();
          break;
        case 2:
          listarLibrosRegistrados();
          break;
        case 3:
          listarAutoresRegistrados();
          break;
        case 99:
          salir();
          break;
        default:
          opcionInvalida();
          break;
      }
    }
  }


  private void mostrarMenu() {
    System.out.println("""
        -------------------------------------------------------------
            Bienvenido a Literatura PRINCIPAL
        -------------------------------------------------------------
          1 - Buscar libro por título.
          2 - Listar libros registrados.
          3 - Listar autores registrados.
          6 - Listar autores vivos durante un año determinado.
          4 - Mostrar libros por idiomas.
          99 - Salir
          7 - Mostrar libros por autor.
          98 - Top 10 de los libros más descargados.
        """);
  }

  private int obtenerOpcion() {
    try {
      return Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("--- Opción inválida. Elija una opción válida. ---");
      return -1;
    }
  }

  private void salir() {
    System.out.println("Salir");
  }

  private void opcionInvalida() {
    System.out.println("*** Opción inválida. Por favor elija una opción válida. ***");
  }

  private void buscarLibroPorTitulo() {
    buscarPorTitulo.buscarPorTitulo(scanner, consumoAPI, URL_SEARCH, conversor);
  }

  private void listarLibrosRegistrados() {
    var libros = libroService.listarTodosLosLibros();
    if (libros.isEmpty()) {
      System.out.println("No hay libros registrados.");
    } else {
      System.out.println("\n--- Lista de libros registrados ---");
      libros.forEach(libro -> System.out.printf("ID: %d | Título: %s | Autor: %s | Descargas: %d%n",
          libro.getIdLibro(),
          libro.getTitle(),
          libro.getAuthor().getName(),
          libro.getNumeroDeDescargas()
      ));
    }
  }

  private void listarAutoresRegistrados() {
    var autores = authorService.listarTodosLosAutores();
    if (autores.isEmpty()) {
      System.out.println("No hay autores registrados.");
    } else {
      System.out.println("\n--- Lista de autores registrados ---");
      autores.forEach(autor -> System.out.printf("ID: %d | Nombre: %s%n",
          autor.getIdAuthor(),
          autor.getName()));
    }
  }


}
