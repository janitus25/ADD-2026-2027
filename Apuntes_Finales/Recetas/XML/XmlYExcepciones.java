import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/*
 * APUNTES 5: XML (DOM, SAX, JAXB) Y EXCEPCIONES
 * ------------------------------------------------------------
 * XML = texto jerárquico con etiquetas. Bien formado: UN solo elemento raíz y todas las etiquetas cerradas.
 *   Elemento: <marca>Renault</marca>      Atributo: <cotxe id="1">
 *
 * DOM: carga TODO en un árbol de nodos en memoria. Fácil. Permite MODIFICAR. Ficheros pequeños/medianos.
 * SAX: recorrido secuencial por EVENTOS (startElement, characters, endElement). Poca memoria.
 *      Solo lectura. Hay que escribir un handler que hereda de DefaultHandler. Ficheros enormes.
 * JAXB: convierte clases Java <-> XML automáticamente (ver comentario al final).
 *
 * Ejecutar:  java XmlYExcepciones.java
 */
public class XmlYExcepciones {

    public static void main(String[] args) {
        crearXmlConDom();
        leerXmlConDom();
        leerXmlConSax();
        ejemploExcepciones();
    }

    // ============================================================
    // DOM: crear un documento y guardarlo
    // ============================================================
    static void crearXmlConDom() {
        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = db.newDocument();

            Element raiz = doc.createElement("concessionari");     // elemento raíz
            doc.appendChild(raiz);

            String[] marcas = {"Renault", "Seat"};
            String[] modelos = {"Megane", "Leon"};

            for (int i = 0; i < marcas.length; i++) {
                Element cotxe = doc.createElement("cotxe");
                cotxe.setAttribute("id", String.valueOf(i + 1));   // atributo
                raiz.appendChild(cotxe);

                Element marca = doc.createElement("marca");
                marca.appendChild(doc.createTextNode(marcas[i]));  // contenido de texto
                cotxe.appendChild(marca);

                Element modelo = doc.createElement("model");
                modelo.appendChild(doc.createTextNode(modelos[i]));
                cotxe.appendChild(modelo);
            }

            // Guardar en fichero con Transformer
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");         // salida legible
            t.transform(new DOMSource(doc), new StreamResult(new File("cotxes.xml")));

            System.out.println("XML creado: cotxes.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ============================================================
    // DOM: leer y recorrer los nodos
    // ============================================================
    static void leerXmlConDom() {
        File file = new File("cotxes.xml");
        try {
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            Document doc = constructor.parse(file);
            doc.getDocumentElement().normalize();

            NodeList lista = doc.getElementsByTagName("cotxe");
            System.out.println("Número de coches: " + lista.getLength());

            for (int i = 0; i < lista.getLength(); i++) {
                Node node = lista.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) node;
                    System.out.println("Coche id: " + e.getAttribute("id"));
                    System.out.println("  Marca:  " + e.getElementsByTagName("marca").item(0).getTextContent());
                    System.out.println("  Modelo: " + e.getElementsByTagName("model").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ============================================================
    // SAX: leer con un handler
    // ============================================================
    static void leerXmlConSax() {
        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            parser.parse(new File("cotxes.xml"), new CotxeHandler());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // El handler hereda de DefaultHandler y decide qué hacer en cada aviso
    static class CotxeHandler extends DefaultHandler {
        private boolean leyendoMarca = false;
        private String idActual = "";

        @Override
        public void startElement(String uri, String local, String qName, Attributes attrs) {
            if (qName.equals("cotxe")) {
                idActual = attrs.getValue("id");           // leer un atributo
            }
            if (qName.equals("marca")) {
                leyendoMarca = true;                       // la siguiente llamada a characters() es la marca
            }
        }

        @Override
        public void characters(char[] ch, int inicio, int largo) {
            if (leyendoMarca) {
                System.out.println("SAX -> coche " + idActual + ": " + new String(ch, inicio, largo));
                leyendoMarca = false;
            }
        }

        @Override
        public void endElement(String uri, String local, String qName) {
            // aquí se haría, por ejemplo, if (qName.equals("cotxe")) lista.add(actual);
        }
    }

    // ============================================================
    // EXCEPCIONES
    // ------------------------------------------------------------
    // try { ... } catch (Específica e) { ... } catch (General e) { ... } finally { ... }
    // - El catch MÁS ESPECÍFICO va primero (FileNotFoundException antes que IOException).
    // - finally se ejecuta SIEMPRE. Si solo cierras flujos, usa try-with-resources.
    // - NUNCA un catch vacío: como mínimo imprime e.getMessage().
    // - throws: si el método no trata la excepción, la declara y la trata quien lo llama.
    //   Criterio: trátala donde puedas resolverla; si no, declárala y pásala.
    // ============================================================
    static void ejemploExcepciones() {
        try {
            System.out.println(primeraLinea("no_existe.txt"));
        } catch (FileNotFoundException e) {                  // el más específico primero
            System.err.println("No encuentro el fichero: " + e.getMessage());
        } catch (IOException e) {                            // el más general después
            System.err.println("Error de entrada/salida: " + e.getMessage());
        } finally {
            System.out.println("Esto se ejecuta siempre");
        }

        try {
            int n = Integer.parseInt("abc");                 // no es un número
        } catch (NumberFormatException e) {
            System.err.println("No es un número: " + e.getMessage());
        }
    }

    // Este método NO trata la excepción: la declara con throws
    static String primeraLinea(String ruta) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            return br.readLine();
        }
    }

    /*
     * EXCEPCIONES TÍPICAS CON FICHEROS
     * FileNotFoundException    no existe el fichero / nombre no válido      -> comprobar exists() o crearlo
     * IOException              error genérico de lectura/escritura           -> avisar con el mensaje
     * EOFException             fin del fichero leyendo objetos               -> salida NORMAL del bucle
     * ClassNotFoundException   readObject() no encuentra la clase            -> revisar classpath
     * NotSerializableException el objeto no implementa Serializable          -> implements Serializable
     * SecurityException        sin permisos sobre la ruta                    -> canRead() / canWrite()
     * NumberFormatException    convertir a número un texto que no lo es      -> validar antes
     */

    /*
     * ============================================================
     * JAXB (objetos <-> XML). Desde Java 11 NO viene en el JDK:
     * hay que añadir la dependencia jakarta.xml.bind al pom.xml.
     * Por eso va comentado (no compila sin la librería).
     * ============================================================
     *
     * import jakarta.xml.bind.*;
     * import jakarta.xml.bind.annotation.XmlRootElement;
     *
     * @XmlRootElement                         // clase -> elemento raíz
     * public class Provincia {
     *     private String nom;
     *     private Localitat[] localitats;
     *     // getters y setters
     * }
     *
     * JAXBContext context = JAXBContext.newInstance(Provincia.class);
     *
     * // OBJETO -> XML (marshalling)
     * Marshaller m = context.createMarshaller();
     * m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
     * m.marshal(provincia, new File("provincia.xml"));
     *
     * // XML -> OBJETO (unmarshalling)
     * Unmarshaller um = context.createUnmarshaller();
     * Provincia p = (Provincia) um.unmarshal(new File("provincia.xml"));
     *
     * CONVERSIÓN ENTRE FORMATOS: formato origen -> objetos Java del modelo -> formato destino.
     *   1) A mano (CSV -> objetos -> XML)   2) XSLT (XML -> XML/HTML)   3) Librerías (Jackson, Apache POI)
     */
}
