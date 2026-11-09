import java.io.File;
import java.io.IOException;

public class Ejercicio1_PorTamanios {

    public static void main(String[] args) {
        crearArchivosYCarpetas();
        moverArchivosPorTamano();
        mostrarConteoFinal();
    }

    public static void crearArchivosYCarpetas() {
        // Creamos la carpeta de entrada y los archivos de prueba
        File carpetaEntrada = new File("DISCO/archivos");
        
        File ficheroTxt = new File(carpetaEntrada, "informe.txt");
        File ficheroCsv = new File(carpetaEntrada, "datos.csv");
        File ficheroJpg = new File(carpetaEntrada, "foto.JPG");
        File ficheroTxt2 = new File(carpetaEntrada, "notas.txt");
        File ficheroLog = new File(carpetaEntrada, "error.log");
        File ficheroLeeme = new File(carpetaEntrada, "LEEME");
        
        try {
            carpetaEntrada.mkdirs();
            
            ficheroTxt.createNewFile();
            ficheroCsv.createNewFile();
            ficheroJpg.createNewFile();
            ficheroTxt2.createNewFile();
            ficheroLog.createNewFile();
            ficheroLeeme.createNewFile();
            System.out.println("Estructura de entrada creada correctamente.");
        } 
        
        catch (IOException e) {
            System.out.println("Error al crear los ficheros iniciales: " + e.getMessage());
        }

        // Creamos las carpetas de salida de forma preventiva
        new File("DISCO/salida/pequenos").mkdirs();
        new File("DISCO/salida/medianos").mkdirs();
        new File("DISCO/salida/grandes").mkdirs();
    }

    public static void moverArchivosPorTamano() {
        File carpetaEntrada = new File("DISCO/archivos");
        
        File carpetaPequenos = new File("DISCO/salida/pequenos");
        File carpetaMedianos = new File("DISCO/salida/medianos");
        File carpetaGrandes = new File("DISCO/salida/grandes");

        File[] ficheros = carpetaEntrada.listFiles();

        if (ficheros != null) {
            for (File fichero : ficheros) {
                if (fichero.isFile()) {
                    long tamanioBytes = fichero.length(); // Miramos el tamaño directamente
                    File carpetaDestino;

                    // Asignamos la carpeta de destino según los criterios de bytes
                    if (tamanioBytes < 100) {
                        carpetaDestino = carpetaPequenos;
                    } else if (tamanioBytes <= 1000) {
                        carpetaDestino = carpetaMedianos;
                    } else {
                        carpetaDestino = carpetaGrandes;
                    }

                    // Preparamos el archivo de destino final
                    File destinoFinal = new File(carpetaDestino, fichero.getName());

                    // Movemos el fichero
                    if (fichero.renameTo(destinoFinal)) {
                        System.out.println(fichero.getName() + " (" + tamanioBytes + " bytes) movido correctamente.");
                    } else {
                        System.out.println("Error al mover el fichero: " + fichero.getName());
                    }
                }
            }
        }
    }

    public static void mostrarConteoFinal() {
        System.out.println("\n--- ESTADÍSTICAS FINALES ---");
        imprimirConteoCarpeta("pequeños", new File("DISCO/salida/pequenos"));
        imprimirConteoCarpeta("medianos", new File("DISCO/salida/medianos"));
        imprimirConteoCarpeta("grandes", new File("DISCO/salida/grandes"));
    }

    // Función auxiliar segura para contar elementos controlando nulos
    public static void imprimirConteoCarpeta(String nombreCarpeta, File carpeta) {
        File[] archivos = carpeta.listFiles();
        int cantidad = (archivos != null) ? archivos.length : 0;
        System.out.println("Archivos en " + nombreCarpeta + " -> " + cantidad + " fichero(s)");
    }
}