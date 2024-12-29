package com.alura.literatura.main;

import com.alura.literatura.service.ConsumoAPI;
import com.alura.literatura.service.ConvierteDatos;
import com.alura.literatura.service.LibroService;
import com.alura.literatura.util.BuscarPorTitulo;
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
          5 - Listar autores registrados.
          6 - Listar autores vivos durante un año determinado.
          4 - Mostrar libros por idiomas.
          99 - Salir
          7 - Mostrar libros por autor.
          3 - Top 10 de los libros más descargados.
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

  private void buscarLibroPorTitulo() {
    buscarPorTitulo.buscarPorTitulo(scanner, consumoAPI, URL_SEARCH, conversor);
  }

  private void salir() {
    System.out.println("Salir");
  }

  private void opcionInvalida() {
    System.out.println("*** Opción inválida. Por favor elija una opción válida. ***");
  }
}
