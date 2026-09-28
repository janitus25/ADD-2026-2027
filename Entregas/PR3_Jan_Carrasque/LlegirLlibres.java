import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class LlegirLlibres {

    public static void main(String[] args) {
        LlegirObjecteAObjecte();
        LlegirArrayList();
    }

    static void MostrarLlibre(Llibre llibre) {
        System.out.println("Titulo: " + llibre.GetTitulo());
        System.out.println("Autor: " + llibre.GetAutor());
        System.out.println("Genero: " + llibre.GetGenero());
        System.out.println("Paginas: " + llibre.GetPaginas());
        System.out.println("Any: " + llibre.GetAnyPublicacio());
        System.out.println();
    }

    // OPCION 1: leer objeto a objeto
    static void LlegirObjecteAObjecte() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("Llibres_objecte_a_objecte.dat"))) {

            int cantidad = ois.readInt(); // primero leemos cuántos libros hay

            System.out.println("LECTURA OBJETO A OBJETO:");
            for (int i = 0; i < cantidad; i++) {
                Llibre llibre = (Llibre) ois.readObject();
                MostrarLlibre(llibre);
            }

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // OPCION 2: leer el ArrayList entero
    static void LlegirArrayList() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("Llibres_arraylist.dat"))) {

            ArrayList<Llibre> llibres = (ArrayList<Llibre>) ois.readObject();

            System.out.println("\nLECTURA DEL ARRAYLIST:");
            for (Llibre llibre : llibres) {
                MostrarLlibre(llibre);
            }

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}