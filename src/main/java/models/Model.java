package models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Stack;

import static org.example.BibliotecaConSecciones_HashMap_HashSet_json.validarEntrada;
import static presenters.Presenter.*;

public class Model {

    public Model() {
    }

    public static void agregarLibroDevuelto() {                                        // Manejo de excepciones en caso de errores de entrada/salida
        if (seccionesExistentes.isEmpty()) {                                            // Comprueba si no hay secciones disponibles
            System.out.println("No hay secciones disponibles para agregar libros.");
            return;                                                                     // Sale de la función si no hay secciones disponibles
        }
        System.out.print("Ingrese el nombre del libro devuelto: ");                   // Solicita al usuario ingresar el nombre del libro devuelto
        String libroDevuelto = validarEntrada();                                        // Llama a la función para validar la entrada del usuario y almacena el nombre del libro
        System.out.print("Ingrese la sección del libro: ");                           // Solicita al usuario ingresar la sección del libro
        String seccion = validarEntrada().toUpperCase();                                // Llama a la función para validar la entrada y convierte el nombre de la sección a mayúsculas
        if (!seccionesExistentes.contains(seccion)) {                                 // Comprueba si la sección no existe en las secciones existentes
            System.out.println("La sección '" + seccion + "' no existe. Elija una sección válida.");
            return;                                                                     // Sale de la función si la sección no existe
        }
        Stack<String> seccionDeLibros = seccionesDeLibros.get(seccion);              // Obtiene la pila de libros de la sección especificada
        seccionDeLibros.push(libroDevuelto.toUpperCase());                          // Agrega el nombre del libro (convertido a mayúsculas) a la sección
        System.out.println("Libro agregado a la sección '" + seccion + "'.");           // Muestra un mensaje indicando que el libro se agregó a la sección
    }
    public static void prestarLibro() {                                         // Función para prestar un libro de una sección de la biblioteca
        if (seccionesExistentes.isEmpty()) {                                        // Comprueba si no hay secciones disponibles en la biblioteca.
            System.out.println("No hay secciones disponibles para prestar libros."); // Si no hay secciones, muestra un mensaje indicando que no hay secciones disponibles y finaliza la función.
            return;
        }
        System.out.print("Ingrese la sección de la que desea prestar un libro: ");// Solicita al usuario que ingrese la sección de la que desea prestar un libro.
        String seccion = validarEntrada().toUpperCase();                        // Llama a la función validarEntrada() para obtener y validar la entrada del usuario, y luego convierte la sección a mayúsculas.
        if (!seccionesExistentes.contains(seccion)) {                             // Comprueba si la sección ingresada no existe en la biblioteca.
            System.out.println("La sección '" + seccion + "' no existe. Elija una sección válida."); // Muestra un mensaje indicando que la sección ingresada no existe y pide al usuario que elija una sección válida. Luego, finaliza la función.
            return;
        }
        Stack<String> seccionDeLibros = seccionesDeLibros.get(seccion);          // Obtiene la pila de libros de la sección especificada.
        if (seccionDeLibros != null && !seccionDeLibros.isEmpty()) {                // Comprueba si la pila de libros de la sección no es nula y si contiene libros.
            String libroPrestado = seccionDeLibros.pop();                           // Realiza la operación de prestar el libro de la sección, sacándolo de la pila.
            System.out.println("El bibliotecario prestó el libro '" + libroPrestado + "' de la sección '" + seccion + "'."); // Muestra un mensaje indicando que el libro fue prestado con éxito.
        } else {
            System.out.println("No hay libros disponibles en la sección '" + seccion + "'."); // Si no hay libros disponibles en la sección, muestra un mensaje indicando que no hay libros disponibles.
        }
    }

