// PR2 - Agenda de contactes en fitxer de text
// Palabras clave: BufferedReader, BufferedWriter, FileWriter append, split(";"), menú, Scanner, exists

import java.io.*;
import java.util.Scanner;

public class PR2_AgendaContactes {

    // Paso 1: formato de línea  ->  nom;telefon;correu
    static final String FICHERO = "contactes.txt";

    // UN solo Scanner para todo el programa
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        MostrarContactosCargados();     // "els torni a llegir en arrencar"
        Menu();
    }

    // Paso 2: menú por consola
    public static void Menu() {
        int opcion = 0;

        do {
            System.out.println("\n===== AGENDA =====");
            System.out.println("1. Afegir contacte");
            System.out.println("2. Llistar contactes");
            System.out.println("3. Cercar per nom");
            System.out.println("4. Sortir");
            System.out.print("Opcio: ");

            try {
                opcion = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                opcion = 0;             // fuerza el "opción incorrecta"
            }

            switch (opcion) {
                case 1:
                    anadirContacto();
                    break;
                case 2:
                    leerListaCSV();
                    break;
                case 3:
                    Cercar();
                    break;
                case 4:
                    System.out.println("Adeu!");
                    break;
                default:
                    System.out.println("Opcio incorrecta");
            }

        } while (opcion != 4);
    }

    // Al arrancar: dice cuántos contactos hay ya guardados en el fichero
    public static void MostrarContactosCargados() {
        System.out.println("Contactes guardats: " + contarContactos());
    }

    // Cuenta las líneas válidas. Si el fichero no existe, devuelve 0 (no peta)
    public static int contarContactos() {
        File fichero = new File(FICHERO);
        int total = 0;

        if (!fichero.exists()) {
            return 0;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.split(";").length >= 3) {
                    total++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error de lectura: " + e.getMessage());
        }
        return total;
    }

    // Paso 3: añadir al final (FileWriter con true = append; crea el fichero si no existe)
    public static void anadirContacto() {

        System.out.print("Nom: ");
        String nom = sc.nextLine().trim();
        System.out.print("Telefon: ");
        String telefon = sc.nextLine().trim();
        System.out.print("Correu: ");
        String correu = sc.nextLine().trim();

        // El separador no puede aparecer dentro de un campo: lo cambiamos por una coma
        nom = nom.replace(";", ",");
        telefon = telefon.replace(";", ",");
        correu = correu.replace(";", ",");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHERO, true))) {
            bw.write(nom + ";" + telefon + ";" + correu);
            bw.newLine();
            System.out.println("Contacte afegit.");
        } catch (IOException e) {
            System.err.println("Error d'escriptura: " + e.getMessage());
        }
    }

    // Paso 4: listar con BufferedReader + split(";")
    public static void leerListaCSV() {

        File fichero = new File(FICHERO);

        // Paso 5: el fichero todavía no existe -> aviso, no excepción
        if (!fichero.exists()) {
            System.out.println("Encara no hi ha contactes.");
            return;
        }

        int n = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] c = linea.split(";");

                if (c.length < 3) {         // línea vacía o mal formada: se salta
                    continue;
                }

                System.out.println("Nom: " + c[0] + " | Telefon: " + c[1] + " | Correu: " + c[2]);
                n++;
            }

            if (n == 0) {
                System.out.println("L'agenda esta buida.");
            }

        } catch (IOException e) {
            System.err.println("Error de lectura: " + e.getMessage());
        }
    }

    // Buscar por nombre
    public static void Cercar() {

        File fichero = new File(FICHERO);

        if (!fichero.exists()) {
            System.out.println("Encara no hi ha contactes.");
            return;
        }

        System.out.print("Nom a cercar: ");
        String buscado = sc.nextLine().trim();
        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] c = linea.split(";");

                if (c.length < 3) {
                    continue;
                }

                if (c[0].equalsIgnoreCase(buscado)) {       // sin distinguir mayúsculas
                    System.out.println(c[0] + " | " + c[1] + " | " + c[2]);
                    encontrado = true;
                }
            }

            if (!encontrado) {
                System.out.println("No existeix aquest contacte.");
            }

        } catch (IOException e) {
            System.err.println("Error de lectura: " + e.getMessage());
        }
    }
}