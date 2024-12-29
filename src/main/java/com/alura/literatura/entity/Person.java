package com.alura.literatura.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "authors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idAuthor;
  private String name;
  private Integer birth_year;
  private Integer death_year;


  @Override
  public String toString() {
    return "Person{" +
        "idAuthor=" + idAuthor +
        ", name='" + name + '\'' +
        ", birth_year=" + birth_year +
        ", death_year=" + death_year +
        '}';
  }
}
