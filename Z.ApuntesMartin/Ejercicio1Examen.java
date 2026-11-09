import java.io.File;
import java.util.Scanner;

public class Ejercicio1Examen {
    Scanner sc = new Scanner(System.in);
    public void main(String[] args) {
        String nombreCarpeta = sc.nextLine();
        String nombreArchivo = sc.nextLine();
        
        File dir = new File(nombreCarpeta);
        File archivo1 = new File(dir,nombreArchivo);
        
        if(dir.exists()){
            System.err.println("El directorio ya existe.");
        } else {
            if (dir.mkdir()){
                System.out.println("Directorio creado correctamente.");
            } else {
                System.out.println("Error al crear el directorio.");
            }
        }
         try {
            archivo1.createNewFile();
        } catch (Exception e) {
            System.out.println("Error al crear los archivos: " + e.getMessage());
        }
    }
}
