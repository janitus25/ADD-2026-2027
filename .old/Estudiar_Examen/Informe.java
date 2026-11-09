import java.io.File;

public class Informe {
    
    static long suma_bytes=0;
    static int totalElements=0;

    public static void main(String[] args) {
        
        if (args.length == 0) {
            System.out.println("Falta la ruta");
            return;
        }

        File dir = new File(args[0]);

        // isDirectory() es false si no existe O si no es una carpeta
        if(!dir.exists() || !dir.isDirectory()){
            System.out.println("La ruta no existeix o no és un directori");
            return;
        }

        System.out.println("Ruta vàlida: " + dir.getAbsolutePath());

        explorar(dir);

        System.out.println("Total d'elements: " + totalElements);
        System.out.println("Suma de bytes (solo ficheros): " + suma_bytes);

    }

    static void explorar(File dir) {

        File[] elementos = dir.listFiles();

        for (File f : elementos) {
            System.out.print("Nom: " + f.getName());

            if (f.isFile()) {
                System.out.print(" | Fichero | ");
                suma_bytes += f.length();        // solo los ficheros suman bytes
            } else {
                System.out.print(" | Carpeta | ");
            }

            System.out.println(f.length() + " bytes | escribible: " + f.canWrite());
            totalElements++;

            // Recursividad: SOLO con carpetas (con un fichero, listFiles() devuelve null)
            if (f.isDirectory()) {
                explorar(f);
            }
        }
    }

}
