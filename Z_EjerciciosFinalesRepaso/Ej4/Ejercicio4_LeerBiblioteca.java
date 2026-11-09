import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ejercicio4_LeerBiblioteca {

    public static void main(String[] args) {
        File f = new File("biblioteca.xml");

        try {
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            Document doc = constructor.parse(f);
            doc.getDocumentElement().normalize();

            String nombreBiblio = doc.getElementsByTagName("nombre").item(0).getTextContent();
            String responsable = doc.getElementsByTagName("responsable").item(0).getTextContent();

            System.out.println("Biblioteca: " + nombreBiblio);
            System.out.println("Responsable: " + responsable);
            System.out.println("\n--- LISTADO DE LIBROS ---");

            NodeList lista = doc.getElementsByTagName("libro");
            double sumaPaginas = 0;
            int maxPaginas = -1;
            String libroMax = "";
            int mayor400 = 0;
            int menorIgual400 = 0;

            for (int i = 0; i < lista.getLength(); i++) {
                Node node = lista.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) node;
                    String genero = e.getAttribute("genero");
                    String titulo = e.getElementsByTagName("titulo").item(0).getTextContent();
                    int paginas = Integer.parseInt(e.getElementsByTagName("paginas").item(0).getTextContent());

                    System.out.println("Título: " + titulo + " | Género: " + genero + " | Páginas: " + paginas);

                    sumaPaginas += paginas;

                    if (paginas > maxPaginas) {
                        maxPaginas = paginas;
                        libroMax = titulo;
                    }

                    if (paginas > 400) {
                        mayor400++;
                    } else {
                        menorIgual400++;
                    }
                }
            }

            double media = lista.getLength() > 0 ? (sumaPaginas / lista.getLength()) : 0;

            System.out.println("\n--- RESULTADOS ---");
            System.out.printf("Media de páginas: %.2f\n", media);
            System.out.println("Libro con más páginas: " + libroMax + " (" + maxPaginas + " páginas)");
            System.out.println("Libros con más de 400 páginas: " + mayor400);
            System.out.println("Libros con 400 páginas o menos: " + menorIgual400);

        } catch (Exception e) {
            System.err.println("Error leyendo con DOM: " + e.getMessage());
        }
    }
}