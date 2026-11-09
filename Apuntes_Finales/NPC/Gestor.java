import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Gestor {

    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Gestor g = new Gestor();
        g.GuardarNPC();
        g.LeerNPC();
    }

    void GuardarNPC() {
        System.out.println("Dime el nombre, la vida y su posicion");

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        int vida;
        float pos[] = new float[3];

        try {
            System.out.print("Vida: ");
            vida = Integer.parseInt(sc.nextLine());
            
            System.out.print("Pos X: ");
            pos[0] = Float.parseFloat(sc.nextLine());
            
            System.out.print("Pos Y: ");
            pos[1] = Float.parseFloat(sc.nextLine());
            
            System.out.print("Pos Z: ");
            pos[2] = Float.parseFloat(sc.nextLine());
        } 
        
        catch (NumberFormatException e) {
            System.out.println("Debes introducir un numero");
            return;
        }

        Npc npc = new Npc(nombre, vida, pos);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("archivo.dat")))) {
            oos.writeObject(npc); // writeObject, NO write
            System.out.println("NPC guardado");
        } 
        
        catch (IOException e) {
            System.err.println("ERROR al guardar: " + e.getMessage());
        }
    }

    void LeerNPC() {
        File fichero = new File("archivo.dat");

        if (!fichero.exists()) {
            System.out.println("Todavia no hay ningun NPC guardado.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {

            while (true) {
                Npc npc = (Npc) ois.readObject();       // cast obligatorio
                System.out.println("NPC = " + npc.GetNombre() + " | Vida: " + npc.GetVida() + " | Pos: " + Arrays.toString(npc.GetCoordenadas()));
            }

        } 
        
        catch (EOFException e) {
            System.out.println("Archivo finalizado!!");     // salida normal
        } 
        
        catch (IOException | ClassNotFoundException e) {
            System.err.println("ERROR al leer: " + e.getMessage());
        }
    }
}