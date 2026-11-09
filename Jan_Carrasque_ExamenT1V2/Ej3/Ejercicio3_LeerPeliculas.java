import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio3_LeerPeliculas {

    public static void main(String[] args) {
        String rutaFichero = "salida/peliculas.dat";

        // 1. Cargar peliculas desde el fichero binario
        ArrayList<pelicula> listaPeliculas = cargarPeliculas(rutaFichero);

        if (listaPeliculas == null || listaPeliculas.isEmpty()) {
            System.out.println("No se pudieron cargar las peliculas.");
            return;
        }

        // 2. Mostrar todas las peliculas
        mostrarTodasLasPeliculas(listaPeliculas);

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nIntroduce el nombre de un genero a buscar: ");
        String GeneroBuscado = scanner.nextLine();
        buscarPorGenero(listaPeliculas, GeneroBuscado);
        scanner.close();
        
        mostrarDuracionMedia(listaPeliculas);
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<pelicula> cargarPeliculas(String rutaFichero) {
        File fichero = new File(rutaFichero);
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {
            return (ArrayList<pelicula>) ois.readObject();
        } 
        
        catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al leer el fichero: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Método para imprimir la lista completa
    public static void mostrarTodasLasPeliculas(ArrayList<pelicula> peliculas) {
        System.out.println("--- LISTA DE TODAS LAS PELICULAS ---");
        for (pelicula p : peliculas) {
            System.out.println(p);
        }
    }

    public static void buscarPorGenero(ArrayList<pelicula> peliculas, String genero) {
        
        boolean encontrada = false;
        System.out.println("\n--- PELICULAS DEL GENERO: " + genero + " ---");
        
        for (pelicula p : peliculas) {
            if (p.GetGenero().equalsIgnoreCase(genero)) {
                System.out.println(p);
                encontrada = true;
            }
        }
        
        if (!encontrada) {
            System.out.println("No hay peliculas de ese genero");
        }
    }

    public static void mostrarDuracionMedia(ArrayList<pelicula> peliculas) {
        
        if (peliculas.isEmpty()){
            return;
        } 

        float duracionMedia = 0;
        int i=0;

        for (pelicula p : peliculas) {
            duracionMedia += p.GetDuracion();
            i++;
        }

        duracionMedia /=i; 

        System.out.println("\n--- DURACION MEDIA DE LAS PELICULAS ---");
        System.out.println(duracionMedia + " minutos");
    }
}