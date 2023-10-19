package presenters;
import models.Model;
import views.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Presenter {


    public static Map<String, Stack<String>> seccionesDeLibros = new HashMap<>();
    public static Set<String> seccionesExistentes = new HashSet<>();
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static View view = new View();
    public static Model model = new Model();

    public Presenter() {
        menu();
    }

    public static void menu(){
        model.cargarRegistrosDesdeJSON(); // Cargar registros desde JSON al iniciar el programa
        while (true) {
            view.showMainMenu();
            String entrada;
            try {
                entrada = reader.readLine().trim();
                int opcion = Integer.parseInt(entrada);
                switch (opcion) {
                    case 1 -> model.agregarLibroDevuelto();
                    case 2 -> model.prestarLibro();
                    case 3 -> model.mostrarLibrosEnBiblioteca();
                    case 4 -> model.mostrarSecciones();
                    case 5 -> model.crearSeccion();
                    case 6 -> {
                        //TODO REVISAR LA PERSISTENCIA DE DATOS EN JSON
                        model.persistirRegistrosEnJSON(); // Guardar registros en JSON antes de salir
                        System.out.println("Saliendo del programa.");
                        return;
                    }
                    default -> System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Debe ingresar un número.");
            } catch (IOException e) {
                System.out.println("Error de entrada/salida: " + e.getMessage());
            }
        }
    }
}
