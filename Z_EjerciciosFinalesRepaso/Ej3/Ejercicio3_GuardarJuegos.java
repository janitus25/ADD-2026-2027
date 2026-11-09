import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Ejercicio3_GuardarJuegos {

    public static void main(String[] args) {
        ArrayList<EJ3_GuardarYLeerVideojuegos> lista = new ArrayList<>();
        lista.add(new EJ3_GuardarYLeerVideojuegos("Zelda: Breath of the Wild", 100, "Switch", "Nintendo"));
        lista.add(new EJ3_GuardarYLeerVideojuegos("Super Mario Odyssey", 25, "Switch", "Nintendo"));
        lista.add(new EJ3_GuardarYLeerVideojuegos("God of War", 30, "PS4", "Sony"));
        lista.add(new EJ3_GuardarYLeerVideojuegos("Halo Infinite", 20, "Xbox", "Microsoft"));
        lista.add(new EJ3_GuardarYLeerVideojuegos("The Witcher 3", 150, "PC", "CD Projekt"));

        File fichero = new File("salida/juegos.dat");
        if (fichero.getParentFile() != null && !fichero.getParentFile().exists()) {
            fichero.getParentFile().mkdirs();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero))) {
            oos.writeObject(lista);
            System.out.println("Videojuegos guardados correctamente en " + fichero.getPath());
        } catch (IOException e) {
            System.err.println("Error al guardar los videojuegos: " + e.getMessage());
        }
    }
}