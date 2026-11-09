// PR3 - Desar objectes amb serialització
// Palabras clave: ObjectOutputStream, writeObject, Serializable, ArrayList, .dat

import java.io.*;
import java.util.ArrayList;

public class PR3_Desar {

    static final String FITXER_OBJECTES = "llibres_objecte_a_objecte.dat";
    static final String FITXER_LLISTA = "llibres_llista.dat";

    public static void main(String[] args) {
        ArrayList<Llibre> llista = OmplirLlista();      // paso 2

        DesarObjecteAObjecte(llista);                   // paso 3, opción A
        DesarLlistaSencera(llista);                     // paso 3, opción B
    }

    // Paso 2: ArrayList con seis objetos
    public static ArrayList<Llibre> OmplirLlista() {
        ArrayList<Llibre> llista = new ArrayList<>();

        llista.add(new Llibre("Dune", "Frank Herbert", "Ciencia ficcio", 600));
        llista.add(new Llibre("1984", "George Orwell", "Distopia", 350));
        llista.add(new Llibre("El Hobbit", "J.R.R. Tolkien", "Fantasia", 310));
        llista.add(new Llibre("Solitud", "Victor Catala", "Novella", 280));
        llista.add(new Llibre("Fundacio", "Isaac Asimov", "Ciencia ficcio", 255));
        llista.add(new Llibre("Drakula", "Bram Stoker", "Terror", 418));

        return llista;
    }

    // Opción A: objeto a objeto (un writeObject por cada libro)
    public static void DesarObjecteAObjecte(ArrayList<Llibre> llista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FITXER_OBJECTES))) {

            for (Llibre l : llista) {
                oos.writeObject(l);
            }
            System.out.println(llista.size() + " llibres desats objecte a objecte a " + FITXER_OBJECTES);

        } catch (IOException e) {
            System.err.println("Error desant objecte a objecte: " + e.getMessage());
        }
    }

    // Opción B: la lista entera de un solo writeObject (ArrayList ya es Serializable)
    public static void DesarLlistaSencera(ArrayList<Llibre> llista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FITXER_LLISTA))) {

            oos.writeObject(llista);
            System.out.println("Llista sencera desada a " + FITXER_LLISTA);

        } catch (IOException e) {
            System.err.println("Error desant la llista: " + e.getMessage());
        }
    }
}