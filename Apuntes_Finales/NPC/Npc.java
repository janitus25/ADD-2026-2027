import java.io.Serializable;
//Se puede usar el io.* que pilla todas las librerias de io

public class Npc implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private int vida;
    private float coordenadas[] = new float[3];

    public Npc() {
        nombre = "Default";
        vida = 100;
        coordenadas[0] = 5;
        coordenadas[1] = 2;
        coordenadas[2] = 7;
    }

    public Npc(String nombre,int vida,float coordenadas[]) {      // Se pone sin numero porque lo toma con lo que venga
        this.nombre = nombre;
        this.vida = vida;
        this.coordenadas = coordenadas;
    }

    public String GetNombre() {
        return nombre;
    }

    public int GetVida() {
        return vida;
    }

    public float[] GetCoordenadas() {
        return coordenadas;
    }
}