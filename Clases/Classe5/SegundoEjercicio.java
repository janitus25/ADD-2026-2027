import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
 
//Tenemos un archivo txt con nombre;edad;cuentaBancaria y tenememos que ver quien tiene mas dinero

public class SegundoEjercicio {
 
    public static void main(String[] args) {
        Scanner teclat = new Scanner(System.in);
        String opcio = "";
 
        while (!opcio.equals("2")) {
            System.out.println("1. Llistar contactes");
            System.out.println("2. Sortir");
            System.out.print("Opció: ");
            opcio = teclat.nextLine();
 
            if (opcio.equals("1")) {
                Llistar();
            }
            else if (!opcio.equals("2")) {
                System.out.println("Opció no vàlida.");
            }
        }
    }   

    public static void Llistar() {
        File fitxer = new File("personas.txt");

        if (!fitxer.exists()) {
            System.out.println("Encara no hi ha contactes.");
            return;
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(fitxer)); // obre el ficher y el prepara per llegir lineas senceres
            String linia;
                
            while ((linia = br.readLine()) != null) { // Llegeix la linea, y la guarda mentre que no sigui null, osigui mentre que no sigui el final de l'arxiu
                String[] camps = linia.split(";"); // Talla la linea pels ;, y els guarda en el array camps 
                System.out.println(camps[0] + " | " + camps[1] + " | " + camps[2]); // Mostrem l'array
            }
            
            br.close();
        } 
        catch (IOException e) {
            System.out.println("Error de lectura: " + e.getMessage());
        }
    }


    
}