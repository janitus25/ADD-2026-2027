/** Comprueba si una ruta existe mediante la clase File; si no es así, crea el directorio completo y un fichero de texto en su interior. */
import java.io.File;
import java.io.IOException;

public class ComprovarSiExisteixRutaICrearDirectoriIFitxer {
    public static void main(String[] args) {
        File dir = new File("dades_examen");
        File fitxer = new File(dir, "clients.txt");

        if (!dir.exists()) {
            dir.mkdirs();
            System.out.println("Directorio creado.");
        }

        try {
            if (fitxer.createNewFile()) {
                System.out.println("Fichero creado con éxito.");
            } else {
                System.out.println("El fichero ya existía.");
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}