package views;

public class View {

    public static final String MENU = "Menú:\n1. Agregar libro devuelto\n2. Prestar libro\n3. Mostrar libros en la biblioteca" +
            "\n4. Mostrar secciones\n5. Crear sección\n6. Salir\nElija una opción: ";

    public View() {
    }

    public void showMainMenu() {
        System.out.println(MENU);
    }
}
