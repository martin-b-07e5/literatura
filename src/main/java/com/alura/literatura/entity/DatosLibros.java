package com.alura.literatura.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
    @JsonAlias("id") Integer id,
    @JsonAlias("title") String title,
    @JsonAlias("authors") List<Person> authors,
    @JsonAlias("languages") List<String> languages,
    @JsonAlias("download_count") Double download_count
//    @JsonAlias("translators") List<Person> translators,
//    @JsonAlias("copyright") Boolean copyright,
//    @JsonAlias("media_type") String media_type,

) {
}
