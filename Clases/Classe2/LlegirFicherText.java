import java.io.File;
import java.io.FileReader;

public class LlegirFicherText {
        public static void main(String[] args){
            File dir = new File("./test.txt");

            try{
                FileReader fr = new FileReader(dir);
                int i;

                while((i=fr.read())!=-1){
                    System.out.print((char)i); //Este char es un cast a caracter, para que si o si lo que salga por pantalla sea un caracter, pasamos de numeros a char, esto se repetira hasta que el final de texto de -1
                }
                fr.close();
            }
            catch(Exception e){
                System.out.println("Error al leer los archivos: " + e.getMessage());
            }
        }    
}
