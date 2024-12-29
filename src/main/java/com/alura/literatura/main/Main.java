package com.alura.literatura.main;

import com.alura.literatura.service.ConsumoAPI;
import com.alura.literatura.service.ConvierteDatos;

import java.util.Scanner;

public class Main {


  Scanner scanner = new Scanner(System.in);
  String URL_BASE = "https://gutendex.com/books/";
  String URL_SEARCH = "http://gutendex.com/books/?search=";

  ConsumoAPI consumoAPI = new ConsumoAPI();
  ConvierteDatos conversor = new ConvierteDatos();
//  Datos datos;


  public void muestraElMenu() {
    // json 32 results devuelve
    System.out.println(consumoAPI.obtenerDatos(URL_BASE));
  }


}

