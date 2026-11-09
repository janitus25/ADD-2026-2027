import java.io.Serializable;

public class Pelicula implements Serializable {

    private static final long serialVersionUID = 1L;

    private String titulo;
    private String director;
    private int anyo;
    private float duracion;
    private float valoracion;

    public Pelicula() {
        titulo = "Default";
        director = "Default";
        anyo = 2020;
        duracion = 2;
        valoracion = 0;
    }

    public Pelicula(String titulo, String director, int anyo, float duracion, float valoracion) {
        this.titulo = titulo;
        this.director = director;
        this.anyo = anyo;
        this.duracion = duracion;
        this.valoracion = valoracion;
    }

    public String GetTitulo() {
        return titulo;
    }

    public String GetDirector() {
        return director;
    }

    public int GetAnyo() {
        return anyo;
    }

    public float GetDuracion() {
        return duracion;
    }

    public float GetValoracion() {
        return valoracion;
    }
}