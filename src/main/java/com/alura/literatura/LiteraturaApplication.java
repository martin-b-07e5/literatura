package com.alura.literatura;

import com.alura.literatura.main.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaApplication {

  public static void main(String[] args) {
    SpringApplication.run(LiteraturaApplication.class, args);

    // implementación del código para buscar libros por título
    Main app = new Main();
    app.muestraElMenu();
  }

}
