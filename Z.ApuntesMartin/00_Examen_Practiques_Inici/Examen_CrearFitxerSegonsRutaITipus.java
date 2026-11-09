/** Programa de examen: Pide al usuario una ruta y nombre, crea los directorios necesarios y genera el archivo (.txt, .dat o .xml) controlando explícitamente IOException y SecurityException. */
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Examen_CrearFitxerSegonsRutaITipus {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== EXAMEN 1: CREACIÓ DINÀMICA DE FITXERS ===");
        System.out.print("Introdueix la ruta del directori (ex: C:/examen o dades/): ");
        String rutaDirectori = sc.nextLine();

        System.out.print("Introdueix el nom del fitxer sense extensió (ex: usuaris): ");
        String nomFitxer = sc.nextLine();

        System.out.println("Tria el tipus de fitxer a crear:");
        System.out.println("1. Text pla (.txt)");
        System.out.println("2. Fitxer Binari (.dat)");
        System.out.println("3. Document XML (.xml)");
        System.out.print("Opció: ");
        int opcio = sc.nextInt();

        String extensio = "";
        switch (opcio) {
            case 1: extensio = ".txt"; break;
            case 2: extensio = ".dat"; break;
            case 3: extensio = ".xml"; break;
            default:
                System.out.println("Opció no vàlida. S'utilitzarà .txt per defecte.");
                extensio = ".txt";
        }

        File directori = new File(rutaDirectori);
        File fitxer = new File(directori, nomFitxer + extensio);

        try {
            // 1. Crear el directori si no existeix
            if (!directori.exists()) {
                if (directori.mkdirs()) {
                    System.out.println("S'ha creat la ruta de directoris: " + directori.getAbsolutePath());
                }
            }

            // 2. Crear el fitxer físic
            if (fitxer.createNewFile()) {
                System.out.println("Fitxer creat amb èxit: " + fitxer.getAbsolutePath());
            } else {
                System.out.println("El fitxer ja existia en aquesta ruta.");
            }

        } catch (IOException e) {
            System.err.println("Error d'Entrada/Sortida (IOException): " + e.getMessage());
        } catch (SecurityException e) {
            System.err.println("Error de permisos del sistema: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}