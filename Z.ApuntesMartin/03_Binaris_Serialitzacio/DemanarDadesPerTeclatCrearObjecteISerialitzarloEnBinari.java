/** Solicita los atributos de un objeto por consola, lo instancia y lo serializa, añadiéndolo al archivo binario de almacenamiento. */
import java.io.*;
import java.util.Scanner;

public class DemanarDadesPerTeclatCrearObjecteISerialitzarloEnBinari {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introdueix el nom: ");
        String nom = sc.nextLine();
        System.out.print("Introdueix l'edat: ");
        int edat = sc.nextInt();

        File fitxer = new File("alumnes.dat");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fitxer, true))) {
            ClasseModelPerSerializable a = new ClasseModelPerSerializable(nom, edat);
            oos.writeObject(a);
            System.out.println("Objecte serialitzat i desat amb èxit.");
        } catch (IOException e) {
            System.err.println("Error en desar l'objecte: " + e.getMessage());
        }
        sc.close();
    }
}