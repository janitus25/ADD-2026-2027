import java.io.*;

public class Eercicio2Repaso {
    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new FileReader("datos.txt"))){

            String linea;
            
            int n=0;
            String personas[][] = new String[100][3];

            while((linea = br.readLine()) != null){
            String[] persona = linea.split(";");
                personas[n][0] = persona[0];
                personas[n][1] = persona[1];
                personas[n][2] = persona[2];
                n++;
            }
            
            String nombre;
            int edad = Integer.parseInt(personas[n][1]);
            int mayorDinero = Integer.parseInt(personas[n][2]);

            while (n<=0){
                if (Integer.parseInt(personas[n][2]) > mayorDinero){
                    nombre = personas[n][0];
                    edad = Integer.parseInt(personas[n][1]);
                    mayorDinero = Integer.parseInt(personas[n][2]);
                }
            }

            System.out.println;
            br.close();

        } catch (IOException e) {
        }
    }
}