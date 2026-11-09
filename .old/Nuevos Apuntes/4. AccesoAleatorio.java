import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/*
 * APUNTES 4: ACCESO ALEATORIO (RandomAccessFile)
 * ------------------------------------------------------------
 * - Modos: "r" (solo leer) y "rw" (leer y escribir).
 * - seek(pos): se coloca en el byte indicado (la clave de todo).
 * - getFilePointer(): posición actual.   length(): tamaño total en bytes.
 * - readInt/readDouble/readChar y writeInt/writeDouble/writeChars avanzan el puntero.
 * - Hace falta que TODOS los registros midan lo mismo.
 *
 * TAMAÑOS: char = 2 bytes, int = 4 bytes, double = 8 bytes.
 *
 * REGISTRO DE EMPLEADO = 36 bytes
 *   id        int         4 bytes   posición dentro del registro: 0
 *   apellido  char x 10  20 bytes   posición: 4
 *   dept      int         4 bytes   posición: 24
 *   salario   double      8 bytes   posición: 28
 *
 * Posición del registro n (empezando en 0) = n * 36
 * Número de registros = length() / 36
 * El apellido se rellena SIEMPRE a 10 caracteres con StringBuffer.setLength(10).
 *
 * Ejecutar:  java AccesoAleatorio.java
 */
public class AccesoAleatorio {

    static final int MIDA_REGISTRO = 36;
    static final String FICHERO = "emple.dat";

    public static void main(String[] args) {
        escribirRegistros();
        System.out.println("Registros: " + contarRegistros());
        consultarRegistro(2);
        modificarSalario(2, 9999.99);
        consultarRegistro(2);
        consultarRegistro(50);          // no existe -> mensaje, no peta
    }

    // ------------------------------------------------------------
    // Escribir registros de tamaño fijo
    // ------------------------------------------------------------
    static void escribirRegistros() {
        String[] apellido = {"FERNANDEZ", "GIL", "LOPEZ", "RAMOS", "SEVILLA", "REY"};
        int[] dept = {10, 20, 10, 30, 30, 20};
        double[] salario = {1000.45, 2400.60, 3000.0, 1500.56, 2200.0, 2000.0};

        try (RandomAccessFile f = new RandomAccessFile(FICHERO, "rw")) {
            for (int i = 0; i < apellido.length; i++) {
                StringBuffer buf = new StringBuffer(apellido[i]);
                buf.setLength(10);                  // siempre 10 caracteres = 20 bytes

                f.writeInt(i + 1);                  // id       -> 4 bytes
                f.writeChars(buf.toString());       // apellido -> 20 bytes
                f.writeInt(dept[i]);                // dept     -> 4 bytes
                f.writeDouble(salario[i]);          // salario  -> 8 bytes
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    // ------------------------------------------------------------
    // Cuántos registros hay
    // ------------------------------------------------------------
    static long contarRegistros() {
        try (RandomAccessFile f = new RandomAccessFile(FICHERO, "r")) {
            return f.length() / MIDA_REGISTRO;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return 0;
        }
    }

    // ------------------------------------------------------------
    // Consultar el registro n (n empieza en 0) saltando con seek()
    // ------------------------------------------------------------
    static void consultarRegistro(int n) {
        try (RandomAccessFile f = new RandomAccessFile(FICHERO, "r")) {

            long posicion = (long) n * MIDA_REGISTRO;

            if (n < 0 || posicion >= f.length()) {          // controlar que existe
                System.out.println("Ese empleado no existe.");
                return;
            }

            f.seek(posicion);                               // el salto directo

            int id = f.readInt();
            char[] apellido = new char[10];
            for (int i = 0; i < apellido.length; i++) {
                apellido[i] = f.readChar();
            }
            int dept = f.readInt();
            double salario = f.readDouble();

            System.out.printf("ID: %d | %s | dept %d | %.2f EUR%n",
                    id, new String(apellido).trim(), dept, salario);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    // ------------------------------------------------------------
    // Modificar SOLO el salario del registro n, sin tocar los demás
    // inicio del registro + 28 bytes (4 + 20 + 4) hasta llegar al salario
    // ------------------------------------------------------------
    static void modificarSalario(int n, double nuevoSalario) {
        try (RandomAccessFile f = new RandomAccessFile(FICHERO, "rw")) {

            long posicion = (long) n * MIDA_REGISTRO;
            if (n < 0 || posicion >= f.length()) {
                System.out.println("Ese empleado no existe.");
                return;
            }

            f.seek(posicion + 28);
            f.writeDouble(nuevoSalario);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    // ------------------------------------------------------------
    // Añadir un registro al final: seek(length()) y escribir
    // ------------------------------------------------------------
    static void anadirRegistro(int id, String apellido, int dept, double salario) {
        try (RandomAccessFile f = new RandomAccessFile(FICHERO, "rw")) {
            f.seek(f.length());                             // ir al final
            StringBuffer buf = new StringBuffer(apellido);
            buf.setLength(10);
            f.writeInt(id);
            f.writeChars(buf.toString());
            f.writeInt(dept);
            f.writeDouble(salario);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    // ------------------------------------------------------------
    // Recorrer TODOS los registros en orden
    // ------------------------------------------------------------
    static void listarTodos() {
        long total = contarRegistros();
        for (int n = 0; n < total; n++) {
            consultarRegistro(n);
        }
    }
}
