import java.io.*;

public class PR1_ExploradorDirectoris {

    // static: así se pueden usar desde los métodos static y en la recursividad
    static long sumaBytes = 0;
    static int totalElements = 0;

    public static void main(String[] args) {

        // 1. Comprobar la ruta. Si falla, se acaba aquí
        if (!ComprobarRuta(args)) {
            return;
        }

        File dir = new File(args[0]);       // ya es seguro usar args[0]

        // 2, 3 y 5. Recorrer, mostrar datos y bajar a las subcarpetas
        RecorrerContingut(dir);

        // 4. Totales
        TotalElementsYSumaMides();
    }

    // Devuelve true si la ruta es válida, false si hay que parar
    public static boolean ComprobarRuta(String[] args) {

        if (args.length == 0) {
            System.out.println("Falta la ruta");
            return false;
        }

        File dir = new File(args[0]);

        if (!dir.isDirectory()) {           // false si no existe O si no es carpeta
            System.out.println("No existe o no es un directorio.");
            return false;
        }

        return true;
    }

    // Recorre una carpeta con listFiles() y baja a las subcarpetas (recursivo)
    public static void RecorrerContingut(File dir) {

        File[] elementos = dir.listFiles();

        if (elementos == null) {            // por ejemplo, sin permisos de lectura
            System.out.println("No se puede leer: " + dir.getName());
            return;
        }

        for (File f : elementos) {
            MostrarDades(f);                // muestra y acumula UN elemento

            if (f.isDirectory()) {          // recursividad SOLO con carpetas
                RecorrerContingut(f);
            }
        }
    }

    // Muestra los datos de un solo elemento y actualiza los contadores
    public static void MostrarDades(File f) {

        System.out.print("Nom: " + f.getName());

        if (f.isFile()) {
            System.out.print(" | Fichero | ");
            sumaBytes += f.length();        // solo los ficheros suman bytes
        } else {
            System.out.print(" | Carpeta | ");
        }

        System.out.println(f.length() + " bytes | escribible: " + f.canWrite());
        totalElements++;
    }

    public static void TotalElementsYSumaMides() {
        System.out.println("Total d'elements: " + totalElements);
        System.out.println("Suma de mides dels fitxers: " + sumaBytes + " bytes");
    }
}