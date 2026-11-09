import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio2_Tareas {

    public static void main(String[] args) {
        String rutaFichero = "salida/tareas.txt";

        // 1. Pedir texto por consola y guardarlo
        escribirTareas(rutaFichero);

        // 2. Leer y analizar el fichero
        LeerTareas(rutaFichero);
    }

    // Método para pedir líneas al usuario y escribir en el fichero
    public static void escribirTareas(String rutaFichero) {
        
        File fichero = new File(rutaFichero);
        File parentDir = fichero.getParentFile();
        
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Escribe las líneas para las tareas (escribe SALIR para terminar):");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichero, true))) {
            while (true) {
                String linea = scanner.nextLine();
                
                if (linea.equalsIgnoreCase("SALIR")) {
                    break;
                }
                bw.write(linea);
                bw.newLine();
            }
        } 
        
        catch (IOException e) {
            System.err.println("Error al escribir en las tareas: " + e.getMessage());
        }
    }

    // Método para leer el fichero y mostrar estadísticas
    public static void LeerTareas(String rutaFichero) {
        File fichero = new File(rutaFichero);
        
        if (!fichero.exists()) {
            System.out.println("El fichero " + rutaFichero + " no existe.");
            return;
        }

        System.out.println("\n--- CONTENIDO DE LAS TAREAS ---");
        int totalTareas = 0;
        int totalPalabras = 0;
        String tareaMasCorta = "";

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            int numLinea = 1;

            while ((linea = br.readLine()) != null) {
                totalTareas++;
                int palabrasLinea = contarCaracteres(linea);
                totalPalabras += palabrasLinea;

                if (linea.length() < tareaMasCorta.length()) {
                    tareaMasCorta = linea;
                }

                System.out.println(numLinea + ": " + linea + " (" + palabrasLinea + " palabras)");
                numLinea++;
            }

        } 
        
        catch (IOException e) {
            System.err.println("Error al leer las tareas: " + e.getMessage());
        }

        System.out.println("\n--- RESUMEN FINAL ---");
        System.out.println("Total de tareas: " + totalTareas);
        System.out.println("Tarea más corta: \"" + tareaMasCorta + "\"");
        System.out.println("Total de palabras: " + totalPalabras);
    }

    public static int contarCaracteres(String texto) {
        if (texto == null || texto.trim().isEmpty()) { 
            return 0;
        }
        return texto.trim().split("\\s+").length; 
    }
}