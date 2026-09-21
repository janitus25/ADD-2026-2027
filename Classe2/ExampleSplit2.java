import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ExampleSplit2 {
    String alumnos[][] = new String[100][3];
    int alumnosAprobados = 0;
    int totalSuspendidos = 0;

    // Hay que hacer un analisis de cuantos alumnos han aprovado, cuantos han suspendido, cuantos han suspendido con mas de 100 minutos de estudio, cuantos con menos de 100 minutos de estudio, y lo de cercar por nombre

    public void AlumnosAprobados(){

        

        for (int i = 0; i < alumnos.length; i++) {
            if (Integer.parseInt(alumnos[i][1].trim()) >= 5) {
            alumnosAprobados++;
        }

            System.out.println("Alumnos aprobados: " + alumnosAprobados);
        
    }

    }
    public void main(String[] args) {
        Scanner teclat = new Scanner(System.in);
        String opcio = "";

        try(BufferedReader br = new BufferedReader(new FileReader("test.txt"))){ //Br es la variable de BufferedReader, antes del bufferedreader hay que hacer un FileReader
            
            String linea;
            int i = 0;

            while((linea=br.readLine())!=null){ //Esta linea lee la linea del BufferedReader hasta que sea nula osea que no haya nada, este contenido se vuelca en linea
                i++;
                System.out.println("Linea "+ i +": "+linea);
            }
           br.close();
        } 

        catch (IOException e) {
            System.out.println("Error al leer el archivo: "+ e.getMessage()); //e es la variable de IOException
        }       
    
    }

}


