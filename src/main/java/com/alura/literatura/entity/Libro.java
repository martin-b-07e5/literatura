package com.alura.literatura.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "libros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Libro {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idLibro;

  @Column(unique = true)
  private String title;

  private String language;

  private Integer numeroDeDescargas;

  @ManyToOne
  @JoinColumn(name = "id_author")
  private Author author;

}
