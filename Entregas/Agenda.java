import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
 
public class Agenda {
 
    public static void main(String[] args) {
        Scanner teclat = new Scanner(System.in);
        String opcio = "";
 
        while (!opcio.equals("4")) {
            System.out.println("\n1. Afegir contacte");
            System.out.println("2. Llistar contactes");
            System.out.println("3. Cercar per nom");
            System.out.println("4. Sortir");
            System.out.print("Opció: ");
            opcio = teclat.nextLine();
 
            if (opcio.equals("1")) {
                Afegir(teclat);
            } else if (opcio.equals("2")) {
                Llistar();
            } else if (opcio.equals("3")) {
                Cercar(teclat);
            } else if (!opcio.equals("4")) {
                System.out.println("Opció no vàlida.");
            }
        }
    }   

    public static void Afegir(Scanner teclat) {
        
        System.out.print("Nom: ");
        String nom = teclat.nextLine();
        System.out.print("Telèfon: ");
        String telefon = teclat.nextLine();
        System.out.print("Correu: ");
        String correu = teclat.nextLine();   
        
        try {
            FileWriter fw = new FileWriter("contactes.txt", true);
            fw.write(nom + ";" + telefon + ";" + correu + "\n");
            fw.close();
            System.out.println("Contacte afegit.");
        } catch (IOException e) {
            System.out.println("Error d'escriptura: " + e.getMessage()); //El e.getMessage, es un metode que retorna una descripcio detallada de l'error
        }
    }

    public static void Llistar() {
        File fitxer = new File("contactes.txt");

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

    public static void Cercar(Scanner teclat) {
        File fitxer = new File("contactes.txt");
 
        if (!fitxer.exists()) {
            System.out.println("Encara no hi ha contactes.");
            return;
        }
 
        System.out.print("Nom a cercar: ");
        String cerca = teclat.nextLine();
        boolean trobat = false;
 
        try {
            BufferedReader br = new BufferedReader(new FileReader(fitxer));
            String linia;
            
            while ((linia = br.readLine()) != null) {
                String[] camps = linia.split(";");
                
                if (camps[0].toLowerCase().contains(cerca.toLowerCase())) { // Mira si el nom conté el text que busquem, ho pasem a minuscules per evitar errors
                    System.out.println(camps[0] + " | " + camps[1] + " | " + camps[2]);
                    trobat = true; //Si el conté es que hi ha una coincidencia y hem trobat un contacte
                }
            }
            
            br.close();
        
        } catch (IOException e) {
            System.out.println("Error de lectura: " + e.getMessage());
        }
 
        if (!trobat) {
            System.out.println("No s'ha trobat cap contacte.");
        }
    }
}