import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LectorPokemon {
    public static void main(String[] args) {
        File fitxer = new File("FitxerPokemons.dat");

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fitxer))) {
            
            while(true){
                Pokemon p = (Pokemon) ois.readObject();
                System.out.println("Nombre: " + p.getNombre() + "\nVida: " + p.Vida());
            }

        } 
        catch(EOFException e){
            System.out.println("FI DE LECTURA");
        }
        
        catch (IOException | ClassNotFoundException e) {
            System.err.println("Error" + e.getMessage());
        }
    }
}
