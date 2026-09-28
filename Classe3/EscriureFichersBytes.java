import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class EscriureFichersBytes {
    public static void main(String[] args) {
        
        File fitxer = new File("fitxerBytes.dat");

        try (FileOutputStream out = new FileOutputStream(fitxer)) {
            
            for(int i=0;i<100;i++){
                out.write(i); //va a transformar el int i, a 4 bytes encriptados
            }
        } 
                
        catch (IOException e) {
            System.err.println("Error !!!" + e.getMessage());
        }


        try (FileInputStream in = new FileInputStream(fitxer)){
            
            int b;

            while ((b = in.read()) != -1) { 
                System.out.println(b);
            }

        } 
        
        catch (Exception e) {
            System.err.println("Error !!!" + e.getMessage());
        }
    }
}
