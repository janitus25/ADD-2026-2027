
import java.io.IOException;
import java.io.RandomAccessFile;


public class EscriureAleatori {
    public static void main(String[] args) {
        String [] nom = {"PEPE", "LUIS", "CARMEN"};
        int [] edad = {20,30,40};
        double[] salario = {1300,2200,6000};

        try(RandomAccessFile f = new RandomAccessFile("empleados.dat", "rw")) {
            
            for(int i=0;i<nom.length;i++){
                StringBuffer buf = new StringBuffer(nom[i]);
                buf.setLength(10);
                f.writeInt(i); // 4 bytes
                f.writeChars(buf.toString()); // 10 caracteres = 20 bytes
                f.writeInt(edad[i]); //4 bytes
                f.writeDouble(salario[i]); // 8 bytes          
            }   
        } 
        
        catch (IOException e) {
            System.err.println("Error" + e.getMessage());
        }
    }
}
