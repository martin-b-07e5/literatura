package com.alura.literatura.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
    @JsonAlias("id") Integer id,
    @JsonAlias("title") String title,
    @JsonAlias("authors") List<Author> authors,
    @JsonAlias("languages") List<String> languages,
    @JsonAlias("download_count") Double download_count
//    @JsonAlias("translators") List<Author> translators,
//    @JsonAlias("copyright") Boolean copyright,
//    @JsonAlias("media_type") String media_type,

) {
  // Método para obtener el primer idioma de la lista (si existe)
  public String getFirstLanguage() {
    if (languages != null && !languages.isEmpty()) {
      return languages.get(0);  // Retorna el primer idioma
    }
    return null;
  }
}
