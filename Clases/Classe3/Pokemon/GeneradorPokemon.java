import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class GeneradorPokemon {
    public static void main(String[] args) {
        String [] nombres = {"Pikachu", "Charmander", "Snorlax", "Squirtle", "Jigglypuff"};
        int [] vidas = {60,100,120,80,100};

        File fitxer = new File ("FitxerPokemons.dat");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fitxer))) {
            for(int i = 0; i < nombres.length ;i++){
                Pokemon pokemon = new Pokemon(nombres[i], vidas[i]);
                oos.writeObject(pokemon);
            }
        } 
        
        catch (IOException e) {
            System.err.println("Error !!!" + e.getMessage());
        }
    }
}
