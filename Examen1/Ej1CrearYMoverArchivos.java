import java.io.File;
import java.io.IOException;

public class Ej1CrearYMoverArchivos {

    public static void main(String[] args) {
        crearArchivosYCarpetas();
        moverArchivos();
        mostrarConteoFinal();
    }

    public static void crearArchivosYCarpetas() {
        // Creamos la carpeta y los archivos
        File carpeta = new File("PROYECTO/entrada");
        
        File ficheroTxt = new File(carpeta, "informe.txt");
        File ficheroCsv = new File(carpeta, "datos.csv");
        File ficheroJpg = new File(carpeta, "foto.JPG");
        File ficheroTxt2 = new File(carpeta, "notas.txt");
        File ficheroLog = new File(carpeta, "error.log");
        File ficheroLeeme= new File(carpeta, "LEEME");
        
        try {
            carpeta.mkdirs();
            
            ficheroTxt.createNewFile();
            ficheroCsv.createNewFile();
            ficheroJpg.createNewFile();
            ficheroTxt2.createNewFile();
            ficheroLog.createNewFile();
            ficheroLeeme.createNewFile();
        } 
        
        catch (IOException e) {
            System.out.println("Error al crear el fichero");
        }

        // Creamos las carpetas de destino
        File carpetaTxt = new File("PROYECTO/salida/txt");
        carpetaTxt.mkdirs();

        File carpetaCsv = new File("PROYECTO/salida/csv");
        carpetaCsv.mkdirs();

        File carpetaJpg = new File("PROYECTO/salida/jpg");
        carpetaJpg.mkdirs();

        File carpetaLog = new File("PROYECTO/salida/log");
        carpetaLog.mkdirs();

        File carpetaSinExtension = new File("PROYECTO/salida/sin_extension");
        carpetaSinExtension.mkdirs();
    }

    public static void moverArchivos() {
        File carpeta = new File("PROYECTO/entrada");
        
        File ficheroTxt = new File(carpeta, "informe.txt");
        File ficheroCsv = new File(carpeta, "datos.csv");
        File ficheroJpg = new File(carpeta, "foto.JPG");
        File ficheroTxt2 = new File(carpeta, "notas.txt");
        File ficheroLog = new File(carpeta, "error.log");
        File ficheroLeeme= new File(carpeta, "LEEME");

        File carpetaTxt = new File("PROYECTO/salida/txt");
        File carpetaCsv = new File("PROYECTO/salida/csv");
        File carpetaJpg = new File("PROYECTO/salida/jpg");
        File carpetaLog = new File("PROYECTO/salida/log");
        File carpetaSinExtension = new File("PROYECTO/salida/sin_extension");

        // Movemos los archivos TXT
        File destinoTxt = new File(carpetaTxt, "informe.txt");
        File destinoTxt2 = new File(carpetaTxt, "notas.txt");
        
        if (ficheroTxt.renameTo(destinoTxt)) {
            System.out.println("informe.txt movido correctamente");
        } 
        
        else {
            System.out.println("Error al mover informe.txt");
        }

        if (ficheroTxt2.renameTo(destinoTxt2)) {
            System.out.println("notas.txt movido correctamente");
        } 
        
        else {
            System.out.println("Error al mover notas.txt");
        }

        File destinoCsv = new File(carpetaCsv, "datos.csv");

        if (ficheroCsv.renameTo(destinoCsv)) {
            System.out.println("datos.csv movido correctamente");
        } 
        
        else {
            System.out.println("Error al mover datos.csv");
        }

        File destinoJpg = new File(carpetaJpg, "foto.JPG");

        if (ficheroJpg.renameTo(destinoJpg)) {
            System.out.println("foto.JPG movido correctamente");
        } 
        
        else {
            System.out.println("Error al mover foto.JPG");
        }

        File destinoLog = new File(carpetaLog, "error.log");

        if (ficheroLog.renameTo(destinoLog)) {
            System.out.println("error.log movido correctamente");
        } 
        else {
            System.out.println("Error al mover error.log");
        }

        File destinoSinExtension = new File(carpetaSinExtension, "LEEME");

        if (ficheroLeeme.renameTo(destinoSinExtension)) {
            System.out.println("LEEME movido correctamente");
        } 
        else {
            System.out.println("Error al mover LEEME");
        }
    }

    public static void mostrarConteoFinal() {
        File carpetaTxt = new File("PROYECTO/salida/txt");
        File carpetaCsv = new File("PROYECTO/salida/csv");
        File carpetaJpg = new File("PROYECTO/salida/jpg");
        File carpetaLog = new File("PROYECTO/salida/log");
        File carpetaSinExtension = new File("PROYECTO/salida/sin_extension");

        // Contamos los archivos de cada carpeta
         // Contamos los archivos de cada carpeta

        File[] archivos = carpetaTxt.listFiles();
        System.out.println("Archivos en txt: " + archivos.length);

        File[] archivosCsv = carpetaCsv.listFiles();
        System.out.println("Archivos en csv: " + archivosCsv.length);

        File[] archivosJpg = carpetaJpg.listFiles();
        System.out.println("Archivos en jpg: " + archivosJpg.length);

        File[] archivosLog = carpetaLog.listFiles();
        System.out.println("Archivos en log: " + archivosLog.length);

        File[] archivosSinExtension = carpetaSinExtension.listFiles();
        System.out.println("Archivos sin extension: " + archivosSinExtension.length);
    }
}