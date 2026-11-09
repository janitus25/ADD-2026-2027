import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio2_Diario {

    public static void main(String[] args) {
        String rutaFichero = "salida/diario.txt";

        // 1. Pedir texto por consola y guardarlo
        escribirEnDiario(rutaFichero);

        // 2. Leer y analizar el fichero
        leerYProcesarDiario(rutaFichero);
    }

    // Método para pedir líneas al usuario y escribir en el fichero
    public static void escribirEnDiario(String rutaFichero) {
        
        File fichero = new File(rutaFichero);
        File parentDir = fichero.getParentFile();
        
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Escribe las líneas para el diario (escribe FIN para terminar):");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichero, true))) {
            while (true) {
                String linea = scanner.nextLine();
                
                if (linea.equalsIgnoreCase("FIN")) {
                    break;
                }
                bw.write(linea);
                bw.newLine();
            }
        } 
        
        catch (IOException e) {
            System.err.println("Error al escribir en el diario: " + e.getMessage());
        }
    }

    // Método para leer el fichero y mostrar estadísticas
    public static void leerYProcesarDiario(String rutaFichero) {
        File fichero = new File(rutaFichero);
        
        if (!fichero.exists()) {
            System.out.println("El fichero " + rutaFichero + " no existe.");
            return;
        }

        System.out.println("\n--- CONTENIDO DEL DIARIO ---");
        int totalLineas = 0;
        int totalPalabras = 0;
        String lineaMasLarga = "";

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            int numLinea = 1;

            while ((linea = br.readLine()) != null) {
                totalLineas++;
                int palabrasLinea = contarPalabras(linea);
                totalPalabras += palabrasLinea;

                if (linea.length() > lineaMasLarga.length()) {
                    lineaMasLarga = linea;
                }

                System.out.println(numLinea + ": " + linea + " (" + palabrasLinea + " palabras)");
                numLinea++;
            }

        } 
        
        catch (IOException e) {
            System.err.println("Error al leer el diario: " + e.getMessage());
        }

        System.out.println("\n--- RESUMEN FINAL ---");
        System.out.println("Total de líneas: " + totalLineas);
        System.out.println("Total de palabras: " + totalPalabras);
        System.out.println("Línea más larga: \"" + lineaMasLarga + "\"");
    }

    // Método auxiliar para contar las palabras de una cadena de texto
    public static int contarPalabras(String texto) {
        if (texto == null || texto.trim().isEmpty()) { //Si el texto es nulo o, al quitarle los espacios (trim), se queda vacío (isEmpty), frena y devuelve 0
            return 0;
        }
        return texto.trim().split("\\s+").length; // Divide el texto, cuenta los elementos y devuelve el número total
    }
}