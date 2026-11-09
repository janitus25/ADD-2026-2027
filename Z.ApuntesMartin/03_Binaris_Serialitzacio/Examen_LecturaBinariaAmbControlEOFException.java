/** Ejercicio de examen: Pide la ruta de un fichero binario y lee sus objetos controlando explícitamente el final del archivo (EOFException). */
import java.io.*;
import java.util.Scanner;

public class Examen_LecturaBinariaAmbControlEOFException {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introdueix la ruta del fitxer binari a llegir (.dat): ");
        String ruta = sc.nextLine();

        File fitxer = new File(ruta);

        if (!fitxer.exists()) {
            System.out.println("El fitxer indicat no existeix.");
            sc.close();
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fitxer))) {
            System.out.println("--- CONTINGUT DEL FITXER BINARI ---");
            while (true) {
                ClasseModelPerSerializable p = (ClasseModelPerSerializable) ois.readObject();
                System.out.println(p);
            }
        } catch (EOFException e) {
            System.out.println("--- FI DE FITXER (EOFException capturada correctament) ---");
        } catch (FileNotFoundException e) {
            System.err.println("Fitxer no trobat: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Classe no trobada durant la deserialització: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error general d'E/S (IOException): " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}