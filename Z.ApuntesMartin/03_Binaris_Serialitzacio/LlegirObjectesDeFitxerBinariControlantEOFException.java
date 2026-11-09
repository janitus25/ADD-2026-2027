/** Lee objetos de un fichero binario en bucle y captura la excepción EOFException para detectar el final del archivo de manera controlada sin que el programa se cuelgue. */
import java.io.*;

public class LlegirObjectesDeFitxerBinariControlantEOFException {
    public static void main(String[] args) {
        File fitxer = new File("persones.dat");

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fitxer))) {
            while (true) {
                ClasseModelPerSerializable p = (ClasseModelPerSerializable) ois.readObject();
                System.out.println(p.toString());
            }
        } catch (EOFException e) {
            System.out.println("--- FI DE LECTURA (EOFException controlada) ---");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}