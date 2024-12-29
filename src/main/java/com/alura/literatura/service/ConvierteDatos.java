package com.alura.literatura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

/**
 * Service class that implements the IConvierteDatos interface for converting JSON data into Java objects.
 */
@Component
public class ConvierteDatos implements IConvierteDatos {

  private ObjectMapper objectMapper = new ObjectMapper();

  /**
   * Converts a JSON string into an object of the specified class type.
   *
   * @param json         The JSON string to be converted.
   * @param genericClass The class type to which the JSON should be converted.
   * @param <T>          The type of class to which the JSON is converted.
   * @return The converted object of type T.
   * @throws RuntimeException if there is an error during the conversion.
   */
  @Override
  public <T> T obtenerDatosx(String json, Class<T> genericClass) {
    try {
      return objectMapper.readValue(json, genericClass);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Error converting JSON to object", e);
    }
  }

  /* https://www.baeldung.com/jackson-object-mapper-tutorial#bd-2-json-to-java-object
   * With ObjectMapper, we can convert JSON to a Java object using the readValue() method.
   * This method converts JSON into an instance of the specified class and returns it.
   * If an error occurs during the conversion, a JsonProcessingException is thrown and wrapped in a RuntimeException.
   */
}

