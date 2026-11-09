// PR3 - Llegir objectes serialitzats
// Palabras clave: ObjectInputStream, readObject, EOFException, cast, ClassNotFoundException

import java.io.*;
import java.util.ArrayList;

public class PR3_Llegir {

    static final String FITXER_OBJECTES = "llibres_objecte_a_objecte.dat";
    static final String FITXER_LLISTA = "llibres_llista.dat";

    public static void main(String[] args) {
        System.out.println("--- Llegit objecte a objecte ---");
        LlegirObjecteAObjecte();

        System.out.println("\n--- Llegit com a llista sencera ---");
        LlegirLlistaSencera();
    }

    // Se lee en bucle hasta que salta EOFException (la salida NORMAL)
    public static void LlegirObjecteAObjecte() {

        File fitxer = new File(FITXER_OBJECTES);
        if (!fitxer.exists()) {
            System.out.println("No existeix " + FITXER_OBJECTES + ". Executa primer PR3_Desar.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fitxer))) {

            while (true) {
                Llibre l = (Llibre) ois.readObject();       // cast obligatorio
                System.out.println(l);
                // System.out.println("Preu: " + l.getPreu());   // paso 5: descomenta
            }

        } catch (EOFException e) {
            System.out.println("FI DE LECTURA");            // salida normal
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error de lectura: " + e);
        }
    }

    // Un solo readObject devuelve la lista entera: no hace falta EOFException
    @SuppressWarnings("unchecked")
    public static void LlegirLlistaSencera() {

        File fitxer = new File(FITXER_LLISTA);
        if (!fitxer.exists()) {
            System.out.println("No existeix " + FITXER_LLISTA + ". Executa primer PR3_Desar.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fitxer))) {

            ArrayList<Llibre> llista = (ArrayList<Llibre>) ois.readObject();

            for (Llibre l : llista) {
                System.out.println(l);
            }
            System.out.println("Total: " + llista.size() + " llibres");

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error de lectura: " + e);
        }
    }
}