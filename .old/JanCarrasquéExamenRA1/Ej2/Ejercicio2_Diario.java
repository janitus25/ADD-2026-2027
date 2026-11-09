import java.in.*;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio2_Diario {
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String texto="default";

        while(!texto.equals("FIN")){
            System.out.println("Escribe un texto:");
            texto = sc.nextLine();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("./Salida/diario.txt"))) {
            bw.write(texto);
            System.out.println("Texto guardado correctamente");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        }
        sc.close();
    }
        
        void LeerDiario() {

        try (ObjectInputStream ois = new ObjectInputStream (new FileInputStream(".salida/diario.txt"))){
            
            ArrayList<Ejercicio2_Diario> juego = (ArrayList<Ejercicio2_Diario>) ois.readObject();
            Ejercicio2_Diario masPaginas = juego.get(0);

            for (int i = 0; i < juego.size(); i++) {
                
                if(masPaginas.GetHoras() < juego.get(i).GetHoras()){
                     masPaginas = juego.get(i);
                }
                
            }   

            System.out.println("La pelicula mas larga es: ");
            System.out.println("Titulo: " +  masPaginas.GetTitulo());
            System.out.println("Desarrolladora: " +  masPaginas.GetDesarrolladora());
            System.out.println("Año: " +  masPaginas.GetHoras());
            System.out.println("Horas de juego: " +  masPaginas.GetHoras());
            System.out.println("Valoracion: " +  masPaginas.GetValoracion());
            
        } 

        catch (Exception e) {
            System.err.println("Error" + e.getMessage());
        }

    }
        
}

