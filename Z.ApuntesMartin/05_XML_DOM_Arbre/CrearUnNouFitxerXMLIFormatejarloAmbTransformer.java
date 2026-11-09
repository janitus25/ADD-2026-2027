/** Construye un documento XML desde cero en memoria añadiendo nodos, y utiliza Transformer para volcarlo a un archivo físico con formato e indentación. */
import java.io.File;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class CrearUnNouFitxerXMLIFormatejarloAmbTransformer {
    public static void main(String[] args) {
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            Element arrel = doc.createElement("biblioteca");
            doc.appendChild(arrel);

            Element llibre = doc.createElement("llibre");
            llibre.setAttribute("id", "1");
            arrel.appendChild(llibre);

            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(doc), new StreamResult(new File("sortida.xml")));
            System.out.println("XML creat correctament.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}