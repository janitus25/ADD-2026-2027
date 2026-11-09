/** Escribe registros calculando un tamaño fijo exacto (ej. 32 bytes por registro) en un RandomAccessFile para permitir futuras búsquedas directas. */
import java.io.*;

public class EscriureRegistresDeMidaFixaAmbRandomAccessFile {
    public static void main(String[] args) {
        try (RandomAccessFile raf = new RandomAccessFile("empleats.dat", "rw")) {
            int id = 1;
            String cognom = "FERNANDEZ";
            StringBuffer buf = new StringBuffer(cognom);
            buf.setLength(10); // 10 caràcters = 20 bytes
            double salari = 1500.00;

            raf.writeInt(id);         // 4 bytes
            raf.writeChars(buf.toString()); // 20 bytes
            raf.writeDouble(salari);  // 8 bytes
            System.out.println("Registre de mida fixa escrit.");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}