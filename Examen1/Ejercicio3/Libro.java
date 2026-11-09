import java.io.Serializable;

public class Libro implements Serializable {
    private static final long serialVersionUID = 1L;

    private String titulo;
    private int paginas;
    private String isbn;
    private String editorial;

    public Libro() {

    }

    public Libro(String titulo, int paginas, String isbn, String editorial) {
        this.titulo = titulo;
        this.paginas = paginas;
        this.isbn = isbn;
        this.editorial = editorial;
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { 
        this.titulo = titulo; 
    }

    public int getPaginas() { 
        return paginas; 
    }
    public void setPaginas(int paginas) { 
        this.paginas = paginas; 

    }

    public String getIsbn() {
        return isbn; 
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEditorial() {
        return editorial; 
    }
    
    public void setEditorial(String editorial) { 
        this.editorial = editorial; 
    }

    @Override
    public String toString() {
        return "Libro [Título=" + titulo + ", Páginas=" + paginas + ", ISBN=" + isbn + ", Editorial=" + editorial + "]";
    }
}