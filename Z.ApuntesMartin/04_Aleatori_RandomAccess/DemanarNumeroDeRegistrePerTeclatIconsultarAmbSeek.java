/** Pide al usuario el índice de un registro, calcula la posición multiplicándolo por los bytes de tamaño fijo, y lee el dato directo saltando con seek(). */
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class DemanarNumeroDeRegistrePerTeclatIconsultarAmbSeek {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int MIDA_REGISTRE = 32; // Ajustado a 4+20+8 según modelo

        System.out.print("Introdueix el nombre de registre que vols consultar (començant per 0): ");
        int nReg = sc.nextInt();

        File fitxer = new File("empleats.dat");
        if (!fitxer.exists()) {
            System.out.println("El fitxer d'accés aleatori no existeix.");
            sc.close();
            return;
        }

        try (RandomAccessFile raf = new RandomAccessFile(fitxer, "r")) {
            long posicio = (long) nReg * MIDA_REGISTRE;

            if (posicio >= raf.length()) {
                System.out.println("Error: Aquest número de registre no existeix (fora de rang).");
            } else {
                raf.seek(posicio);
                int id = raf.readInt();
                char[] nomArr = new char[10];
                for (int i = 0; i < 10; i++) {
                    nomArr[i] = raf.readChar();
                }
                double salari = raf.readDouble();

                System.out.println("--- REGISTRE TROBAT ---");
                System.out.println("ID: " + id);
                System.out.println("Nom: " + new String(nomArr).trim());
                System.out.println("Salari: " + salari);
            }
        } catch (IOException e) {
            System.err.println("Error d'accés aleatori: " + e.getMessage());
        }
        sc.close();
    }
}