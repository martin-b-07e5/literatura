package com.alura.literatura.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "libros")
public class Libro {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idLibro;

  @Column(unique = true)
  private String title;

  @ManyToOne
  @JoinColumn(name = "id_author")
  private Author author;

  private Integer numeroDeDescargas;

  // incomplete


}
