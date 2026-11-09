/** Pide por teclado una ruta y un texto, crea la estructura de carpetas si hace falta, y añade la frase al final del fichero de texto de forma segura. */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DemanarTextPerTeclatIAfegirloEnModeAppendAUnFitxer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Introdueix la ruta del fitxer de text: ");
        String ruta = sc.nextLine();
        
        System.out.print("Escriu la línia de text que vols afegir: ");
        String text = sc.nextLine();

        File parentDir = new File(ruta).getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true))) {
            bw.write(text);
            bw.newLine();
            System.out.println("Dada desada correctament al fitxer de text.");
        } catch (IOException e) {
            System.err.println("S'ha produït un error d'E/S: " + e.getMessage());
        }
        sc.close();
    }
}