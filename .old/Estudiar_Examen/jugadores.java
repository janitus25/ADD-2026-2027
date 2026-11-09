import java.io.*;
import java.util.Scanner;

public class jugadores {

    public static void main(String[] args) {
        
        File fichero  = new File("jugadores.txt");

        anadirJugador();
        
        if(!fichero.exists()){
            System.out.println("El fichero no existe");
            return;
        }


        try (BufferedReader br = new BufferedReader(new FileReader("jugadores.txt"))){
            
            String linea;
            String nombreMax = "";
            int puntosMax = 0;
            

            while((linea = br.readLine()) != null){
                String[] campos = linea.split(";"); //El split corta la linea por cada ;
                System.out.println(campos[0] + " | " + campos[1] + " | " + campos[2]);

                int puntos = Integer.parseInt(campos[2]);

                if(puntos > puntosMax){
                    puntosMax = puntos;
                    nombreMax = campos[0];
                }
            }

            System.out.println("Más puntos: " + nombreMax + " con " + puntosMax );
        } 
        
        catch (IOException e) {
            System.err.println("Error el leer el archivo: " + e.getMessage());
        }

    }


    public static void anadirJugador() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Nombre:");
        String nombre = sc.nextLine();

        System.out.println("Nivel:");
        String nivel = sc.nextLine();

        System.out.println("Puntos:");
        String puntos = sc.nextLine();

        String linea = nombre + ";" + nivel + ";" + puntos;

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("jugadores.txt", true))) { //True se usa para ponerlo debajo, con false borrariamos los archivos
            
            bw.write(linea);
            bw.newLine();

        } 
        
        catch (IOException e) {
            System.err.println("Error al escribir: " + e.getMessage());
        
        }
    }

}