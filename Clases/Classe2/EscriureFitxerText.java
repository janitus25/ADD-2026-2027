import java.io.BufferedWriter;
import java.io.FileWriter;


public class EscriureFitxerText {

    public static void main(String[] args) {
        String[] Lineas = {"Hola, primera linea.", "Segunda linea.", "Tercera línea"}; //En java no hace falta especificar el tamaño del array
   
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("Writter.txt")); //Uso el FileWriter para poder trabajar sobre el archivo caracter a caracter, luego cojemos el BufferedWriter, y 
            for(String linea : Lineas){
                bw.write(linea);
                bw.newLine();
            }
            bw.close();
        }

        catch(Exception e){
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }

    }
}
