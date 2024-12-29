package com.alura.literatura;

import com.alura.literatura.main.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.alura.literatura")
@EntityScan(basePackages = "com.alura.literatura.entity")
@EnableJpaRepositories("com.alura.literatura.repository")
public class LiteraturaApplication implements CommandLineRunner {

  @Autowired
  private Main main;

  public static void main(String[] args) {
    SpringApplication.run(LiteraturaApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    main.muestraElMenu();
  }
}
