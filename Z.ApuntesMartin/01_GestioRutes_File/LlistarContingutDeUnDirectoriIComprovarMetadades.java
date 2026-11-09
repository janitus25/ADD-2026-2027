/** Recorre un directorio específico y lista todos sus archivos y subcarpetas, mostrando las metadatos básicos (si es fichero/directorio y el tamaño en bytes). */
import java.io.File;

public class LlistarContingutDeUnDirectoriIComprovarMetadades {
    public static void main(String[] args) {
        File dir = new File("dades_examen");

        if (dir.isDirectory()) {
            File[] arxius = dir.listFiles();
            if (arxius != null) {
                for (File f : arxius) {
                    System.out.printf("Nom: %s | Es fitxer: %b | Mida: %d bytes%n", 
                            f.getName(), f.isFile(), f.length());
                }
            }
        } else {
            System.out.println("No es un directorio válido.");
        }
    }
}