
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class Ejercicio4_LeerXML {

    public static void main(String[] args) {

        File f = new File("colegio.xml");

        try {

            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();

            DocumentBuilder constructor = fabrica.newDocumentBuilder();

            Document doc = constructor.parse(f);
            doc.getDocumentElement().normalize();

            String nombre = doc.getElementsByTagName("nombre").item(0).getTextContent();
            String director = doc.getElementsByTagName("director").item(0).getTextContent();

            System.out.println("Nombre: " + nombre);
            System.out.println("Director: " + director);

            NodeList lista = doc.getElementsByTagName("alumno");
            String[] alumnos = new String[lista.getLength()];

            double suma = 0;
            double mejorNota = 0;
            String mejorAlumno = "";
            int aprobados = 0;
            int suspendidos = 0;

            for (int i = 0; i < lista.getLength(); i++) {

                Node node = lista.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) node;
                    String alumno = e.getElementsByTagName("nombre").item(0).getTextContent();
                    String curso = e.getAttribute("curso");
                    double nota = Double.parseDouble(e.getElementsByTagName("nota").item(0).getTextContent());

                    alumnos[i] = alumno;

                    System.out.println("\nAlumno: " + alumnos[i]);
                    System.out.println("Curso: " + curso);
                    System.out.println("Nota: " + nota);

                    suma = suma + nota;

                    if (nota > mejorNota) {
                        mejorNota = nota;
                        mejorAlumno = alumno;
                    }

                    if (nota >= 5) {
                        aprobados++;
                    } 
                    else {
                        suspendidos++;
                    }
                }
            }

            double media = suma / lista.getLength();

            System.out.println("\n--- RESULTADOS ---");
            System.out.println("Nota media: " + media);
            System.out.println("Mejor alumno: " + mejorAlumno);
            System.out.println("Mejor nota: " + mejorNota);
            System.out.println("Aprobados: " + aprobados);
            System.out.println("Suspendidos: " + suspendidos);

        } 
        
        catch (Exception e) {
            System.err.println("Error leyendo con DOM: " + e.getMessage());
        }
    }
}
