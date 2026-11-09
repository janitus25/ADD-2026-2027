import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class frases {
    
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        escribirFrases();
        leerFichero();
    }


    public static void escribirFrases() {
        // false = sobrescribe lo que hubiera
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("frases.txt", false))) {
            for (int i = 1; i <= 3; i++) {
                System.out.print("Escribe la frase " + i + ": ");
                bw.write(sc.nextLine().trim());
                bw.newLine();
            }
            System.out.println("Frases escritas.");
        } catch (IOException e) {
            System.err.println("Error de escritura: " + e.getMessage());
        }
    }

    public static void leerFichero() {
        File fichero = new File("frases.txt");

        if (!fichero.exists()) {
            System.out.println("Todavía no hay frases.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            int n = 0;
            
            while ((linea = br.readLine()) != null) {
                n++;
                System.out.println(n + ": " + linea);
            }

            if (n == 0) {
                System.out.println("El fichero está vacío.");
            }
        } 
        catch (IOException e) {
            System.err.println("Error de lectura: " + e.getMessage());
        }
    }

}

