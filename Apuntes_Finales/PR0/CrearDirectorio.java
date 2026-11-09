import java.io.File;

public class CrearDirectorio {

    public static void main(String[] args) {

        File carpeta = new File("Archivos");

        if (carpeta.mkdir()) {
            System.out.println("Carpeta creada");
        } else {
            System.out.println("Ya existe o no se ha podido crear");
        }
    }
}
