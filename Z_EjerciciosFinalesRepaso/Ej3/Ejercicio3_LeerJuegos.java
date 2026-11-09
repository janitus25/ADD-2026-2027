import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio3_LeerJuegos {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        File fichero = new File("salida/juegos.dat");
        ArrayList<EJ3_GuardarYLeerVideojuegos> lista = null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {
            lista = (ArrayList<EJ3_GuardarYLeerVideojuegos>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al leer el fichero binario: " + e.getMessage());
            return;
        }

        System.out.println("--- LISTA DE TODOS LOS VIDEOJUEGOS ---");
        for (EJ3_GuardarYLeerVideojuegos v : lista) {
            System.out.println(v);
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("\nIntroduce el nombre de una desarrolladora: ");
        String devBuscada = sc.nextLine();
        sc.close();

        boolean encontrada = false;
        System.out.println("\n--- JUEGOS DE LA DESARROLLADORA: " + devBuscada + " ---");
        for (EJ3_GuardarYLeerVideojuegos v : lista) {
            if (v.getDesarrolladora().equalsIgnoreCase(devBuscada)) {
                System.out.println(v);
                encontrada = true;
            }
        }
        if (!encontrada) {
            System.out.println("No hay juegos de esa desarrolladora");
        }

        EJ3_GuardarYLeerVideojuegos masLargo = lista.get(0);
        for (EJ3_GuardarYLeerVideojuegos v : lista) {
            if (v.getHorasDuracion() > masLargo.getHorasDuracion()) {
                masLargo = v;
            }
        }
        System.out.println("\n--- VIDEOJUEGO CON MÁS DURACIÓN ---");
        System.out.println(masLargo);
    }
}