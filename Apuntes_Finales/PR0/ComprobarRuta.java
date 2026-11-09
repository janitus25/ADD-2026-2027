import java.io.File;
import java.util.Scanner;

public class ComprobarRuta {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce una ruta:");
        String ruta = sc.nextLine();

        File f = new File(ruta);

        if (f.exists()) {
            if (f.isFile()) {
                System.out.println("Es un fichero");
            } else if (f.isDirectory()) {
                System.out.println("Es un directorio");
            }

            System.out.println("Tamaño: " + f.length() + " bytes");
        } else {
            System.out.println("La ruta no existe");
        }

        sc.close();
    }
}
