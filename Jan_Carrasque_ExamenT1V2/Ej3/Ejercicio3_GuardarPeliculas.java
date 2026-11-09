import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Ejercicio3_GuardarPeliculas {
    
    public static void main(String[] args) {
        ArrayList<pelicula> peliculas = generarPeliculasEjemplo();
        guardarPeliculasEnFichero("salida/peliculas.dat", peliculas);
    }

    public static ArrayList<pelicula> generarPeliculasEjemplo() {
        ArrayList<pelicula> lista = new ArrayList<>();
        lista.add(new pelicula("Titanic", 120, 2000, "Aventura"));
        lista.add(new pelicula("Jumangi", 160, 2010, "Aventura"));
        lista.add(new pelicula("Asistenta", 90, 2025, "Suspense"));
        lista.add(new pelicula("Viernes", 143, 1999, "Terror"));
        lista.add(new pelicula("Regreso al futuro", 124, 2005, "Magia"));
        return lista;
    }

    public static void guardarPeliculasEnFichero(String rutaFichero, ArrayList<pelicula> lista) {
        
        File fichero = new File(rutaFichero);
        File parentDir = fichero.getParentFile();
        
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero))) {
            oos.writeObject(lista);
            System.out.println("Peliculas guardadas correctamente en: " + rutaFichero);
        } catch (IOException e) {
            System.err.println("Error al guardar las peliculas: " + e.getMessage());
        }
    }

}
