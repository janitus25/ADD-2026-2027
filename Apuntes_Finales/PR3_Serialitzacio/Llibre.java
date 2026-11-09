// PR3 - Model: classe Serializable amb 4 atributs
import java.io.Serializable;

public class Llibre implements Serializable {

    // Versión del formato. Es lo que decide el paso 5 (ver explicación abajo)
    private static final long serialVersionUID = 1L;

    private String titol;
    private String autor;
    private String genere;
    private int pagines;

    // PASO 5: descomenta estas dos líneas, recompila y vuelve a leer el fichero antiguo
    // private double preu;
    // public double getPreu() { return preu; }

    public Llibre() {
    
    }

    public Llibre(String titol, String autor, String genere,int pagines) {
        this.titol = titol;
        this.autor = autor;
        this.genere = genere;
        this.pagines = pagines;
    }

    public String getTitol() { 
        return titol; 
    }
    
    public String getAutor() {
         return autor; 
    }
    
    public String getGenere() { 
        return genere; 
    }
    
    public int getPagines() {
         return pagines; 
    }


    public void setTitol(String titol) { 
        this.titol = titol; 
    }

    public void setAutor(String autor) {
        this.autor = autor; 
    }

    public void setGenere(String genere) {
        this.genere = genere; 
    }

    public void setPagines(int pagines) { 
        this.pagines = pagines; 
    }

    @Override
    public String toString() {
        return titol + " | " + autor + " | " + genere + " | " + pagines + " pagines";
    }
}