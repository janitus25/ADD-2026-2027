import java.io.Serializable;

public class EJ3_GuardarYLeerVideojuegos implements Serializable {
    private static final long serialVersionUID = 1L;

    private String titulo;
    private int horasDuracion;
    private String plataforma;
    private String desarrolladora;

    public EJ3_GuardarYLeerVideojuegos() {}

    public EJ3_GuardarYLeerVideojuegos(String titulo, int horasDuracion, String plataforma, String desarrolladora) {
        this.titulo = titulo;
        this.horasDuracion = horasDuracion;
        this.plataforma = plataforma;
        this.desarrolladora = desarrolladora;
    }

    public String getTitulo() { return titulo; }
    public int getHorasDuracion() { return horasDuracion; }
    public String getPlataforma() { return plataforma; }
    public String getDesarrolladora() { return desarrolladora; }

    @Override
    public String toString() {
        return "Videojuego [Título=" + titulo + ", Duración=" + horasDuracion + "h, Plataforma=" + plataforma + ", Desarrolladora=" + desarrolladora + "]";
    }
}