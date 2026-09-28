import java.io.IOException;
import java.io.RandomAccessFile;

public class MostrarEmpleados {

    public static void main(String[] args) {
        
        final int MIDA_REGISTRO = 36;

        try(RandomAccessFile f = new RandomAccessFile("empleados.dat", "r")){
            long posicion = (long) 2 * MIDA_REGISTRO;

            if(posicion >= f.length()){
                System.out.println("USUARIO INEXISTENTE");  
                return;
            }

            f.seek(posicion);
            int id = f.readInt();
            char [] nom = new char[10];

            for(int i=0;i<10;i++){
                nom[i] = f.readChar();
            }
            int edad = f.readInt();
            double salario = f.readDouble();
            System.out.println("Usuario = " + new String(nom).trim() + "edad" + edad + "salario" + salario);

        }

        catch(IOException e){
            System.err.println("Error" + e.getMessage());
        }
    }
}
