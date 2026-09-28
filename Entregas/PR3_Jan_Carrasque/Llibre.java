import java.io.Serializable;

public class Llibre implements Serializable {

    private static final long serialVersionUID = 1L; //En java escribimos private delante de la variable no private: como en C++
    private String titulo;
    private String autor;
    private String genero;
    private int paginas;
    private int anyPublicacio;


    //Constructor   
    public Llibre(String titulo, String autor, String genero, int paginas) {
        this.titulo = titulo; //Ponemos el this, para decir que el atributo recibe el valor de titulo
        this.autor = autor;
        this.genero = genero;
        this.paginas = paginas;
    }

    public String GetTitulo() {
        return titulo;
    }

    public String GetAutor() {
        return autor;
    }

    public String GetGenero() {
        return genero;
    }

    public int GetPaginas() {
        return paginas;
    }

    public int GetAnyPublicacio() { 
        return anyPublicacio; 
    }

}