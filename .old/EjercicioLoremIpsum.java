
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class EjercicioLoremIpsum {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        File carpetaSalida = new File("salida");

        if (!carpetaSalida.exists()) {
            carpetaSalida.mkdirs();
        }

        try (BufferedReader br = new BufferedReader(new FileReader("ipsum.txt"))) {

            String linea;
            int cantidadIpsums = 0;
            int cant = 0;
            while ((linea = br.readLine()) != null) {

                System.out.println(linea);

                int posicion = 0;

                while ((posicion = linea.indexOf("ipsum", posicion)) != -1) {
                        cantidadIpsums++;
                        cant+= linea.length();
                    }
                    posicion += "ipsum".length();
                }
            

            System.out.println("Cantidad de Ipsums: " + cantidadIpsums);
            System.out.println("Porcentaje de Ipsums: " + (cantidadIpsums/cant) * 100);


        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}
