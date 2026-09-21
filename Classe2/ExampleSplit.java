import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExampleSplit {
    
    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new FileReader("test.txt"))){ //Br es la variable de BufferedReader, antes del bufferedreader hay que hacer un FileReader
            
            String linea;
            int n=0;

            while((linea=br.readLine())!=null){ //Esta linea lee la linea del BufferedReader hasta que sea nula osea que no haya nada, este contenido se vuelca en linea
                n++;
                System.out.println("Linea "+ n +": "+linea);
            }
           br.close();
        } 
        
        // El try intenta acceder al archivo test.txt

        catch (IOException e) {
            System.out.println("Error al leer el archivo: "+ e.getMessage()); //e es la variable de IOException
        }
    }

}
