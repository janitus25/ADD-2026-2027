import java.io.*;

public class npc implements Serializable{

    private String nombre;
    private int vida;
    private float coordenadas[] = new float [3];

    public npc() { //contructor por omision
        nombre = "default";
        vida = 100;
        coordenadas[0] = 0;
        coordenadas[1] = 0;
        coordenadas[2] = 0;
    }

    public npc(String nombre, int vida, float coordenadas[]){
        this.nombre = nombre; // con this referenciamos a la variable de la propia clase
        this.vida = vida;
        this.coordenadas = coordenadas;
    }
    
    public String getNombre(){return nombre;}
    public int getVida(){return vida;}
}
