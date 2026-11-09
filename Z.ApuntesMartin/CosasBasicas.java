import java.io.File;
import java.util.Scanner;

public class CosasBasicas {
    public static void main(String[] args){
        //Pedir que escriba el nombre y imprimirlo
        Scanner sc = new Scanner(System.in); //solo se hace una vez, para crearlo, se puede reutilizar.
        //sc.nextLine(): Para textos (String).
        //sc.nextInt(): Para números enteros (int).
        //sc.nextDouble(): Para números con decimales (double).
        System.out.println("Escribe tu nombre");
        String nombre = sc.nextLine();
        System.out.println("Escribe tu nota");
        Double nota = sc.nextDouble();
        System.out.println("Nombre: " + nombre + "Nota: " + nota);

        //File
        //Se puede definir la ruta de dos formas principales
        //Opcion A: Ruta relativa o absoluta directa
        File carpeta = new File("salida");
        File archivo1 = new File("salida/diario.txt");

        // Opción B: Pasando el objeto carpeta y el nombre del archivo dentro (muy limpio)
        File carpeta1 = new File("salida");
        File archivo2 = new File(carpeta1, "diario.txt");
        sc.close();
    }
}
