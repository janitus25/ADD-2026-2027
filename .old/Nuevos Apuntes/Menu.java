import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Menu {

    public static void main(String[] args) {
        Menu();
    }

    // =========================
    // MENU
    // =========================
    public static void Menu() {
        BufferedReader teclado = new BufferedReader(new java.io.InputStreamReader(System.in));
        int opcion = 0;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Leer fichero");
            System.out.println("2. Escribir fichero");
            System.out.println("3. Añadir al fichero");
            System.out.println("4. Salir");
            System.out.print("Opcion: ");

            try {
                opcion = Integer.parseInt(teclado.readLine());

                switch (opcion) {
                    case 1:
                        LeerFichero();
                        break;

                    case 2:
                        EscribirFichero();
                        break;

                    case 3:
                        AnadirFichero();
                        break;

                    case 4:
                        System.out.println("Saliendo...");
                        break;

                    default:
                        System.out.println("Opcion incorrecta");
                }

            } catch (IOException e) {
                System.out.println("Error al leer la opcion");
            } catch (NumberFormatException e) {
                System.out.println("Debes introducir un numero");
            }

        } while (opcion != 4);
    }

    // =========================
    // LEER FICHERO
    // =========================
    public static void LeerFichero() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("fichero.txt"));

            String linea;

            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Error al leer el fichero");
        }
    }

    // =========================
    // ESCRIBIR FICHERO
    // =========================
    public static void EscribirFichero() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("fichero.txt"));

            bw.write("Primera linea");
            bw.newLine();
            bw.write("Segunda linea");
            bw.newLine();

            bw.close();

            System.out.println("Fichero escrito correctamente");

        } catch (IOException e) {
            System.out.println("Error al escribir el fichero");
        }
    }

    // =========================
    // AÑADIR AL FICHERO
    // =========================
    public static void AnadirFichero() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("fichero.txt", true));

            bw.write("Nueva linea");
            bw.newLine();

            bw.close();

            System.out.println("Contenido añadido correctamente");

        } catch (IOException e) {
            System.out.println("Error al añadir al fichero");
        }
    }
}
