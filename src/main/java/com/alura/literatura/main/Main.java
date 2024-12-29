package com.alura.literatura.main;

import com.alura.literatura.repository.IAutorRepository;
import com.alura.literatura.repository.ILibroRepository;
import com.alura.literatura.service.ConsumoAPI;
import com.alura.literatura.service.ConvierteDatos;
import com.alura.literatura.service.LibroService;
import com.alura.literatura.util.BuscarPorTitulo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Main {

  Scanner scanner = new Scanner(System.in);
  String URL_BASE = "https://gutendex.com/books/";
  String URL_SEARCH = "http://gutendex.com/books/?search=";

  ConsumoAPI consumoAPI = new ConsumoAPI();
  ConvierteDatos conversor = new ConvierteDatos();
//  Datos datos;  //  2. Top 10 libros más descargados

  private LibroService libroService;
  private ILibroRepository libroRepository;
  private IAutorRepository autorRepository;

  @Autowired
  private BuscarPorTitulo buscarPorTitulo;


  public void muestraElMenu() {
    // json 32 results devuelve
//    System.out.println(consumoAPI.obtenerDatos(URL_BASE));

    System.out.println("*** BIENVENIDOS A LITERALURA! ***");
    int option = -1;
    while (option != 99) {
      var menu = """
           -------------------------------------------------------------
               Bienvenido a Literatura PRINCIPAL
           -------------------------------------------------------------
             1 - Buscar libro por título.
             2 - Listar libros registrados
             5 - Listar autores registrados.
             6 - Listar autores vivos durante un año determinado.
          
             4 - Mostrar libros por idiomas.
            99 - Salir
          
            7 - Mostrar libros por autor.
            3 - Top 10 de los libros más descargados.
          """;
      System.out.println(menu);

      try {
        option = Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("---Opción inválida. Elija una opción válida.---\n");
        continue;
      }

      switch (option) {
        case 1:
          // 1. Buscar libros por título
          buscarPorTitulo.buscarPorTitulo(scanner, consumoAPI, URL_SEARCH, conversor);
          break;

        case 99:
          System.out.println("Salir");
          break;

        default:
          System.out.println("*** Opción inválida. Por favor elija una opción válida. ***");

      }

    } // end while


  }

}

