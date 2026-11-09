import java.io.File;
import java.io.IOException;

public class Ejercicio1_Backup {

    public static void main(String[] args) {
        crearArchivosYCarpetas();
        moverArchivos();
    }

    public static void crearArchivosYCarpetas() {
        // Creamos la carpeta y los archivos
        File carpeta = new File("PRACTICA/documentos");
        
        File ficheroTxt = new File(carpeta, "tema1.txt");
        File ficheroCsv = new File(carpeta, "horario.csv");
        File ficheroPdf = new File(carpeta, "apuntes.pdf");
        File ficheroTxt2 = new File(carpeta, "tema2.txt");
        File ficheroPng = new File(carpeta, "foto.png");
        File ficheroReadme= new File(carpeta, "README");
        
        try {
            carpeta.mkdirs();
            
            ficheroTxt.createNewFile();
            ficheroCsv.createNewFile();
            ficheroPdf.createNewFile();
            ficheroTxt2.createNewFile();
            ficheroPng.createNewFile();
            ficheroReadme.createNewFile();
        } 
        
        catch (IOException e) {
            System.out.println("Error al crear el fichero");
        }

    }

    public static void moverArchivos() {

        int copiados=0, ignorados=0;

        File carpeta = new File("PRACTICA/documentos");
        
        File ficheroTxt = new File(carpeta, "tema1.txt");
        File ficheroCsv = new File(carpeta, "horario.csv");
        File ficheroTxt2 = new File(carpeta, "tema2.txt");

        File carpetaDestino = new File("PRACTICA/backup");

        File destinoTxt = new File(carpetaDestino, "copia_tema1.txt");
        File destinoTxt2 = new File(carpetaDestino, "copia_tema2.txt");
        File destinoCsv = new File(carpetaDestino, "copia_horario.csv");

        if(!carpetaDestino.exists()){
            carpetaDestino.mkdirs();
        }
        
        if (ficheroTxt.renameTo(destinoTxt)) {

            if(destinoTxt.exists()){
                System.out.println("copia_tema1.txt movido correctamente");
                copiados++;
            }  

            else{
                System.out.println("El fichero ya existe en backup");
                ignorados++;
            }    
        } 

        else {
            System.out.println("Error al mover copia_tema1.txt");
        }

        if (ficheroTxt2.renameTo(destinoTxt2)) {

            if(destinoTxt2.exists()){
                System.out.println("copia_tema2.txt movido correctamente");
                copiados++;
            }  

            else{
                System.out.println("El fichero ya existe en backup");
                ignorados++;
            }    
        } 

        else {
            System.out.println("Error al mover copia_tema2.txt");
        }

        if (ficheroCsv.renameTo(destinoCsv)) {
            
            if(destinoCsv.exists()){
                System.out.println("copia_horario.csv movido correctamente");
                copiados++;
            }  

            else{
                System.out.println("El fichero ya existe en backup");
                ignorados++;
            } 
        } 
        
        else {
            System.out.println("Error al mover copia_horario.csv");
        }

        System.out.println("Copiados: " + copiados + " | " + "Ignorados: " + ignorados);

    }

}