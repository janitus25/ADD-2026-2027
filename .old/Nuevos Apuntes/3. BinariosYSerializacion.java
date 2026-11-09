import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/*
 * APUNTES 3: FICHEROS BINARIOS Y SERIALIZACIÓN DE OBJETOS
 * ------------------------------------------------------------
 * BINARIOS (bytes):
 *   FileOutputStream.write(int)  /  FileInputStream.read()  (devuelve -1 al final)
 *   DataOutputStream: writeInt, writeDouble, writeUTF...
 *   DataInputStream:  readInt,  readDouble,  readUTF...
 *   EL ORDEN ES EL CONTRATO: se lee en el MISMO orden y con los MISMOS tipos que se escribió.
 *
 * SERIALIZACIÓN (objetos):
 *   1) La clase implementa Serializable (interfaz sin métodos).
 *   2) ObjectOutputStream(new FileOutputStream(...)).writeObject(obj)   <- writeObject, NO write
 *   3) ObjectInputStream(new FileInputStream(...)).readObject()         <- devuelve Object: CAST
 *   - readObject() NO avisa del final: lanza EOFException (es la salida normal del bucle).
 *   - EOFException se captura ANTES que IOException (es su subclase).
 *   - readObject() lanza además ClassNotFoundException.
 *   - Abrir un FileOutputStream normal SOBRESCRIBE el fichero.
 *   - No se puede "añadir" con un nuevo ObjectOutputStream sobre un fichero ya escrito
 *     (cada uno escribe una cabecera y luego da StreamCorruptedException al leer).
 *     Solución: leer la lista, añadir, y reescribirlo todo.
 *   - static y transient NO se guardan.
 *
 * Ejecutar:  java BinariosYSerializacion.java
 */
public class BinariosYSerializacion {

    public static void main(String[] args) {
        escribirYLeerBytes();
        escribirYLeerData();

        guardarObjetoPorObjeto();
        leerObjetoPorObjeto();

        ArrayList<Jugador> lista = new ArrayList<>();
        lista.add(new Jugador("Paco", 20, 1000));
        lista.add(new Jugador("Ariel", 21, 5000));
        guardarLista(lista);
        anadirJugador(new Jugador("Carlos", 33, 5));   // lee, añade y reescribe
        leerLista();
    }

    // ============================================================
    // BINARIOS
    // ============================================================

    static void escribirYLeerBytes() {
        File fichero = new File("FitxBytes.dat");

        try (FileOutputStream out = new FileOutputStream(fichero)) {
            for (int i = 1; i < 100; i++) {
                out.write(i);                    // escribe UN byte
            }
        } catch (IOException e) {
            System.err.println("Error escribiendo: " + e.getMessage());
        }

        try (FileInputStream in = new FileInputStream(fichero)) {
            int b;
            while ((b = in.read()) != -1) {      // -1 = fin de fichero
                System.out.print(b + " ");
            }
            System.out.println();
        } catch (IOException e) {
            System.err.println("Error leyendo: " + e.getMessage());
        }
    }

    static void escribirYLeerData() {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("datos.dat"))) {
            dos.writeUTF("Paco");        // String
            dos.writeInt(20);            // 4 bytes
            dos.writeDouble(1000.5);     // 8 bytes
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        try (DataInputStream dis = new DataInputStream(new FileInputStream("datos.dat"))) {
            String nombre = dis.readUTF();     // MISMO orden que al escribir
            int edad = dis.readInt();
            double dinero = dis.readDouble();
            System.out.println(nombre + " " + edad + " " + dinero);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    // ============================================================
    // SERIALIZACIÓN
    // ============================================================

    // Varios objetos uno detrás de otro
    static void guardarObjetoPorObjeto() {
        String[] nombres = {"Ana", "Lluís", "Alícia"};
        int[] niveles = {14, 15, 13};
        int[] puntos = {100, 200, 300};

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("FitxJugador.dat"))) {
            for (int i = 0; i < nombres.length; i++) {          // empieza en 0, no en 1
                Jugador j = new Jugador(nombres[i], niveles[i], puntos[i]);
                oos.writeObject(j);
            }
            System.out.println(nombres.length + " jugadores guardados.");
        } catch (IOException e) {
            System.err.println("Error guardando: " + e.getMessage());
        }
    }

    // Leer hasta que salte EOFException
    static void leerObjetoPorObjeto() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("FitxJugador.dat"))) {
            while (true) {
                Jugador j = (Jugador) ois.readObject();          // cast obligatorio
                System.out.println(j.getNombre() + " | nivel " + j.getNivel() + " | " + j.getPuntos());
            }
        } catch (EOFException e) {
            System.out.println("FIN DE LECTURA");                // salida normal
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error de lectura: " + e.getMessage());
        }
    }

    // Guardar TODA la colección de un solo golpe (ArrayList es Serializable)
    static void guardarLista(ArrayList<Jugador> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("lista.dat"))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            System.err.println("Error guardando la lista: " + e.getMessage());
        }
    }

    // Leer la colección entera (un solo readObject, no hace falta EOFException)
    @SuppressWarnings("unchecked")
    static ArrayList<Jugador> leerLista() {
        ArrayList<Jugador> lista = new ArrayList<>();
        File fichero = new File("lista.dat");

        if (!fichero.exists()) {
            return lista;                                        // lista vacía si no hay fichero
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {
            lista = (ArrayList<Jugador>) ois.readObject();
            for (Jugador j : lista) {
                System.out.println(j.getNombre() + " | " + j.getNivel() + " | " + j.getPuntos());
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error de lectura: " + e.getMessage());
        }
        return lista;
    }

    // "Añadir" a un fichero de objetos = leer todo, añadir y reescribir todo
    static void anadirJugador(Jugador nuevo) {
        ArrayList<Jugador> lista = leerListaSinMostrar();
        lista.add(nuevo);
        guardarLista(lista);
    }

    @SuppressWarnings("unchecked")
    static ArrayList<Jugador> leerListaSinMostrar() {
        File fichero = new File("lista.dat");
        if (!fichero.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {
            return (ArrayList<Jugador>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
    }
}

// ============================================================
// LA CLASE QUE SE GUARDA (en un proyecto real iría en su propio fichero Jugador.java)
// ============================================================
class Jugador implements Serializable {

    // Versión del formato: evita InvalidClassException si cambias la clase
    private static final long serialVersionUID = 1L;

    private String nombre;
    private int nivel;
    private int puntos;
    // Si tuviera un atributo que es otro objeto, ESA clase también debe ser Serializable
    // private transient String password;   // transient = NO se guarda

    public Jugador() { }                      // constructor vacío

    public Jugador(String nombre, int nivel, int puntos) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.puntos = puntos;
    }

    public String getNombre() { return nombre; }
    public int getNivel() { return nivel; }
    public int getPuntos() { return puntos; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setNivel(int nivel) { this.nivel = nivel; }
    public void setPuntos(int puntos) { this.puntos = puntos; }
}
