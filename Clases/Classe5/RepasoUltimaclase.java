import java.io.File;
import java.io.IOException;
import java.util.Scanner; 

public class RepasoUltimaclase {


    Scanner sc = new Scanner(System.in);
    
    public void main(String[] args) throws IOException {

        String nombreCarpeta = sc.nextLine();
        String nombreArchivo = sc.nextLine();


        File dir = new File(nombreCarpeta);
        File f1 = new File(dir, nombreArchivo);


        if(dir.exists()){
            System.err.println("El directorio ya existe");
        }

        else{
            if(dir.mkdir()){
                System.out.println("Directorio creado correctamente");
            }

            else{
                 System.out.println("Error al crear el directorio");
            }
        }

        try{
            f1.createNewFile();
        }
        catch(Exception e){
            System.out.println("Error al crear los archivos: " + e.getMessage());
        }
    }
}