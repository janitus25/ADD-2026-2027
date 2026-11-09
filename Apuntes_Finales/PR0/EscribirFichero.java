import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EscribirFichero {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Escribe un texto:");
        String texto = sc.nextLine();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("datos.txt"))) {
            bw.write(texto);
            System.out.println("Texto guardado correctamente");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }

    void LeerFichero(){
        try (BufferedReader br = new BufferedReader(new FileReader("datos.txt"))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
}