    public static void mostrarLibrosEnBiblioteca() {                             // Función para mostrar los libros en la biblioteca por sección
        if (seccionesExistentes.isEmpty()) {                                          // Comprueba si no hay secciones disponibles en la biblioteca (HashSet).
            System.out.println("No hay secciones disponibles para mostrar libros.");// Si no hay secciones, muestra un mensaje indicando que no hay secciones para mostrar libros y finaliza la función.
            return;
        }
        System.out.println("Libros en la biblioteca por sección:");                 // Imprime un encabezado para indicar que se mostrarán los libros en la biblioteca por sección.
        for (String seccion : seccionesExistentes) {                              // Itera a través de las secciones existentes en la biblioteca.
            Stack<String> seccionDeLibros = seccionesDeLibros.get(seccion);    // Obtiene la pila de libros de la sección actual.

            System.out.println("Sección '" + seccion + "': " + seccionDeLibros);      // Imprime el nombre de la sección seguido de la lista de libros que pertenecen a esa sección.
        }
    }

    public static void mostrarSecciones() {                                    // Función para mostrar las secciones disponibles
        if (seccionesExistentes.isEmpty()) {                                    // Comprueba si no hay secciones disponibles
            System.out.println("No hay secciones disponibles para mostrar."); // Muestra un mensaje si no hay secciones
            return;                                                             // Sale de la función si no hay secciones disponibles
        }
        System.out.println("Secciones disponibles:");                         // Muestra un encabezado indicando que se van a listar las secciones
        for (String seccion : seccionesExistentes) {                            // Itera a través de las secciones existentes del HashSet
            System.out.println("- " + seccion);                                 // Muestra el nombre de cada sección precedido por un guion ("-")
        }
    }

    public static void crearSeccion() {                                        // Función para crear una nueva sección
        System.out.print("Ingrese el nombre de la nueva sección: ");
        String nuevaSeccion = validarEntrada().toUpperCase();                   // Solicita al usuario el nombre de la nueva sección, luego llama a la función validarEntrada()
        // para obtener y validar la entrada, y convierte el nombre a mayúsculas.
        if (seccionesExistentes.contains(nuevaSeccion)) {                     // Comprueba si el conjunto seccionesExistentes (HashSet) ya contiene el nombre de la nueva sección (nuevaSeccion).
            System.out.println("La sección '" + nuevaSeccion + "' ya existe. Intente con otro nombre.");
        } else {
            seccionesDeLibros.put(nuevaSeccion, new Stack<>());              // Crea una nueva pila para la sección y la agrega al mapa seccionesDeLibros (HashMap).
            System.out.println("Sección '" + nuevaSeccion + "' creada.");       // Imprime un mensaje para confirmar la creación exitosa de la sección.
        }
    }

    public static void persistirRegistrosEnJSON() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String json = objectMapper.writeValueAsString(seccionesDeLibros);
            try (FileWriter fileWriter = new FileWriter("registros.json")) {
                fileWriter.write(json);
                System.out.println("Registros guardados en 'registros.json'");
            } catch (IOException e) {
                System.err.println("Error al guardar los registros en JSON: " + e.getMessage());
            }
        } catch (JsonProcessingException e) {
            System.err.println("Error al convertir registros a JSON: " + e.getMessage());
        }
    }

    public static void cargarRegistrosDesdeJSON() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            FileReader fileReader = new FileReader("Library/registros.json");
            Map<String, Stack<String>> registros = objectMapper.readValue(fileReader, new TypeReference<Map<String, Stack<String>>>() {});
            seccionesDeLibros = registros;
            seccionesExistentes = registros.keySet();
            System.out.println("Registros cargados desde 'registros.json'");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al cargar registros desde JSON: " + e.getMessage());
        }
    }


    public static String validarEntrada() {                                    // Función para validar la entrada del usuario y manejar excepciones de entrada/salida
        String entrada = null;
        try {
            entrada = reader.readLine().trim();                                 // Lee una línea de entrada y elimina espacios en blanco alrededor
            if (entrada.isEmpty()) {                                            // Comprueba si la entrada está vacía
                throw new IOException("La entrada no puede estar vacía.");// Lanza una excepción de E/S si la entrada está vacía
            }
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());              // Maneja la excepción de E/S imprimiendo un mensaje de error
        }
        return entrada;                                                         // Devuelve la entrada válida o null si se produce una excepción
    }
}
