import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Contactes {
    
    public static void main(String[] args) {        
        Menu();
    }

    public static void Menu() {
        Scanner sc = new Scanner (System.in);
        int opcion = 0;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Afegir al ficher");
            System.out.println("2. Llistar contingut");
            System.out.println("3. Cercar per nom");
            System.out.println("4. Salir");
            System.out.print("Opcion: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1:
                        anadirContacto();
                        break;

                    case 2:
                        leerListaCSV();
                        break;

                    case 3:
                        Cercar();
                        break;

                    case 4:
                        System.out.println("Saliendo...");
                        break;

                    default:
                        System.out.println("Opcion incorrecta");
                }

            } 
            catch (NumberFormatException e) {
                System.out.println("Debes introducir un numero");
            }

        } while (opcion != 4);
    }

    static void anadirContacto() {

        Scanner sc = new Scanner(System.in);
        
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();
        System.out.print("Correo: ");
        String correo = sc.nextLine();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("contactes.txt", true))) {
            bw.write(nombre + ";" + telefono + ";" + correo);
            bw.newLine();
        } 
        
        catch (IOException e) {
            System.err.println("Error al escribir: " + e.getMessage());
        }

    }

    public static void leerListaCSV(){

        File fichero = new File("contactes.txt");

        if (!fichero.exists()) {
            System.out.println("Todavía no hay contactos.");
            return;
        }

        try (BufferedReader br = new BufferedReader(
                new FileReader("contactes.txt"))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] camps = linea.split(";");

                System.out.print("Nom: " + camps[0] + " | ");
                System.out.print("Telefon: " + camps[1] + " | ");
                System.out.print("Correu: " + camps[2]);
                System.out.println();
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public static void Cercar(){

        Scanner sc = new Scanner (System.in);

        File fichero = new File("contactes.txt");
        
        if (!fichero.exists()) {
            System.out.println("Todavía no hay contactos.");
            return;
        }

        System.out.print("Nombre a buscar: ");
        String buscado = sc.nextLine();
        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            
            String linea;
            
            while ((linea = br.readLine()) != null) {
                String[] c = linea.split(";");
                
                if (c[0].equals(buscado)) {
                    System.out.println(c[0] + " | " + c[1] + " | " + c[2]);
                    encontrado = true;
                }
            }
            if (!encontrado) {
                System.out.println("No existe ese contacto.");
            }

        } catch (IOException e) {
            System.err.println("Error de lectura: " + e.getMessage());
        }

    }
}
