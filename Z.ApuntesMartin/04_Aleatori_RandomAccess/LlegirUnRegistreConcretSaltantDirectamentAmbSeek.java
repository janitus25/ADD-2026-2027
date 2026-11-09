/** Calcula la posición en bytes de un registro específico y salta a él instantáneamente utilizando el método seek() de RandomAccessFile. */
import java.io.*;

public class LlegirUnRegistreConcretSaltantDirectamentAmbSeek {
    public static void main(String[] args) {
        final int MIDA_REGISTRE = 32; // 4 + 20 + 8 bytes
        int nRegistreDesegut = 0;

        try (RandomAccessFile raf = new RandomAccessFile("empleats.dat", "r")) {
            long posicio = (long) nRegistreDesegut * MIDA_REGISTRE;
            
            if (posicio < raf.length()) {
                raf.seek(posicio);
                
                int id = raf.readInt();
                char[] cog = new char[10];
                for (int i = 0; i < 10; i++) cog[i] = raf.readChar();
                double salari = raf.readDouble();

                System.out.println("ID: " + id + " | Cognom: " + new String(cog).trim() + " | Salari: " + salari);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}