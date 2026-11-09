import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class alumnos {

    public static void main(String[] args) {
        
        leerNotasYDecirlas("alumnos.txt");
    }   


    static void leerNotasYDecirlas(String ruta) {
        File fichero = new File(ruta);

        if (!fichero.exists()) {                    
            System.out.println("El fichero no existe");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            
            String linea;
            int aprobaos=0;
            float media=0;
            int totalAlumnos=0;
            
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(";");
                System.out.println(campos[0] + ": " + campos[1]);

                int notas = Integer.parseInt(campos[1]);
                totalAlumnos++;

                if(notas >=5){
                    aprobaos++;
                }
                media += notas;
                
                
            }

            media = media / totalAlumnos;

            System.out.println("");
            System.out.println("Aprobados: " + aprobaos);
            System.out.println("Nota media: "+ media);

        } 
        
        catch (IOException e) {
            System.err.println("Error de lectura: " + e.getMessage());
        }
    }
    
}
