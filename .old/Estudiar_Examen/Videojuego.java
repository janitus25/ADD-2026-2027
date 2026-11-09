import java.io.Serializable;

public class Videojuego implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private String titulo;
    private String desarrolladora;
    private int anyo;
    private float horas;
    private float  valoracion;

    public Videojuego() {
        titulo = "Default";
        desarrolladora = "Default";
        anyo = 2020;
        horas = 1;
        valoracion = 7;
    }

    public Videojuego(String titulo, String desarrolladora, int anyo, float horas, float valoracion) {
        this.titulo = titulo;
        this.desarrolladora = desarrolladora;
        this.anyo = anyo;
        this.horas = horas;
        this.valoracion = valoracion;
    }

    public String GetTitulo() {
        return titulo;
    }

    public String GetDesarrolladora() {
        return desarrolladora;
    }

    public int GetAnyo() {
        return anyo;
    }

     public float GetHoras() {
        return horas;
    }

    public float GetValoracion() {
        return valoracion;
    }

}
