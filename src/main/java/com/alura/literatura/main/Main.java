package com.alura.literatura.main;

import com.alura.literatura.entity.Author;
import com.alura.literatura.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Main {

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
        case 4:
          listarAutoresVivosDuranteAnio();
          break;
        case 5:
          listarLibrosPorIdioma();
          break;
        case 6:
          listarLibrosPorIdAutor();
          break;
        case 7:
          listarLibrosPorNameAuthor();
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
          4 - Listar autores vivos durante un año determinado.
          5 - Listar libros por idiomas.
          6 - Listar libros por idAuthor.
          7 - Listar libros por nameAuthor.
          99 - Salir
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
      libros.forEach(libro -> System.out.printf("ID: %d | Título: %s | Autor: %s | Descargas: %d | language: %s%n",
          libro.getIdLibro(),
          libro.getTitle(),
          libro.getAuthor().getName(),
          libro.getNumeroDeDescargas(),
          libro.getLanguages()
      ));
    }
  }

  private void listarAutoresRegistrados() {
    var autores = authorService.listarTodosLosAutores();
    if (autores.isEmpty()) {
      System.out.println("No hay autores registrados.");
    } else {
      System.out.println("\n--- Lista de autores registrados ---");
      autores.forEach(autor -> System.out.printf("ID: %d | Nombre: %s | Birth: %d | Death: %s%n",
          autor.getIdAuthor(),
          autor.getName(),
          autor.getBirth_year(),
          autor.getDeath_year() == null ? "Aún vivo" : autor.getDeath_year()
      ));


    }
  }

  private void listarAutoresVivosDuranteAnio() {
    System.out.print("Ingrese el año para buscar autores vivos: ");
    try {
      int year = Integer.parseInt(scanner.nextLine());
      var autores = authorService.listarAutoresVivosDurante(year);
      if (autores.isEmpty()) {
        System.out.printf("No hay autores registrados que estuvieran vivos en el año %d.%n", year);
      } else {
        System.out.printf("\n--- Autores vivos en el año %d ---\n", year);
        autores.forEach(autor -> System.out.printf("ID: %d | Nombre: %s | Nacimiento: %d | Fallecimiento: %s%n",
            autor.getIdAuthor(),
            autor.getName(),
            autor.getBirth_year(),
            autor.getDeath_year() == null ? "Aún vivo" : autor.getDeath_year()));
      }
    } catch (NumberFormatException e) {
      System.out.println("Año inválido. Por favor, ingrese un año numérico válido.");
    }
  }

  private void listarLibrosPorIdioma() {
    System.out.print("Ingrese el idioma para buscar los libros: ");
    String idioma = scanner.nextLine();
    var libros = libroService.listarLibrosPorIdioma(idioma);
    if (libros.isEmpty()) {
      System.out.printf("No hay libros registrados en el idioma %s.%n", idioma);
    } else {
      System.out.printf("\n--- Lista de libros en el idioma %s ---\n", idioma);
      libros.forEach(libro -> System.out.printf("ID: %d | Título: %s | Autor: %s | Descargas: %d%n",
          libro.getIdLibro(),
          libro.getTitle(),
          libro.getAuthor().getName(),
          libro.getNumeroDeDescargas()));
    }
  }

  private void listarLibrosPorIdAutor() {
    System.out.print("Ingrese el ID del autor para buscar los libros: ");
    try {
      Long autorId = Long.parseLong(scanner.nextLine());
      var libros = libroService.listarLibrosPorIdAuthor(autorId);
      if (libros.isEmpty()) {
        System.out.println("No se encontraron libros para este autor.");
      } else {
        System.out.println("\n--- Lista de libros de este autor ---");
        libros.forEach(libro -> System.out.printf("ID: %d | Título: %s | Autor: %s | Descargas: %d | Idioma: %s%n",
            libro.getIdLibro(),
            libro.getTitle(),
            libro.getAuthor().getName(),
            libro.getNumeroDeDescargas(),
            libro.getLanguages()));
      }
    } catch (NumberFormatException e) {
      System.out.println("ID de autor inválido. Por favor, ingrese un ID numérico válido.");
    }
  }


  private void listarLibrosPorNameAuthor() {
    System.out.print("Ingrese el nombre del autor para buscar los libros: ");
    String nombreAutor = scanner.nextLine();
    var autores = authorService.buscarAutoresPorNombre(nombreAutor);

    if (autores.isEmpty()) {
      System.out.printf("No se encontraron autores con el nombre \"%s\".%n", nombreAutor);
    } else if (autores.size() == 1) {
      // Si hay un único autor, listar sus libros directamente
      Author autor = autores.get(0);
      System.out.printf("\n--- Libros del autor: %s ---\n", autor.getName());
      var libros = libroService.listarLibrosPorIdAuthor(autor.getIdAuthor());
      if (libros.isEmpty()) {
        System.out.println("Este autor no tiene libros registrados.");
      } else {
        libros.forEach(libro -> System.out.printf("ID: %d | Título: %s | Descargas: %d | Idioma: %s%n",
            libro.getIdLibro(),
            libro.getTitle(),
            libro.getNumeroDeDescargas(),
            libro.getLanguages()));
      }
    } else {
      // Si hay múltiples autores, listarlos y permitir la selección
      System.out.println("\n--- Autores encontrados ---");
      autores.forEach(autor -> System.out.printf("ID: %d | Nombre: %s | Nacimiento: %d | Fallecimiento: %s%n",
          autor.getIdAuthor(),
          autor.getName(),
          autor.getBirth_year(),
          autor.getDeath_year() == null ? "Aún vivo" : autor.getDeath_year()));

      System.out.print("\nSeleccione el ID del autor para listar sus libros: ");
      try {
        Long idAutor = Long.parseLong(scanner.nextLine());
        var libros = libroService.listarLibrosPorIdAuthor(idAutor);
        if (libros.isEmpty()) {
          System.out.println("Este autor no tiene libros registrados.");
        } else {
          System.out.println("\n--- Lista de libros de este autor ---");
          libros.forEach(libro -> System.out.printf("ID: %d | Título: %s | Descargas: %d | Idioma: %s%n",
              libro.getIdLibro(),
              libro.getTitle(),
              libro.getNumeroDeDescargas(),
              libro.getLanguages()));
        }
      } catch (NumberFormatException e) {
        System.out.println("ID de autor inválido. Por favor, ingrese un ID numérico válido.");
      }
    }
  }


}
