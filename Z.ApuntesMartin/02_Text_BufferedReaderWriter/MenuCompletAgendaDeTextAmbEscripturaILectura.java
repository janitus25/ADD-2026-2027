/** Aplicación interactiva tipo agenda que permite al usuario elegir entre escribir nuevas líneas en un archivo de texto o leer todo su contenido almacenado. */
import java.io.*;
import java.util.Scanner;

public class MenuCompletAgendaDeTextAmbEscripturaILectura {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String ruta = "agenda.txt";
        int opcio;

        do {
            System.out.println("\n--- AGENDA DE TEXT ---");
            System.out.println("1. Escriure línia (Append)");
            System.out.println("2. Llegir tot (Línia a línia)");
            System.out.println("3. Sortir");
            System.out.print("Tria: ");
            opcio = sc.nextInt();
            sc.nextLine();

            if (opcio == 1) {
                System.out.print("Introdueix text: ");
                String text = sc.nextLine();
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true))) {
                    bw.write(text);
                    bw.newLine();
                } catch (IOException e) { System.err.println(e.getMessage()); }
            } else if (opcio == 2) {
                try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
                    String l;
                    while ((l = br.readLine()) != null) System.out.println(l);
                } catch (FileNotFoundException e) {
                    System.out.println("El fitxer no existeix encara.");
                } catch (IOException e) { System.err.println(e.getMessage()); }
            }
        } while (opcio != 3);
        sc.close();
    }
}