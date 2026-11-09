import java.io.*;
import java.util.ArrayList;

public class Ejercicio3_GuardarLibros {

    static final String FITXER_LLISTA = "./Salida";

    public static void main(String[] args) {
        ArrayList<Libro> llista = OmplirLlista();

        DesarLlistaSencera(llista);                    
    }

    // Paso 2: ArrayList con seis objetos
    public static ArrayList<Libro> OmplirLlista() {
        ArrayList<Libro> llista = new ArrayList<>();

        llista.add(new Libro("Libro1", 150, "498751P", "Editorial1" ));
        llista.add(new Libro("Libro2", 200, "94878D", "Editorial1"));
        llista.add(new Libro("Libro3", 179, "8774565K", "Editorial3"));
        llista.add(new Libro("Libro4", 97, "9441651I", "Editorial4"));
        llista.add(new Libro("Libro5", 320, "54159948A", "Editorial5"));

        return llista;
    }

    public static void DesarLlistaSencera(ArrayList<Libro> llista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FITXER_LLISTA))) {

            oos.writeObject(llista);
            System.out.println("Llista sencera desada a " + FITXER_LLISTA);

        } catch (IOException e) {
            System.err.println("Error desant la llista: " + e.getMessage());
        }
    }
}