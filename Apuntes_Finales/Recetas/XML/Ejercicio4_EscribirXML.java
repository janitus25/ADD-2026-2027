import java.io.File;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Ejercicio4_EscribirXML {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // 1. Inicializar la factoría para construir el documento DOM
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            Document doc = constructor.newDocument();

            // Elemento raíz <colegio>
            Element raiz = doc.createElement("colegio");
            doc.appendChild(raiz);

            // Pedir datos generales del colegio
            System.out.print("Introduce el nombre del colegio: ");
            String nombreColegio = scanner.nextLine();
            Element elNombre = doc.createElement("nombre");
            elNombre.setTextContent(nombreColegio);
            raiz.appendChild(elNombre);

            System.out.print("Introduce el nombre del director: ");
            String directorColegio = scanner.nextLine();
            Element elDirector = doc.createElement("director");
            elDirector.setTextContent(directorColegio);
            raiz.appendChild(elDirector);

            // Pedir cuántos alumnos se desean añadir
            System.out.print("¿Cuántos alumnos quieres registrar?: ");
            int numAlumnos = Integer.parseInt(scanner.nextLine());

            for (int i = 0; i < numAlumnos; i++) {
                System.out.println("\n--- Alumno " + (i + 1) + " ---");
                System.out.print("Nombre del alumno: ");
                String nombreAlumno = scanner.nextLine();

                System.out.print("Curso (ej. 1ºESO, 2ºBACH): ");
                String cursoAlumno = scanner.nextLine();

                System.out.print("Nota (ej. 8.5): ");
                String notaAlumno = scanner.nextLine();

                // Crear elemento <alumno> con su atributo "curso"
                Element elAlumno = doc.createElement("alumno");
                elAlumno.setAttribute("curso", cursoAlumno);

                // Sub-elemento <nombre> dentro de <alumno>
                Element elNombreAlumno = doc.createElement("nombre");
                elNombreAlumno.setTextContent(nombreAlumno);
                elAlumno.appendChild(elNombreAlumno);

                // Sub-elemento <nota> dentro de <alumno>
                Element elNotaAlumno = doc.createElement("nota");
                elNotaAlumno.setTextContent(notaAlumno);
                elAlumno.appendChild(elNotaAlumno);

                // Adjuntar el alumno al elemento raíz <colegio>
                raiz.appendChild(elAlumno);
            }

            // 2. Volcar la estructura DOM a un archivo físico llamado "colegio.xml"
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            
            // Propiedades para dar formato legible (sangría y saltos de línea)
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("colegio.xml"));
            transformer.transform(source, result);

            System.out.println("\n¡Archivo 'colegio.xml' creado y guardado con éxito!");

        } catch (Exception e) {
            System.err.println("Error escribiendo el XML: " + e.getMessage());
        }
    }
}