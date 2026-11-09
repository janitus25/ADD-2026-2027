import java.io.File;
import java.io.IOException;

/* 
public class SeguirPresentacion {

    public static void main(String[] args) {
        // a) Creamos un solo fichero
        File f1 = new File("clientes.txt");
    
        // c) Creamos un directorio, y dentro del directorio creamos un fichero
        File dir = new File("C:/dades");
        File f3 = new File(dir, "clients.txt");
    }
*/


/* 
    //llistar el contingut d'un directori
    public class VerDir {

        public static void main(String[] args) {
            File dirr = new File("C:\\Users\\Jan\\Downloads\\files");
            
            if (!dirr.isDirectory()) { // sempre comprovar primer
                System.out.println("No existeix o no és un directori.");
                return;
            }
                
            String[] noms = dirr.list();    
            System.out.println("Elements al directori: " + noms.length);
                
            for (String nom : noms) {
                File f = new File(dirr, nom); // segon File per consultar-lo
                System.out.printf("Fitxer: " + f.isFile() + " | " + "Directori: " + f.isDirectory() + " | " + "Bytes: " + f.length());
                System.out.println("");
            }
        }
        
        
}

//crear un directori, fitxers i reanomenar-los
*/

public class CrearDir {
    public static void main(String[] args) {
        File dir = new File("C:\\Users\\Jan\\Downloads\\files\\PruebaBorrar");
        File f1 = new File(dir, "Fichero1.TXT");
        File f2 = new File(dir, "Fichero2.TXT");
        dir.mkdirs(); // crea tota la ruta si cal
        
        try {
            System.out.println(f1.createNewFile());
            System.out.println(f2.createNewFile());
            f1.renameTo(new File(dir, "Fichero1Rename.txt"));
        } 
        
        catch (IOException e) {
            System.err.println("Error creant els fitxers: " + e.getMessage());
        }
    }
}
