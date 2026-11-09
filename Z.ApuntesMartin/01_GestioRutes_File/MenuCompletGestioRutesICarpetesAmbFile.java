/** Menú interactivo por consola que permite al usuario gestionar rutas, crear directorios/ficheros y listar el contenido físico de una carpeta. */
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MenuCompletGestioRutesICarpetesAmbFile {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcio;
        File dir = new File("dades_examen");

        do {
            System.out.println("\n--- GESTIÓN DE RUTAS Y DIRECTORIOS ---");
            System.out.println("1. Crear directorio y fichero");
            System.out.println("2. Listar contenido y metadatos");
            System.out.println("3. Sortir");
            System.out.print("Tria: ");
            opcio = sc.nextInt();

            switch (opcio) {
                case 1:
                    if (!dir.exists()) dir.mkdirs();
                    try {
                        new File(dir, "exemple.txt").createNewFile();
                        System.out.println("Directori i fitxer creats.");
                    } catch (IOException e) { System.err.println(e.getMessage()); }
                    break;
                case 2:
                    if (dir.isDirectory() && dir.listFiles() != null) {
                        for (File f : dir.listFiles()) {
                            System.out.println("- " + f.getName() + " (" + f.length() + " bytes)");
                        }
                    }
                    break;
            }
        } while (opcio != 3);
        sc.close();
    }
}