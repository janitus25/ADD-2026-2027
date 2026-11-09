import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class frases_Con_Menu {
    
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Menu();
    }


    public static void Menu() {
        int opcion = 0;

        do {
            System.out.println("\n===== FRASES =====");
            System.out.println("1. Añadir una frase");
            System.out.println("2. Llistar las frases");
            System.out.println("3. Mostrar cuantas lineas hay");
            System.out.println("4. Sortir");
            System.out.print("Opcio: ");

            try {
                opcion = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                opcion = 0;
            }

            switch (opcion) {
                case 1:
                    escribirFrase();
                    break;
                case 2:
                    leerFichero();
                    break;
                case 3:
                    ContarLineas();
                    break;
                case 4:
                    System.out.println("Adeu!");
                    break;
                default:
                    System.out.println("Opcio incorrecta");
            }

        } while (opcion != 4);
    }


    public static void escribirFrase() {
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("frasess.txt", true))) {
                System.out.print("Escribe la frase: ");
                bw.write(sc.nextLine().trim());
                bw.newLine();
            
            System.out.println("Frases escritas.");
        } 
        
        catch(IOException e) {
            System.err.println("Error de escritura: " + e.getMessage());
        }
    }

    public static void leerFichero() {
        File fichero = new File("frasess.txt");

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


    public static void ContarLineas() {
      
        try (BufferedReader br = new BufferedReader(new FileReader("frasess.txt"))) {
            String linea;
            int n = 0;

            while ((linea = br.readLine()) != null) {
                n++;
            }

            System.out.println("Lineas totales: " + n);

            if (n == 0) {
                System.out.println("El fichero está vacío.");
            }
        } 
        catch (IOException e) {
            System.err.println("Error de lectura: " + e.getMessage());
        }

    }
}

