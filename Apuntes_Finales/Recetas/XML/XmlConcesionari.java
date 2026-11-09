// XML - Concesionari: DOM (leer y crear) y SAX
// Palabras clave: DOM, SAX, getAttribute, getTextContent, getElementsByTagName, NodeList, setAttribute, Transformer

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class XmlConcesionari {

    public static void main(String[] args) {
        LeerConDom();
        LeerConSax();
        CrearConDom();
    }

    // Atajo: texto del primer hijo con esa etiqueta
    static String Texto(Element padre, String etiqueta) {
        return padre.getElementsByTagName(etiqueta).item(0).getTextContent();
    }

    // Atajo: crea <etiqueta>texto</etiqueta> en un paso
    static Element CrearElemento(Document doc, String etiqueta, String texto) {
        Element el = doc.createElement(etiqueta);
        el.appendChild(doc.createTextNode(texto));
        return el;
    }

    // ============================================================
    // DOM: LEER
    // ============================================================
    static void LeerConDom() {
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("concesionari.xml"));
            doc.getDocumentElement().normalize();

            // Atributos del elemento raíz
            Element raiz = doc.getDocumentElement();
            System.out.println("Concesionario: " + raiz.getAttribute("name") + " (" + raiz.getAttribute("location") + ")");

            NodeList coches = doc.getElementsByTagName("car");
            System.out.println("Numero de coches: " + coches.getLength());

            double totalDisponibles = 0;

            for (int i = 0; i < coches.getLength(); i++) {
                Node node = coches.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element car = (Element) node;

                    // Atributos del coche
                    String id = car.getAttribute("id");
                    String status = car.getAttribute("status");

                    // Elementos que tienen texto Y atributo: hay que sacarlos como Element
                    Element engine = (Element) car.getElementsByTagName("engine").item(0);
                    Element price = (Element) car.getElementsByTagName("price").item(0);

                    System.out.println(id + " [" + status + "] " + Texto(car, "brand") + " " + Texto(car, "model") + " (" + Texto(car, "year") + ")");
                    System.out.println("   Motor: " + engine.getTextContent() + " - " + engine.getAttribute("fuel"));
                    System.out.println("   Precio: " + price.getTextContent()+ " " + price.getAttribute("currency"));

                    // Lista anidada: todos los <feature> de este coche
                    NodeList features = car.getElementsByTagName("feature");
                    
                    for (int j = 0; j < features.getLength(); j++) {
                        System.out.println("   - " + features.item(j).getTextContent());
                    }

                    // Filtrar y sumar: solo los disponibles (equals, nunca ==)
                    if (status.equals("available")) {
                        totalDisponibles += Double.parseDouble(price.getTextContent());
                    }
                }
            }
            System.out.println("Suma de precios (available): " + totalDisponibles);

        } 
        catch (Exception e) {
            System.err.println("Error leyendo con DOM: " + e.getMessage());
        }
    }

    // ============================================================
    // SAX: LEER (solo lectura, por eventos)
    // ============================================================
    static void LeerConSax() {
        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            parser.parse(new File("concesionari.xml"), new CocheHandler());
        } 
        catch (Exception e) {
            System.err.println("Error leyendo con SAX: " + e.getMessage());
        }
    }

    static class CocheHandler extends DefaultHandler {

        // characters() puede llegar en trozos: se acumula en un StringBuilder
        private StringBuilder texto = new StringBuilder();
        private String id = "";
        private String marca = "";
        private String modelo = "";
        private ArrayList<String> features = new ArrayList<>();

        @Override
        public void startElement(String uri, String local, String qName, Attributes attrs) {
            texto.setLength(0);                         // vaciar al abrir cualquier etiqueta
            if (qName.equals("car")) {
                id = attrs.getValue("id");              // leer un atributo
                features.clear();
            }
        }

        @Override
        public void characters(char[] ch, int inicio, int largo) {
            texto.append(ch, inicio, largo);
        }

        @Override
        public void endElement(String uri, String local, String qName) {
            String valor = texto.toString().trim();     // el texto está completo al cerrar

            if (qName.equals("brand")) {
                marca = valor;
            } 
            
            else if (qName.equals("model")) {
                modelo = valor;
            } 
            
            else if (qName.equals("feature")) {
                features.add(valor);
            } 
            
            else if (qName.equals("car")) {           // al cerrar el coche, ya tenemos todo
                System.out.println("SAX -> " + id + ": " + marca + " " + modelo + " " + features);
            }
        }
    }

    // ============================================================
    // DOM: CREAR (con atributos, anidado y lista)
    // ============================================================
    static void CrearConDom() {
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            Element raiz = doc.createElement("dealership");
            raiz.setAttribute("name", "Premium Motors");
            raiz.setAttribute("location", "Barcelona");
            doc.appendChild(raiz);

            Element inventory = doc.createElement("inventory");
            raiz.appendChild(inventory);

            Element car = doc.createElement("car");
            car.setAttribute("id", "C001");
            car.setAttribute("status", "available");
            inventory.appendChild(car);

            car.appendChild(CrearElemento(doc, "brand", "Toyota"));
            car.appendChild(CrearElemento(doc, "model", "Corolla"));

            // Elemento con texto Y atributo
            Element engine = CrearElemento(doc, "engine", "1.8L");
            engine.setAttribute("fuel", "Hybrid");
            car.appendChild(engine);

            // Lista anidada
            Element features = doc.createElement("features");
            features.appendChild(CrearElemento(doc, "feature", "Apple CarPlay"));
            features.appendChild(CrearElemento(doc, "feature", "Lane Assist"));
            car.appendChild(features);

            // Guardar en fichero
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(doc), new StreamResult(new File("sortida.xml")));
            System.out.println("XML creado: sortida.xml");

        } 
        
        catch (Exception e) {
            System.err.println("Error creando el XML: " + e.getMessage());
        }
    }
}