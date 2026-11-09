
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class tienda {

    public static void main(String[] args) {

        File f = new File("tienda.xml");

        try {

            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();

            DocumentBuilder constructor = fabrica.newDocumentBuilder();

            Document doc = constructor.parse(f);
            doc.getDocumentElement().normalize();

            String nombre = doc.getElementsByTagName("nombre").item(0).getTextContent();
            String encargado = doc.getElementsByTagName("encargado").item(0).getTextContent();

            System.out.println("Nombre: " + nombre);
            System.out.println("Encargado: " + encargado);

            NodeList lista = doc.getElementsByTagName("producto");
            String[] productos = new String[lista.getLength()];

            double suma = 0;
            double MasBarato = 50000;
            String productoMasBarato = "";
            int productosMasCincuenta = 0;

            for (int i = 0; i < lista.getLength(); i++) {

                Node node = lista.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) node;
                    String producto = e.getElementsByTagName("nombre").item(0).getTextContent();
                    String categoria = e.getAttribute("categoria");
                    double precio = Double.parseDouble(e.getElementsByTagName("precio").item(0).getTextContent());

                    productos[i] = producto;

                    System.out.println("\nProducto: " + productos[i]);
                    System.out.println("Categoria: " + categoria);
                    System.out.println("Precio: " + precio);

                    suma = suma + precio;

                    if (precio < MasBarato) {
                        MasBarato = precio;
                        productoMasBarato = producto;
                    }

                    if (precio > 50) {
                        productosMasCincuenta++;
                    } 

                }
            }

            double precioMedio = suma / lista.getLength();

            System.out.println("\n--- RESULTADOS ---");
            System.out.println("Precio Medio: " + precioMedio);
            System.out.println("Producto mas barato: " + productoMasBarato + " cuesta: " + MasBarato);
            System.out.println("Productos mas caros de 50 euros: " + productosMasCincuenta);

        } 
        
        catch (Exception e) {
            System.err.println("Error leyendo con DOM: " + e.getMessage());
        }
    }
}
