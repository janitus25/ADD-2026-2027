/** Carga un XML existente en memoria (o crea uno nuevo), pide un dato por consola, lo añade como nuevo nodo al árbol DOM y reescribe el archivo. */
import java.io.File;
import java.util.Scanner;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class DemanarDadesPerTeclatIModificarUnFitxerXMLambDOM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introdueix el títol del nou llibre a afegir a l'XML: ");
        String nouTitol = sc.nextLine();

        File file = new File("cataleg.xml");

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc;

            if (file.exists()) {
                doc = db.parse(file);
                doc.getDocumentElement().normalize();
            } else {
                doc = db.newDocument();
                Element arrel = doc.createElement("biblioteca");
                doc.appendChild(arrel);
            }

            Element arrel = doc.getDocumentElement();
            Element llibre = doc.createElement("llibre");
            Element titol = doc.createElement("titol");
            titol.appendChild(doc.createTextNode(nouTitol));
            llibre.appendChild(titol);
            arrel.appendChild(llibre);

            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(doc), new StreamResult(file));

            System.out.println("Fitxer XML actualitzat amb el nou llibre introduït.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        sc.close();
    }
}