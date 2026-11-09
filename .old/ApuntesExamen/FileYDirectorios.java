import java.io.File;
import java.io.IOException;

/*
 * APUNTES 1: LA CLASE File
 * ------------------------------------------------------------
 * - File NO contiene datos: solo la RUTA y utilidades para consultarla.
 * - new File("...") NO crea nada en disco.
 * - Usa "/" en las rutas (vale en Windows, Linux y macOS).
 * - Ningún método de File lee el contenido (para eso están los flujos).
 *
 * Ejecutar:  java FileYDirectorios.java <ruta_de_una_carpeta>
 */
public class FileYDirectorios {

    // Variables static (fuera de los métodos) para poder usarlas en el método recursivo
    static long sumaBytes = 0;
    static int totalElements = 0;

    public static void main(String[] args) {

        crearDirectorioYFicheros();   // demo de crear / renombrar

        // La ruta por parámetro llega en args[0]
        if (args.length == 0) {
            System.out.println("Falta la ruta");
            return;
        }

        File dir = new File(args[0]);

        // isDirectory() es false si no existe O si no es una carpeta
        if (!dir.isDirectory()) {
            System.out.println("No existe o no es un directorio.");
            return;
        }

        explorar(dir);

        System.out.println("Total d'elements: " + totalElements);
        System.out.println("Suma de bytes (solo ficheros): " + sumaBytes);
    }

    // ------------------------------------------------------------
    // Las 3 formas de crear un File
    // ------------------------------------------------------------
    static void formasDeCrearFile() {
        File f1 = new File("C:/dades/clients.txt");      // ruta completa en un String
        File f2 = new File("C:/dades", "clients.txt");   // directorio y nombre por separado
        File dir = new File("C:/dades");
        File f3 = new File(dir, "clients.txt");          // desde otro File (directorio)
    }

    // ------------------------------------------------------------
    // Crear directorio y ficheros, comprobar y renombrar
    // mkdir()  -> crea UNA carpeta   | mkdirs() -> crea toda la ruta
    // createNewFile() -> crea el fichero vacío si no existe (lanza IOException)
    // ------------------------------------------------------------
    static void crearDirectorioYFicheros() {
        File dir = new File("NOUDIR");
        File f1 = new File(dir, "FITXER1.TXT");
        File f2 = new File(dir, "FITXER2.TXT");

        if (!dir.exists()) {
            if (dir.mkdirs()) {
                System.out.println("Directorio creado correctamente");
            } else {
                System.out.println("Error al crear el directorio");
            }
        } else {
            System.out.println("El directorio ya existe");
        }

        try {
            // createNewFile() devuelve true si lo ha creado, false si ya existía
            System.out.println(f1.createNewFile() ? "Fichero 1 creado" : "Ya existía el fichero 1");
            System.out.println(f2.createNewFile() ? "Fichero 2 creado" : "Ya existía el fichero 2");
            f1.renameTo(new File(dir, "FITXER1_NOU.TXT"));   // renombrar
        } catch (IOException e) {
            System.err.println("Error creando los ficheros: " + e.getMessage());
        }
    }

    // ------------------------------------------------------------
    // Listar el contenido de un directorio con list() (devuelve String[])
    // list() devuelve null si la ruta no es un directorio -> comprobar antes
    // ------------------------------------------------------------
    static void listarConList(File dir) {
        String[] nombres = dir.list();
        System.out.println("Elementos en el directorio: " + nombres.length);
        for (String nombre : nombres) {
            File f = new File(dir, nombre);   // hay que crear otro File para consultarlo
            System.out.println("Nombre: " + nombre + " | fichero: " + f.isFile()+ " | directorio: " + f.isDirectory() + " | " + f.length() + " bytes");
        }
    }

    // ------------------------------------------------------------
    // PRÁCTICA 1: explorar con listFiles() (devuelve File[]) y RECURSIVO
    // ------------------------------------------------------------
    static void explorar(File dir) {
        File[] elementos = dir.listFiles();

        for (File f : elementos) {
            System.out.print("Nom: " + f.getName());

            if (f.isFile()) {
                System.out.print(" | Fichero | ");
                sumaBytes += f.length();        // solo los ficheros suman bytes
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

    /*
     * CHULETA DE MÉTODOS
     * exists()                       boolean  ¿existe?
     * isFile() / isDirectory()       boolean  ¿fichero o carpeta?
     * getName()                      String   nombre sin ruta
     * getPath() / getAbsolutePath()  String   ruta relativa / completa
     * length()                       long     tamaño en bytes
     * canRead() / canWrite()         boolean  permisos
     * list() / listFiles()           String[] / File[]  contenido
     * mkdir() / mkdirs()             boolean  crear carpeta / toda la ruta
     * createNewFile()                boolean  crear fichero vacío (IOException)
     * renameTo(File) / delete()      boolean  renombrar / borrar
     */
}
