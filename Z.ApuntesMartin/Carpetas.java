import java.io.File;
import java.io.IOException;

public class Carpetas {
    public static void main(String[] args) {
        // 1. Apuntamos a la carpeta "salida"
        File carpeta = new File("salida");

        // 2. Comprobamos si la carpeta existe. Si no, la creamos
        if (!carpeta.exists()) {
            boolean creada = carpeta.mkdirs(); // Crea la carpeta
            System.out.println("¿Carpeta creada?: " + creada);
        } else {
            System.out.println("La carpeta ya existía.");
        }

        // 3. Apuntamos a un archivo dentro de "salida"
        File archivo = new File(carpeta, "prueba.txt");

        // 4. Intentamos crear el archivo físico si no existe
        try {
            if (!archivo.exists()) {
                boolean creado = archivo.createNewFile(); // Crea el fichero vacío
                System.out.println("¿Archivo creado?: " + creado);
            } else {
                System.out.println("El archivo ya existía.");
            }
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }

        // 5. Comprobaciones de información
        System.out.println("Nombre del archivo: " + archivo.getName());
        System.out.println("Ruta absoluta: " + archivo.getAbsolutePath());
    }
}

/*
 2. Métodos principales para comprobar y crear
 Método	                ¿Qué hace?
 file.exists()	        Devuelve true si el archivo o carpeta existe físicamente en el disco.
 file.isDirectory()	    Devuelve true si la ruta corresponde a una carpeta.
 file.isFile()	        Devuelve true si la ruta corresponde a un fichero.
 file.mkdir()	        Crea una carpeta. Falla si las carpetas superiores no existen.
 file.mkdirs()	        Crea la carpeta y todas las carpetas padre si no existen
 file.createNewFile()	Crea un fichero vacío si no existía previamente. Devuelve true si lo creó.
*/