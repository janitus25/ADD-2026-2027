/** Pide una ruta por teclado al usuario, valida su existencia y muestra sus propiedades (tamaño, tipo) o su contenido si se trata de un directorio. */
import java.io.File;
import java.util.Scanner;

public class DemanarRutaIComprovarSiExisteixDirectoriOPrintarMetadades {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introdueix la ruta del directori o fitxer a consultar: ");
        String ruta = sc.nextLine();

        File f = new File(ruta);

        if (!f.exists()) {
            System.out.println("La ruta especificada no existeix al disc.");
            sc.close();
            return;
        }

        System.out.println("Nom: " + f.getName());
        System.out.println("Ruta absoluta: " + f.getAbsolutePath());
        System.out.println("És un directori?: " + f.isDirectory());
        System.out.println("És un fitxer?: " + f.isFile());
        System.out.println("Mida en bytes: " + f.length());

        if (f.isDirectory()) {
            File[] elements = f.listFiles();
            if (elements != null) {
                System.out.println("--- Contingut de la carpeta ---");
                for (File el : elements) {
                    System.out.println("- " + el.getName() + " (" + el.length() + " bytes)");
                }
            }
        }
        sc.close();
    }
}