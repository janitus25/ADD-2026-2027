/** Abre un archivo de texto y lo lee secuencialmente línea por línea utilizando BufferedReader hasta llegar al final del documento (null). */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LlegirUnFitxerDeTextLiniaALiniaAmbBufferedReader {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("equips.txt"))) {
            String linia;
            while ((linia = br.readLine()) != null) {
                System.out.println(linia);
            }
        } catch (IOException e) {
            System.err.println("Error de lectura: " + e.getMessage());
        }
    }
}