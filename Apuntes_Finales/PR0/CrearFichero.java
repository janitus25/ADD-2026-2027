import java.io.File;
import java.io.IOException;

public class CrearFichero {

    public static void main(String[] args) {

        File f = new File("datos.txt");

        try {
            if (f.createNewFile()) {
                System.out.println("Fichero creado");
            } else {
                System.out.println("Ya existe");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
