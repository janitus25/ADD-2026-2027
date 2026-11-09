import java.io.*;
import java.util.Scanner;

public class Ejercicio3_LeerLibros {

   static final String FITXER_LLISTA = "./Salida";
   static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("\n--- Llegit com a llista sencera ---");
        LlegirLlistaSencera();
    }

    public static void LlegirLlistaSencera() {

        File fitxer = new File(FITXER_LLISTA);
        if (!fitxer.exists()) {
            System.out.println("No existeix " + FITXER_LLISTA);
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fitxer))) {

            while(true){
                Libro libro1 = (Libro) ois.readObject();
                System.out.println("Libro: " + libro1.getPagines());
            
                System.out.println("Total: " + libro1.getPagines() + " pagines");
            }

            

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error de lectura: " + e);
        }

        File fichero = new File("./Salida");

        if (!fichero.exists()) {
            System.out.println("Todavia no hay productos.");
            return;
        }

        System.out.print("Editorial a buscar: ");
        String buscado = sc.nextLine().trim();
        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] c = linea.split("|");

                if (c.length < 3) {
                    continue;
                }

                if (c[0].equalsIgnoreCase(buscado)) {
                    System.out.println(c[0] + " | " + c[1] + " | " + c[2]);
                    encontrado = true;
                }
            }

            if (!encontrado) {
                System.out.println("No hay libros de esa editorial.");
            }

        } catch (IOException e) {
            System.err.println("Error de lectura: " + e.getMessage());
        }


        try (BufferedReader br = new BufferedReader(new FileReader(FITXER_LLISTA))) {
            
            String paginas;
            int n = 0;

            while ((paginas = br.readLine()) != null) {
                n++;
            }

            System.out.println("Paginas totales: " + n);

            if (n == 0) {
                System.out.println("El fichero está vacío.");
            }
        } 
        catch (IOException e) {
            System.err.println("Error de lectura: " + e.getMessage());
        }
    
    }
}