import java.io.Serializable;

public class Libro implements Serializable {

    // Versión del formato. Es lo que decide el paso 5 (ver explicación abajo)
    private static final long serialVersionUID = 1L;

    private String titulo;
    private int pagines;
    private String isbn;
    private String editorial;
   

    // PASO 5: descomenta estas dos líneas, recompila y vuelve a leer el fichero antiguo
    // private double preu;
    // public double getPreu() { return preu; }

    public Libro() {
    
    }

    public Libro(String titol, int pagines, String isbn, String editorial) {
        this.titulo = titol;
        this.pagines = pagines;
        this.isbn = isbn;
        this.editorial = editorial;  
    }

    public String getTitol() { 
        return titulo; 
    }

    public int getPagines() {
         return pagines; 
    }
    
    public String getIsbn() {
         return isbn; 
    }
    
    public String getEditorial() { 
        return editorial; 
    }
    

    public void setTitol(String titol) { 
        this.titulo = titol; 
    }

    public void setPagines(int pagines) { 
        this.pagines = pagines; 
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn; 
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial; 
    }

    

    @Override
    public String toString() {
        return titulo + " | " + isbn + " | " + editorial + " | " + pagines + " pagines";
    }
}