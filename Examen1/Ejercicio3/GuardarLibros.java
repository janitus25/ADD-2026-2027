import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GuardarLibros {

    public static void main(String[] args) {
        ArrayList<Libro> libros = generarLibrosEjemplo();
        guardarLibrosEnFichero("salida/libros.dat", libros);
    }

    // Método que genera la lista con 5 libros de prueba
    public static ArrayList<Libro> generarLibrosEjemplo() {
        ArrayList<Libro> lista = new ArrayList<>();
        lista.add(new Libro("Java para todos", 350, "978-1", "Planeta"));
        lista.add(new Libro("Programación Avanzada", 420, "978-2", "Anaya"));
        lista.add(new Libro("Bases de Datos", 280, "978-3", "Planeta"));
        lista.add(new Libro("Sistemas Operativos", 310, "978-4", "Ra-Ma"));
        lista.add(new Libro("Redes de Computadores", 500, "978-5", "McGraw-Hill"));
        return lista;
    }

    // Método que realiza la serialización a disco
    public static void guardarLibrosEnFichero(String rutaFichero, ArrayList<Libro> lista) {
        File fichero = new File(rutaFichero);
        File parentDir = fichero.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero))) {
            oos.writeObject(lista);
            System.out.println("Libros guardados correctamente en: " + rutaFichero);
        } catch (IOException e) {
            System.err.println("Error al guardar los libros: " + e.getMessage());
        }
    }
}