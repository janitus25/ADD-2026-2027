import java.io.*;

public class Explorar_Directoris {
        static long suma_bytes=0;
        static int totalElements=0;
        
    public static void main(String[] args) {
        
        if(args.length == 0){
            System.out.println("Falta la ruta");
            return;
        }

        File dir = new File(args[0]);

        if(!dir.isDirectory()){
            System.out.println("No existe o no es un directorio.");
            return;
        }

        explorar(dir);
        

        System.out.println("Total d'elements: " + totalElements);
        System.out.println("Suma de bytes: " + suma_bytes);
    }

        public static void explorar(File dir) {

            File[] elementos = dir.listFiles();
        
            for(File f: elementos){
                System.out.print("Nom: " + f.getName());
            
           if(f.isFile()){
                System.out.print(" | Fichero | ");
                suma_bytes += f.length();                 
            }

            else{
                System.out.print(" | Carpeta | ");   
            }
            
            System.out.print(f.length() + " bytes | escribible: ");
            System.out.println(f.canWrite());

            totalElements++;
            
            if(f.isFile()){
                return null;
            }
            else{
               explorar(f);   
            }
        }
    }
}

