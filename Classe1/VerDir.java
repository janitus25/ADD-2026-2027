import java.io.File; //Libreria de ficheros

public class VerDir { //El nombre debe ser el mismo que el del archivo
    public static void main(String[] args) {
        String path = "./"; //Esto crea un objeto File que apunta a la carpeta actual (./ significa "aquí mismo donde se ejecuta el programa").
        File dir = new File(path); //Esto crea un objeto File que apunta a la carpeta actual (./ significa "aquí mismo donde se ejecuta el programa").

       if(!dir.isDirectory()){ //Si dir no es una carpeta, avisa y return corta la ejecución del programa ahí mismo (sale del main).
        System.out.println("No es un directorio");
        return;
       }
        System.out.println("Es un directorio y su ruta absoluta es: " + dir.getAbsolutePath()); //Imprime la ruta de la carpeta.
        String [] files = dir.list(); //files es un array de nombres (texto) de todo lo que hay dentro de dir. files.length es cuántos elementos tiene ese array.
        System.out.println("Numero de archivos: " + files.length);
        
        for(String file : files){ //Este for recorre cada nombre. Como file es solo texto (ej: "archivo1.txt"), para poder preguntar cosas como el tamaño necesitas volver a construir un objeto File combinando la carpeta (dir) + el nombre (file). Por eso se hace new File(dir, file).
            System.out.println(file);
            File f = new File(dir, file);
            System.out.printf("\nEs un archiu? %b\n, Tamaño: %d bytes\n", f.isFile(), f.length()); //printf imprime con un formato: %b es un booleano (true/false), %d es un número entero. Aquí pregunta si f es archivo (isFile()) y cuánto pesa (length()).
        }
    }
    
    public static void imprimirContenidoCarpeta(File dir){ //Metodo recursividad
            String [] files = dir.list();
            
            for(String file : files){
                System.out.println(file);
                File f = new File(dir, file);
                System.out.printf("\nEs un archiu? %b\n, Tamaño: %d bytes\n", f.isFile(), f.length());

                if(f.isDirectory()){
                    imprimirContenidoCarpeta(f);
                }
            }
    }
}