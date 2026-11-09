import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TercerEjercicio {
    
    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new FileReader("test.txt"))){ //Br es la variable de BufferedReader, antes del bufferedreader hay que hacer un FileReader
            
            String linea;
            int n=0;

            String personas[][] = new String[200][3];

            while((linea=br.readLine())!=null){
                
                String[] persona = linea.split(";");
                
                personas[n][0] = persona[0];
                personas[n][1] = persona[1];
                personas[n][2] = persona[2];
                n++;
            }


            int mayorDinero = Integer.parseInt(personas[n][2]); //Cast
            String nombre;
            int edad;

            while(n<=0){
                
                if(Integer.parseInt(personas[n][2]) > mayorDinero){
                    nombre = personas[n][0];
                    edad =  Integer.parseInt(personas[n][1]);
                    mayorDinero = Integer.parseInt(personas[n][2]);
                }
            }

            System.out.println("La persona con mas dinero es: ");
           br.close();
        } 
        
        // El try intenta acceder al archivo test.txt

        catch (IOException e) {
            System.out.println("Error al leer el archivo: "+ e.getMessage()); //e es la variable de IOException
        }
    }

}
