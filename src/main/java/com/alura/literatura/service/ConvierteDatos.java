package com.alura.literatura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos implements IConvierteDatos {

  private ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public <T> T obtenerDatosx(String json, Class<T> genericClass) {
    try {
      return objectMapper.readValue(json, genericClass);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

    /* https://www.baeldung.com/jackson-object-mapper-tutorial#bd-2-json-to-java-object
   Con ObjectMapper, podemos convertir un JSON a un objeto Java, utilizando el method readValue()
   Este method convierte un JSON en una instancia de la clase indicada por el segundo parámetro (Class<T>) y la devuelve.
   En caso de error durante la conversión, lanza una JsonProcessingException y la lanza como una RuntimeException.*/


}
