import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ejercicio4_LeerSupermercado {

    public static void main(String[] args) {
        File f = new File("supermercado.xml");

        try {
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            Document doc = constructor.parse(f);
            doc.getDocumentElement().normalize();

            String nombre = doc.getElementsByTagName("nombre").item(0).getTextContent();
            String gerente = doc.getElementsByTagName("gerente").item(0).getTextContent();

            System.out.println("Nombre del supermercado: " + nombre);
            System.out.println("Gerente del local: " + gerente);
            System.out.println("\n--- LISTADO DE productos ---");

            NodeList lista = doc.getElementsByTagName("producto");
            
            float precioMedio=0;
            float productoMasCaro=0;
            int ProductoMayorDeDosEuros=0;
            int ProductoMenorDeDosEuros=0;
            int cantidad=0;
            String productoMasCaroo="";

            for (int i = 0; i < lista.getLength(); i++) {
                Node node = lista.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) node;
                    String categoria = e.getAttribute("categoria");
                    String nombreProducto = e.getElementsByTagName("nombre").item(0).getTextContent();
                    float precio = Float.parseFloat(e.getElementsByTagName("precio").item(0).getTextContent());

                    System.out.println("Nombre del producto: " + nombreProducto + " | Categoria: " + categoria + " | Precio: " + precio);

                    precioMedio += precio;
                    cantidad++;

                    if (productoMasCaro < precio) {
                        productoMasCaro = precio;
                        productoMasCaroo = nombreProducto;
                    }

                    if (precio > 2) {
                        ProductoMayorDeDosEuros++;
                    } else {
                        ProductoMenorDeDosEuros++;
                    }
                }
            }

            precioMedio = precioMedio/cantidad;

            System.out.println("\n--- RESULTADOS ---");
            System.out.printf("Media de precios: %.2f\n", precioMedio);
            System.out.println("Producto mas caro: " + productoMasCaroo + " Cuesta (" + productoMasCaro + " euros)");
            System.out.println("Productos que cuestan mas de 2 euros: " + ProductoMayorDeDosEuros);
            System.out.println("Productos que cuestan menos de 2 euros: " + ProductoMenorDeDosEuros);

        } 
        catch (Exception e) {
            System.err.println("Error leyendo con DOM: " + e.getMessage());
        }
    }
}