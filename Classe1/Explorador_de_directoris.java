import java.io.File;

public class Explorador_de_directoris {

    static int totalElements = 0; // Variable de classe (static): compartida per tots els mètodes de la classe, no cal instanciar cap objecte per fer-la servir
    static long totalMida = 0;

    public static void main(String[] args){ // String[] args: és un array de text amb els paràmetres que es passen en executar el programa

        // Comprovem que ens han passat un paràmetre
        if (args.length == 0){
            System.out.println("Has d'indicar una ruta com a paràmetre");
            return;
        }

        // Creem l'objecte File amb la ruta rebuda
        File raiz = new File(args[0]);

        // Aquesta comprovació és: si raiz no existeix o raiz no és un directori llavors...
        if(!raiz.exists() || !raiz.isDirectory()){
            System.out.println("La ruta no existeix o no és un directori");
            return;
        }

        System.out.println("Ruta vàlida: " + raiz.getAbsolutePath());

        explorar(raiz); // En lloc d'escriure el for aquí, cridem al mètode

        System.out.println("\n--- RESUM ---");
        System.out.println("Total d'elements: " + totalElements);
        System.out.println("Suma de mides (fitxers): " + totalMida + " bytes");
    }

    static void explorar(File carpeta) {

        File[] elements = carpeta.listFiles(); // Retorna un array d'objectes File, un per cada cosa que hi ha dins de la carpeta (fitxers i subcarpetes, tot junt al mateix array)

        // Si no es pot llegir el contingut (per exemple, per permisos), listFiles() retorna null
        if (elements == null) {
            System.out.println("No s'ha pogut llegir el contingut de: " + carpeta.getPath());
            return;
        }

        for (File i : elements){ // For-each, recorre l'array un a un i en cada volta la variable agafa el valor de l'element actual

            String tipus, escrivible;
            long mida;

            if (i.isDirectory()) {
                tipus = "Carpeta";
                explorar(i); // Recursivitat: entrem dins d'aquesta subcarpeta i repetim tot el procés
            }

            else {
                tipus = "Fitxer";
            }

            if (i.isFile()) {
                mida = i.length();
            }

            else {
                mida = 0;
            }

            if (i.canWrite()) {
                escrivible = "Sí";
            }

            else {
                escrivible = "No";
            }

            System.out.println("Nom: " + i.getName() + " | Tipus: " + tipus + " | Mida: " + mida + " bytes" + " | Escrivible: " + escrivible);

            totalElements++;
            totalMida += mida;
        }
    }
}