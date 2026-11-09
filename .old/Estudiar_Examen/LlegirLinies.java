import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LlegirLinies {
    public static void main(String[] args) {
        // El recurs declarat entre parèntesis es tanca sol, passi el que passi
        try (BufferedReader br = new BufferedReader(new FileReader("prueba.txt"))) {
            String linia;
            int n = 0;
        
            while ((linia = br.readLine()) != null) { // null = fi de fitxer
                n++;
                System.out.println("    " + n + ": " + linia);
            }
        } 
        
        catch (IOException e) {
            System.err.println("Error de lectura: " + e.getMessage());
        }
    }
}