/** Parsea un archivo XML entero cargándolo en memoria RAM como un árbol de nodos (DOM) para navegar por sus etiquetas y extraer información. */
import java.io.File;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class LlegirUnFitxerXMLcarregantLArbreCompletEnMemoriaAmbDOM {
    public static void main(String[] args) {
        try {
            File file = new File("cataleg.xml");
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            NodeList llista = doc.getElementsByTagName("llibre");
            for (int i = 0; i < llista.getLength(); i++) {
                Element e = (Element) llista.item(i);
                System.out.println("Atribut ID: " + e.getAttribute("id"));
                System.out.println("Títol: " + e.getElementsByTagName("titol").item(0).getTextContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}