/** Modelo de examen: Pide ruta del fichero y número de registro al usuario, saltando con seek() y gestionando el caso de que la posición se salga del rango. */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Examen_RandomAccessFileAmbPosicionamentSeek {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introdueix la ruta del fitxer d'accés aleatori: ");
        String ruta = sc.nextLine();

        System.out.print("Introdueix el número de registre a buscar (índex inici 0): ");
        int numRegistre = sc.nextInt();

        final int MIDA_REGISTRE = 32;
        File f = new File(ruta);

        try (RandomAccessFile raf = new RandomAccessFile(f, "r")) {
            long posicioByte = (long) numRegistre * MIDA_REGISTRE;

            if (posicioByte >= raf.length()) {
                System.out.println("El registre sol·licitat està fora del rang del fitxer.");
            } else {
                raf.seek(posicioByte); 

                int id = raf.readInt();
                char[] nomChars = new char[10];
                for (int i = 0; i < 10; i++) {
                    nomChars[i] = raf.readChar();
                }
                double salari = raf.readDouble();

                System.out.println("ID: " + id);
                System.out.println("Nom: " + new String(nomChars).trim());
                System.out.println("Salari: " + salari);
            }

        } catch (FileNotFoundException e) {
            System.err.println("El fitxer no s'ha trobat: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error en llegir el fitxer aleatori (IOException): " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}