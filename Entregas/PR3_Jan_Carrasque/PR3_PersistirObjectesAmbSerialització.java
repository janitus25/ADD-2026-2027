import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PR3_PersistirObjectesAmbSerialització {

    public static void main(String[] args) {

        ArrayList<Llibre> llibres = new ArrayList<>(); //Creamos un arrayList de llibre en llibres

        //Usamos el .add para añadir a la "BD" llibres un nuevo libro
        llibres.add(new Llibre("El principito", "Antoine de Saint-Exupery", "Aventura", 96));
        llibres.add(new Llibre("1984", "George Orwell", "Distopia", 328));
        llibres.add(new Llibre("Dracula", "Bram Stoker", "Terror", 418));
        llibres.add(new Llibre("El Hobbit", "J.R.R. Tolkien", "Fantasia", 310));
        llibres.add(new Llibre("La metamorfosis", "Franz Kafka", "Ficcion", 96));
        llibres.add(new Llibre("Frankenstein", "Mary Shelley", "Terror", 280));

        
        // OPCION 1: Guardar objeto a objeto, usando WriteObject para cada libro
        try (ObjectOutputStream oos = new ObjectOutputStream( //El ObjectOutputStream es una herramienta para guardar objetos completos en un archivo
                new FileOutputStream("Llibres_objecte_a_objecte.dat"))) {

            oos.writeInt(llibres.size()); //Guarda el numero de libros

            for (Llibre llibre : llibres) { //El for recorre la lista Llibres, y en cada vuelta coge un libro de la lista, despues con WriteObject usando la variable llibre lo guarda en el archivo
                oos.writeObject(llibre); //Los : Es como decir de, osea en este for quedaria por cada libro de la lista llibres guardalo en la variable llibre despues escribe ese libro en el archivo con WriteObject y repite hasta que no queden libros
            }

            System.out.println("Llibres guardados objeto a objeto.");

        } 
        
        catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        

        // OPCION 2: Guardar el ArrayList entero
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("Llibres_arraylist.dat"))) {

            oos.writeObject(llibres);

            System.out.println("ArrayList guardado correctamente.");

        } 
        
        catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}