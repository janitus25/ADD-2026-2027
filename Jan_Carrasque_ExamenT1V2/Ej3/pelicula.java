import java.io.Serializable;

public class pelicula implements Serializable {

    private static final long serialVersionUID = 1L;

    private String titulo;
    private int duracion;
    private int anio;
    private String genero;
    
    
    public pelicula() {
        titulo = "Default";
        duracion = 0;
        anio = 0;
        genero = "Default";
    }

    public pelicula(String titulo, int duracion, int anio, String genero) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.anio = anio;
        this.genero = genero;
    }

    public String GetTitulo() {
        return titulo;
    }

    public int GetDuracion() {
        return duracion;
    }

    public int GetAnio() {
        return anio;
    }

    public String GetGenero() {
        return genero;
    }


    public void SetTitulo(String titulo) { 
        this.titulo = titulo; 
    }

    public void SetDuracion(int duracion) { 
        this.duracion = duracion; 
    }

    public void SetAnio(int anio) { 
        this.anio = anio; 
    }

    public void SetGenero(String genero) { 
        this.genero = genero; 
    }

    @Override
    public String toString() {
        return "Pelicula [Título=" + titulo + ", Duracion=" + duracion + ", Anio=" + anio + ", Genero=" + genero + "]";
    }
}