/** Crea un objeto Java y lo convierte a formato binario para guardarlo íntegramente en un archivo (.dat) utilizando ObjectOutputStream. */
import java.io.*;

public class EscriureObjectesEnFitxerBinariAmbObjectOutputStream {
    public static void main(String[] args) {
        File fitxer = new File("persones.dat");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fitxer))) {
            oos.writeObject(new ClasseModelPerSerializable("Ana", 20));
            System.out.println("Objecte desat correctament.");
        } catch (IOException e) {
            System.err.println("Error desant: " + e.getMessage());
        }
    }
}