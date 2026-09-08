import java.io.File;

public class VerDir {
    public static void main(String[] args) {
        String path = "./";
        File dir = new File(path);

       if(!dir.isDirectory()){
        System.out.println("No es un directorio");
        return;
       }
        System.out.println("Es un directorio y su ruta absoluta es: " + dir.getAbsolutePath());
        String [] files = dir.list();
        System.out.println("Numero de archivos: " + files.length);
        
        for(String file : files){
            System.out.println(file);
            File f = new File(dir, file);
            System.out.printf("\nEs un archiu? %b\n, Tamaño: %d bytes\n", f.isFile(), f.length());
        }
    }
    
    public static void imprimirContenidoCarpeta(File dir){
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