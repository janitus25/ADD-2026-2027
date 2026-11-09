/** Escribe texto en un archivo en modo "append" (añadiendo al final) usando el parámetro 'true' en FileWriter para no sobrescribir el contenido existente. */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EscriureEnUnFitxerDeTextAfegintAlFinalModeAppend {
    public static void main(String[] args) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("equips.txt", true))) {
            bw.write("Girona;932111222");
            bw.newLine();
            System.out.println("Línia afegida correctament.");
        } catch (IOException e) {
            System.err.println("Error d'escriptura: " + e.getMessage());
        }
    }
}