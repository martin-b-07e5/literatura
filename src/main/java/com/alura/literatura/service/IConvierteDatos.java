package com.alura.literatura.service;

public interface IConvierteDatos {
  // Datos genéricos. No sabemos cuál va a ser el retorno. Puede retornarme una cosa u otra.
  // Pasamos una clase genérica, llamada genericClass.
  <T> T obtenerDatosx(String json, Class<T> genericClass);

  /* The line <T> T obtenerDatos(String json, Class<T> genericClass);
    in the interface IConvierteDatos demonstrates the use of generics in Java.

Here's a breakdown of the code:
  - <T>: This is the declaration of a type parameter T. It allows you to use T as a type within the method.

  - T obtenerDatos(String json, Class<T> genericClass);: This is the method signature. The T before the method name
 indicates that the method returns a value of type T.

  - String json: This is the first parameter of the method, which is of type String.

  - Class<T> genericClass: This is the second parameter of the method, which is of type Class<T>. The Class<T> is a
  generic type that represents a class.


By using generics, you can create a method that can work with different types, allowing for more flexibility and reusability in your code.
 In this case, the obtenerDatos method can convert a JSON string into an object of any type specified by the genericClass parameter.
  In this specific case, we are using it to convert JSON data into objects of the DatosSerieRecord class.
*/

}
