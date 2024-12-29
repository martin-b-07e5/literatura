package com.alura.literatura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Component;

/**
 * Service class that handles API consumption by making HTTP requests.
 */
@Component
public class ConsumoAPI {

  /**
   * Sends an HTTP GET request to the specified URL and returns the response body as a string.
   *
   * @param url The URL of the API to request data from.
   * @return The response body as a string containing the JSON data.
   * @throws RuntimeException if there is an error during the HTTP request or response handling.
   */
  public String obtenerDatos(String url) {
    // Returns the JSON data from the API
    // https://docs.oracle.com/en/java/javase/21/docs/api/java.net.http/java/net/http/HttpRequest.html
    HttpResponse<String> response;
    try (HttpClient client = HttpClient.newHttpClient()) {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(url)) // e.g., "http://foo.com/"
          .build();
      // https://docs.oracle.com/en/java/javase/21/docs/api/java.net.http/java/net/http/HttpResponse.html
      try {
        response = client
            .send(request, HttpResponse.BodyHandlers.ofString());
      } catch (IOException | InterruptedException e) {
        throw new RuntimeException("Error during the HTTP request", e);
      }
    }

    // Returning the body of the response
//    System.out.println("response.body() = " + response.body());
    return response.body();
  }
}

