import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio1_Organizador {

    public static void main(String[] args) {
        //Directorio y ficheros creados a mano
        RecorrerYMoverFicheros();
    }

    static void RecorrerYMoverFicheros(){
        
        try (BufferedReader br = new BufferedReader(new FileReader("./Salida"))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
                
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void MostrarCarpetas(){

        
    }
}
