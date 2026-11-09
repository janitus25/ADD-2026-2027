import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class LeerLibros {

    public static void main(String[] args) {
        String rutaFichero = "salida/libros.dat";

        // 1. Cargar libros desde el fichero binario
        ArrayList<Libro> listaLibros = cargarLibros(rutaFichero);

        if (listaLibros == null || listaLibros.isEmpty()) {
            System.out.println("No se pudieron cargar los libros.");
            return;
        }

        // 2. Mostrar todos los libros
        mostrarTodosLosLibros(listaLibros);

        // 3. Buscar libros por editorial
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nIntroduce el nombre de una editorial a buscar: ");
        String editorialBuscada = scanner.nextLine();
        buscarPorEditorial(listaLibros, editorialBuscada);
        scanner.close();

        // 4. Mostrar el libro con más páginas
        mostrarLibroMasPaginas(listaLibros);
    }

    // Método para desensamblar/deserializar el objeto ArrayList
    @SuppressWarnings("unchecked")
    public static ArrayList<Libro> cargarLibros(String rutaFichero) {
        File fichero = new File(rutaFichero);
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {
            return (ArrayList<Libro>) ois.readObject();
        } 
        
        catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al leer el fichero: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Método para imprimir la lista completa
    public static void mostrarTodosLosLibros(ArrayList<Libro> libros) {
        System.out.println("--- LISTA DE TODOS LOS LIBROS ---");
        for (Libro l : libros) {
            System.out.println(l);
        }
    }

    // Método para filtrar la lista por la editorial especificada
    public static void buscarPorEditorial(ArrayList<Libro> libros, String editorial) {
        
        boolean encontrada = false;
        System.out.println("\n--- LIBROS DE LA EDITORIAL: " + editorial + " ---");
        
        for (Libro l : libros) {
            if (l.getEditorial().equalsIgnoreCase(editorial)) {
                System.out.println(l);
                encontrada = true;
            }
        }
        
        if (!encontrada) {
            System.out.println("No hay libros de esa editorial");
        }
    }

    // Método para buscar e imprimir el libro con más páginas
    public static void mostrarLibroMasPaginas(ArrayList<Libro> libros) {
        
        if (libros.isEmpty()) return;

        Libro masPaginas = libros.get(0);
        for (Libro l : libros) {
            if (l.getPaginas() > masPaginas.getPaginas()) {
                masPaginas = l;
            }
        }

        System.out.println("\n--- LIBRO CON MÁS PÁGINAS ---");
        System.out.println(masPaginas);
    }
}