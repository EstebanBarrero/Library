package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class BibliotecaConSecciones_HashMap_HashSet {                             // Declaración de la clase principal

    // Declaración de variables estáticas para almacenar información de la biblioteca
    private static Map<String, Stack<String>> seccionesDeLibros = new HashMap<>();      // Un mapa para asociar secciones con pilas de libros
    private static Set<String> seccionesExistentes = new HashSet<>();                   // Un conjunto para almacenar las secciones existentes
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // Para la entrada de datos

    public static void main(String[] args) {                                    // Método principal del programa
        try {                                                                   // Manejo de excepciones en caso de errores de entrada/salida
            while (true) {                                                      // Bucle infinito para mostrar el menú hasta que se elija la opción de salida
                mostrarMenu();                                                  // Llama a la función para mostrar el menú
                String entrada = reader.readLine().trim();                      // Lee la entrada del usuario y elimina espacios en blanco alrededor
                try {                                                           // Manejo de excepciones en caso de errores de conversión de entrada
                    int opcion = Integer.parseInt(entrada);                   // Convierte la entrada a un número entero

                    switch (opcion) {                                           // Realiza diferentes acciones según la opción seleccionada
                        case 1 ->
                                agregarLibroDevuelto();                             // Caso 1: Llama a la función para agregar un libro devuelto
                        case 2 ->
                                prestarLibro();                                     // Caso 2: Llama a la función para prestar un libro
                        case 3 ->
                                mostrarLibrosEnBiblioteca();                        // Caso 3: Llama a la función para mostrar los libros en la biblioteca
                        case 4 ->
                                mostrarSecciones();                                 // Caso 4: Llama a la función para mostrar las secciones
                        case 5 ->
                                crearSeccion();                                     // Caso 5: Llama a la función para crear una nueva sección
                        case 6 -> {
                            System.out.println("Saliendo del programa.");     // Caso 6: Muestra un mensaje y sale del programa
                            return;
                        }
                        default ->
                                System.out.println("Opción no válida. Intente de nuevo."); // Opción no válida
                    }
                } catch (NumberFormatException e) {                                      // Manejo de excepciones en caso de entrada no numérica
                    System.out.println("Entrada no válida. Debe ingresar un número.");
                }
            }
        } catch (IOException e) {                                                        // Manejo de excepciones en caso de errores de entrada/salida
            System.out.println("Error de entrada/salida: " + e.getMessage());
        }
    }

    private static void mostrarMenu() { // Función para mostrar el menú de opciones
        System.out.println("Menú:");
        System.out.println("1. Agregar libro devuelto");
        System.out.println("2. Prestar libro");
        System.out.println("3. Mostrar libros en la biblioteca");
        System.out.println("4. Mostrar secciones");
        System.out.println("5. Crear sección");
        System.out.println("6. Salir");
        System.out.print("Elija una opción: ");
    }

    private static void agregarLibroDevuelto() {                                        // Manejo de excepciones en caso de errores de entrada/salida

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

    private static void prestarLibro() {                                         // Función para prestar un libro de una sección de la biblioteca
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

    private static void mostrarLibrosEnBiblioteca() {                             // Función para mostrar los libros en la biblioteca por sección
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

    private static void mostrarSecciones() {                                    // Función para mostrar las secciones disponibles
        if (seccionesExistentes.isEmpty()) {                                    // Comprueba si no hay secciones disponibles
            System.out.println("No hay secciones disponibles para mostrar."); // Muestra un mensaje si no hay secciones
            return;                                                             // Sale de la función si no hay secciones disponibles
        }
        System.out.println("Secciones disponibles:");                         // Muestra un encabezado indicando que se van a listar las secciones
        for (String seccion : seccionesExistentes) {                            // Itera a través de las secciones existentes del HashSet
            System.out.println("- " + seccion);                                 // Muestra el nombre de cada sección precedido por un guion ("-")
        }
    }

    private static void crearSeccion() {                                        // Función para crear una nueva sección

        System.out.print("Ingrese el nombre de la nueva sección: ");
        String nuevaSeccion = validarEntrada().toUpperCase();                   // Solicita al usuario el nombre de la nueva sección, luego llama a la función validarEntrada() 
        // para obtener y validar la entrada, y convierte el nombre a mayúsculas.
        if (seccionesExistentes.contains(nuevaSeccion)) {                     // Comprueba si el conjunto seccionesExistentes (HashSet) ya contiene el nombre de la nueva sección (nuevaSeccion).

            System.out.println("La sección '" + nuevaSeccion + "' ya existe. Intente con otro nombre.");
        } else {
            seccionesExistentes.add(nuevaSeccion);                            // Verifica si la nueva sección ya existe en el conjunto seccionesExistentes y muestra un mensaje si es el caso. Si no existe, agrega la nueva sección al conjunto.
            seccionesDeLibros.put(nuevaSeccion, new Stack<>());              // Crea una nueva pila para la sección y la agrega al mapa seccionesDeLibros (HashMap).
            System.out.println("Sección '" + nuevaSeccion + "' creada.");       // Imprime un mensaje para confirmar la creación exitosa de la sección.
        }
    }

    private static String validarEntrada() {                                    // Función para validar la entrada del usuario y manejar excepciones de entrada/salida
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