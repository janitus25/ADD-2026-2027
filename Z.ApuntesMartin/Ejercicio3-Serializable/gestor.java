import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.EOFException;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class gestor {
    Scanner sc = new Scanner(System.in);
    void GuardarNPC(){
        System.out.println("Dime el nombre, la vida y su posicion");
        int vida;
        String nombre;
        float pos[] = new float[3];
        vida = Integer.parseInt(sc.nextLine());
        nombre = sc.nextLine();
        pos[0] = Integer.parseInt(sc.nextLine());
        pos[1] = Integer.parseInt(sc.nextLine());
        pos[2] = Integer.parseInt(sc.nextLine());

        npc pepe = new npc(nombre,vida,pos);

        try (ObjectOutPutStream oos = new ObjectOutputStream(new FileOutputStream(new File("archivo.dat")))){
            oos.write(pepe);            
        } catch (IOException e) {
            System.err.println("ERROR");
        }
    }
    void LeerNPC(){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("archivo.dat")))){
            NPC npc = ois.readObject();
            System.out.println("NPC = " + npc.getNombre() + "Vida: " + npc.getVida());
        } catch (EOFException e) {
            System.out.println("Archivo finalizado!");
        }
    }
}
