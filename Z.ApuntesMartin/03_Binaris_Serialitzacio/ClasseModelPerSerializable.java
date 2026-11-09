/** Clase modelo de datos que implementa la interfaz Serializable, requisito obligatorio en Java para poder guardar objetos enteros en ficheros binarios. */
import java.io.Serializable;

public class ClasseModelPerSerializable implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nom;
    private int edat;

    public ClasseModelPerSerializable(String nom, int edat) {
        this.nom = nom;
        this.edat = edat;
    }

    public String getNom() { return nom; }
    public int getEdat() { return edat; }
    
    @Override
    public String toString() {
        return "Nom: " + nom + ", Edat: " + edat;
    }
}