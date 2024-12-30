# Literatura Challenge

## Pasos para completar este desafío

    ✅- Configuración del Ambiente Java.
    ✅- Creación del Proyecto.
    ✅- Consumo de la API.
    ✅- Análisis de la Respuesta JSON.
    ✅- Inserción y consulta en la base de datos.
    ✅- Exhibición de resultados a los usuarios.

---

### Funcionalidades

Selecciona una opción a través de su número:

    ✅1 - Buscar libro por título.
    ✅2 - Listar libros registrados.
    ✅3 - Listar autores registrados.
    ✅4 - Listar autores vivos en un determinado año.
    ✅5 - Listar libros por idioma.
    ✅6 - Listar libros por ID de autor.
    ✅7 - Listar libros por nombre del autor.
    ✅8 - Top 10 de los libros más descargados.
    ✅99 - Salir.

---

### **LiteraturaApplication**

- **Framework y dependencias:**
  Utiliza **Spring Boot** con JPA y PostgreSQL para la gestión de datos, **Lombok** para reducir código repetitivo, y *
  *Jackson** para trabajar con datos JSON.

- **Configuración:**
  Los datos de conexión a la base de datos (host, usuario, contraseña) se configuran a través del archivo
  `application.properties` y se gestionan mediante variables de entorno para mayor seguridad.

- **Objetivo del programa:**
  Gestionar libros y autores con datos obtenidos de la API de Gutendex, almacenarlos en PostgreSQL y mostrarlos a los
  usuarios a través de un menú interactivo.

---

### **Estructura principal**

1. **Entity:**

- `Libro` y `Author`: Clases que mapean las entidades correspondientes en la base de datos.
- `Datos` y `DatosLibros`: Clases utilizadas para modelar datos JSON provenientes de la API.

2. **Repository:**

- Interfaces `IAutorRepository` e `ILibroRepository` para realizar consultas y operaciones con Spring Data JPA.

3. **Service:**

- `ConsumoAPI`: Gestiona la comunicación con la API de Gutendex.
- `ConvierteDatos`: Convierte datos JSON en objetos Java.
- `LibroService`: Contiene lógica de negocio para la gestión de libros.
- `BuscarPorTitulo`: Permite buscar libros por título utilizando la API.

4. **Main:**

- Clase que gestiona el menú principal y las interacciones del usuario.

5. **Aplicación:**

- Clase `LiteraturaApplication` actúa como punto de entrada al programa.

---

### **Estado del desarrollo**

- **Funcionalidad completamente implementada:**
    - **Buscar libro por título:** Realizada a través del servicio `BuscarPorTitulo`.
    - **Listar libros y autores registrados:** Implementada mediante consultas a la base de datos.
    - **Listar autores vivos en un año específico:** Lógica definida e implementada en el servicio de autores.
    - **Listar libros por idioma:** Funcionalidad operativa con filtros predefinidos.
    - **Listar libros por autor (ID o nombre):** Completamente funcional, con opciones para manejar múltiples autores
      encontrados.
    - **Top 10 libros más descargados:** Implementado con consulta optimizada a la base de datos y ordenamiento por
      descargas.

- **Estado pendiente:**
    - Ninguna funcionalidad pendiente. El proyecto cumple todos los requerimientos establecidos.

---

### **Componentes destacados**

1. **Conversión de JSON:**

- Uso de `ObjectMapper` y genéricos en `ConvierteDatos` para mapear JSON a cualquier clase Java, facilitando la
  extensión para nuevos datos.

2. **Relaciones de base de datos:**

- Relaciones configuradas con `@OneToMany` y `@ManyToOne` entre autores y libros.
- Consultas optimizadas utilizando métodos personalizados en `ILibroRepository` e `IAutorRepository`.

3. **Estrategia de manejo de excepciones:**

- Manejo robusto de errores en la comunicación con la API y consultas a la base de datos.
- Uso de logging para rastrear problemas en lugar de depender de `printStackTrace`.

4. **Presentación de datos:**

- Uso de `System.out.printf` para formatear y presentar información clara y estructurada en el menú interactivo.

---

### **Instrucciones para ejecutar**

1. **Configurar las variables de entorno:**

- Configura las credenciales de la base de datos en el archivo `application.properties` utilizando variables de entorno.

2. **Compilar y ejecutar:**

- Asegúrate de tener Java 17 o superior instalado.
- Ejecuta el programa utilizando tu IDE preferido o el comando:
  ```bash
  mvn spring-boot:run
  ```

3. **Interacción con el programa:**

- Sigue las opciones numeradas del menú principal para explorar las diferentes funcionalidades.

---
