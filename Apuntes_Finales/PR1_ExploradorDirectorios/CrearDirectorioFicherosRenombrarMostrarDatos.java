package PR1_ExploradorDirectorios;
import java.io.File;
import java.io.IOException;

public class CrearDirectorioFicherosRenombrarMostrarDatos {

    public static void main(String[] args) {

        File dir = new File("Practica");
        File f1 = new File(dir, "uno.txt");
        File f2 = new File(dir, "dos.txt");
        File f1Nuevo = new File(dir, "uno_nuevo.txt");

        crearDirectorio(dir);
        crearFicheros(f1, f2);
        renombrar(f1, f1Nuevo);

        mostrarDatos(f1Nuevo);              // el renombrado: usar el File NUEVO
        mostrarDatos(f2);
    }

    static void crearDirectorio(File dir) {
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                System.out.println("Directorio creado correctamente");
            } else {
                System.out.println("Error al crear el directorio");
            }
        } else {
            System.out.println("El directorio ya existe");
        }
    }

    static void crearFicheros(File f1, File f2) {
        try {
            System.out.println(f1.createNewFile() ? "Fichero 1 creado" : "Ya existia el fichero 1");
            System.out.println(f2.createNewFile() ? "Fichero 2 creado" : "Ya existia el fichero 2");
        } catch (IOException e) {
            System.err.println("Error creando los ficheros: " + e.getMessage());
        }
    }

    static void renombrar(File origen, File destino) {
        if (origen.renameTo(destino)) {
            System.out.println("Renombrado: " + origen.getName() + " -> " + destino.getName());
        } else {
            System.out.println("No se ha podido renombrar " + origen.getName());
        }
    }

    static void mostrarDatos(File f) {
        System.out.println(f.getName()
                + " | existe: " + f.exists()
                + " | fichero: " + f.isFile()
                + " | tamano: " + f.length() + " bytes");
    }
}