package com.alura.literatura.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "authors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idAuthor;

  private String name;
  private Integer birth_year;
  private Integer death_year;

  @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
  private List<Libro> libros;


  @Override
  public String toString() {
    return "Author{" +
        "idAuthor=" + idAuthor +
        ", name='" + name + '\'' +
        ", birth_year=" + birth_year +
        ", death_year=" + death_year +
        '}';
  }
}
