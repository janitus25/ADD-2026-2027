import java.io.BufferedReader;
import java.io.FileReader;

public class TrasladarCodigo {
    public static void main(String[] args) {
       try{
            BufferedReader br = new BufferedReader(new FileReader("Writer.txt"));

            String linea;
            while((linea = br.readLine()) != null){
                String[] palabras = linea.split(";");
                if(Integer.parseInt(palabras[1].trim()) >= 5){
                    System.out.println("Alumno Aprobado: " + palabras[0] + ", Nota: " + palabras[1] + " - Minutos de estudio: " + palabras[2]);
                }
                else{
                    System.out.println("Alumno Suspendido: " + palabras[0] + ", Nota: " + palabras[1] + " - Minutos de estudio: " + palabras[2]);
                }
            }
            br.close();
       }catch(Exception e){
            System.out.println("Error al leer en el archivo: " + e.getMessage());
        }
    }
}