# Literatura challenge

## Pasos para completar este desafío

    ✅- Configuración del Ambiente Java;
    ✅- Creación del Proyecto;
    ✅- Consumo de la API;
    ✅- Análisis de la Respuesta JSON;
    ✅- Inserción y consulta en la base de datos;
    ✅- Exibición de resultados a los usuarios;

### Funcionalidades

    Lija la opción a través de su número
    ✅1- Buscar libro por título
    ✅2- Listar libros registrados
    ✅3- Listar autores registrados
    ✅4- Listar autores vivos en un determinado año
    👷5- Listar libros por idioma
    ✅6- Salir de la app

---

### **LiteraturaApplication**

- **Framework y dependencias:** Utiliza **Spring Boot** con JPA y PostgreSQL, junto con Lombok y Jackson para
  simplificar y gestionar objetos y datos JSON.
- **Configuración:** Los datos de conexión a la base de datos se configuran a través de `application.properties`
  utilizando variables de entorno para host, usuario y contraseña.
- **Objetivo del programa:** Gestionar libros y autores con datos obtenidos de la API de Gutendex, almacenados en
  PostgreSQL y accesibles a través de un menú interactivo.

---

### **Estructura principal**

- **Entity:** Clases `Libro` y `Author` mapean entidades a la base de datos, mientras que `Datos` y `DatosLibros`
  modelan datos JSON.
- **Repository:** Interfaces `IAutorRepository` e `ILibroRepository` para operaciones con JPA.
- **Service:** Servicios para consumo de la API (`ConsumoAPI`), conversión de JSON (`ConvierteDatos`), y lógica de
  negocio (`LibroService`, `BuscarPorTitulo`).
- **Main:** Clase `Main` gestiona el menú principal e interacciones del usuario.
- **Aplicación:** Clase `LiteraturaApplication` como punto de entrada.

---

### **Estado del desarrollo**

- Funcionalidad implementada:
    - **Buscar libro por título:** Implementado a través del servicio `BuscarPorTitulo`.
    - **Infraestructura base:** Comunicación con la API y conversión de datos JSON a objetos Java.
    - **Autores vivos en un año y libros por idioma:** Necesario definir lógica para filtrar y listar datos según los
      criterios.

- Funcionalidad pendiente o incompleta:

    - **Top 10 libros más descargados y mostrar libros por autor:** Aún no implementado.

---

### **Componentes destacados**

- **IConvierteDatos & ConvierteDatos:**
    - Usa genéricos y `ObjectMapper` para mapear JSON a cualquier clase Java.
    - Facilita la reutilización y extensibilidad del código para manejar diferentes tipos de datos.

- **Base de datos:**
    - Uso de relaciones `@OneToMany` y `@ManyToOne` entre `Author` y `Libro`.
    - Configuración de Hibernate para mostrar y formatear SQL.

---