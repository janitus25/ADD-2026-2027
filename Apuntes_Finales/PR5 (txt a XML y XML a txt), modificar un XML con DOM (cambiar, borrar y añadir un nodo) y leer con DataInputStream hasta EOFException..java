import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
 * APUNTES EXTRA (lo que no estaba en los otros apuntes)
 * ------------------------------------------------------------
 * 1) PR5: texto (contactes.txt) -> lista de objetos -> XML con DOM, y el camino inverso
 * 2) Comparar dos ficheros de texto línea a línea
 * 3) MODIFICAR un XML con DOM (cambiar un valor, borrar un nodo, añadir un nodo)
 * 4) Leer con DataInputStream varios registros hasta EOFException
 *
 * Ejecutar:  java ApuntesExtra.java
 * (crea contactes.txt, contactes.xml, contactes_nou.txt y registros.dat en la carpeta actual)
 */
public class ApuntesExtra {

    public static void main(String[] args) {
        crearTxtDeEjemplo();

        txtAXml();                       // texto -> objetos -> XML
        xmlATxt();                       // XML -> objetos -> texto
        compararFicheros("contactes.txt", "contactes_nou.txt");

        modificarXml();                  // cambiar, borrar y añadir en el XML

        escribirRegistrosData();
        leerRegistrosData();
    }

    // ============================================================
    // 1) PR5: texto <-> objetos <-> XML
    // ============================================================

    // Texto -> lista de objetos -> XML (DOM)
    static void txtAXml() {
        ArrayList<Contacte> llista = leerTxt("contactes.txt");

        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            Element arrel = doc.createElement("contactes");
            doc.appendChild(arrel);

            for (Contacte c : llista) {
                Element el = doc.createElement("contacte");
                arrel.appendChild(el);

                el.appendChild(crearElemento(doc, "nom", c.getNom()));
                el.appendChild(crearElemento(doc, "telefon", c.getTelefon()));
                el.appendChild(crearElemento(doc, "correu", c.getCorreu()));
            }

            guardarDom(doc, "contactes.xml");
            System.out.println("XML creado: contactes.xml (" + llista.size() + " contactos)");

        } catch (Exception e) {
            System.err.println("Error creando el XML: " + e.getMessage());
        }
    }

    // XML -> lista de objetos -> texto
    static void xmlATxt() {
        ArrayList<Contacte> llista = new ArrayList<>();

        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new File("contactes.xml"));
            doc.getDocumentElement().normalize();

            NodeList nodos = doc.getElementsByTagName("contacte");
            for (int i = 0; i < nodos.getLength(); i++) {
                Node node = nodos.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) node;
                    String nom = e.getElementsByTagName("nom").item(0).getTextContent();
                    String telefon = e.getElementsByTagName("telefon").item(0).getTextContent();
                    String correu = e.getElementsByTagName("correu").item(0).getTextContent();
                    llista.add(new Contacte(nom, telefon, correu));
                }
            }
        } catch (Exception e) {
            System.err.println("Error leyendo el XML: " + e.getMessage());
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("contactes_nou.txt"))) {
            for (Contacte c : llista) {
                bw.write(c.getNom() + ";" + c.getTelefon() + ";" + c.getCorreu());
                bw.newLine();
            }
            System.out.println("Texto regenerado: contactes_nou.txt");
        } catch (IOException e) {
            System.err.println("Error escribiendo el texto: " + e.getMessage());
        }
    }

    // Leer un txt "nom;telefon;correu" y devolver una lista de objetos
    static ArrayList<Contacte> leerTxt(String ruta) {
        ArrayList<Contacte> llista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] c = linea.split(";");
                llista.add(new Contacte(c[0], c[1], c[2]));
            }
        } catch (IOException e) {
            System.err.println("Error de lectura: " + e.getMessage());
        }
        return llista;
    }

    // Crear <etiqueta>texto</etiqueta> en un solo paso (evita repetir 3 líneas cada vez)
    static Element crearElemento(Document doc, String etiqueta, String texto) {
        Element el = doc.createElement(etiqueta);
        el.appendChild(doc.createTextNode(texto));
        return el;
    }

    // Guardar un Document DOM en un fichero
    static void guardarDom(Document doc, String ruta) throws Exception {
        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.transform(new DOMSource(doc), new StreamResult(new File(ruta)));
    }

    // ============================================================
    // 2) Comparar dos ficheros de texto (PR5: "son idénticos?")
    // Se compara línea a línea con equals() (nunca ==)
    // ============================================================
    static void compararFicheros(String ruta1, String ruta2) {
        try (BufferedReader br1 = new BufferedReader(new FileReader(ruta1));
             BufferedReader br2 = new BufferedReader(new FileReader(ruta2))) {

            String l1;
            String l2;
            boolean iguales = true;

            do {
                l1 = br1.readLine();
                l2 = br2.readLine();

                // Si solo uno ha terminado, o las líneas difieren, no son iguales
                if (l1 == null || l2 == null) {
                    if (l1 != l2) {              // aquí == es correcto: se comparan null con null
                        iguales = false;
                    }
                } else if (!l1.equals(l2)) {
                    iguales = false;
                }
            } while (iguales && l1 != null && l2 != null);

            System.out.println(iguales ? "Los ficheros son idénticos" : "Los ficheros son diferentes");

        } catch (IOException e) {
            System.err.println("Error comparando: " + e.getMessage());
        }
    }

    // ============================================================
    // 3) MODIFICAR un XML con DOM (esto SAX no puede hacerlo)
    // Pasos: parsear -> cambiar el árbol -> guardar con Transformer
    // ============================================================
    static void modificarXml() {
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new File("contactes.xml"));
            doc.getDocumentElement().normalize();

            NodeList nodos = doc.getElementsByTagName("contacte");

            // a) CAMBIAR un valor: telefono del primer contacto
            Element primero = (Element) nodos.item(0);
            primero.getElementsByTagName("telefon").item(0).setTextContent("999999999");

            // b) BORRAR un nodo: el último contacto (se pide al padre que borre al hijo)
            Node ultimo = nodos.item(nodos.getLength() - 1);
            ultimo.getParentNode().removeChild(ultimo);

            // c) AÑADIR un nodo nuevo al elemento raíz
            Element nuevo = doc.createElement("contacte");
            nuevo.appendChild(crearElemento(doc, "nom", "Nuevo"));
            nuevo.appendChild(crearElemento(doc, "telefon", "600000000"));
            nuevo.appendChild(crearElemento(doc, "correu", "nuevo@correo.com"));
            doc.getDocumentElement().appendChild(nuevo);

            guardarDom(doc, "contactes_modificat.xml");
            System.out.println("XML modificado: contactes_modificat.xml");

        } catch (Exception e) {
            System.err.println("Error modificando el XML: " + e.getMessage());
        }
    }

    // ============================================================
    // 4) Varios registros con Data*Stream y lectura hasta EOFException
    // Con DataInputStream NO hay valor especial de fin: salta EOFException
    // (igual que con ObjectInputStream). Va ANTES que IOException.
    // ============================================================
    static void escribirRegistrosData() {
        String[] nombres = {"Anna", "Pere", "Marta"};
        int[] edades = {25, 31, 28};
        double[] sueldos = {1850.5, 1620.0, 1910.75};

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("registros.dat"))) {
            for (int i = 0; i < nombres.length; i++) {
                dos.writeUTF(nombres[i]);
                dos.writeInt(edades[i]);
                dos.writeDouble(sueldos[i]);
            }
        } catch (IOException e) {
            System.err.println("Error escribiendo: " + e.getMessage());
        }
    }

    static void leerRegistrosData() {
        try (DataInputStream dis = new DataInputStream(new FileInputStream("registros.dat"))) {
            while (true) {
                String nombre = dis.readUTF();         // MISMO orden que al escribir
                int edad = dis.readInt();
                double sueldo = dis.readDouble();
                System.out.println(nombre + " | " + edad + " | " + sueldo);
            }
        } catch (EOFException e) {
            System.out.println("FIN DE LECTURA");      // salida normal
        } catch (IOException e) {
            System.err.println("Error leyendo: " + e.getMessage());
        }
    }

    // ============================================================
    // Datos de ejemplo para que las recetas de arriba funcionen
    // ============================================================
    static void crearTxtDeEjemplo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("contactes.txt"))) {
            bw.write("Anna;600111222;anna@correo.com");
            bw.newLine();
            bw.write("Pere;600333444;pere@correo.com");
            bw.newLine();
            bw.write("Marta;600555666;marta@correo.com");
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error creando el ejemplo: " + e.getMessage());
        }
    }
}

// ============================================================
// Clase del modelo (en un proyecto real iría en su propio Contacte.java)
// ============================================================
class Contacte {

    private String nom;
    private String telefon;
    private String correu;

    public Contacte() {
    }

    public Contacte(
            String nom,
            String telefon,
            String correu) {
        this.nom = nom;
        this.telefon = telefon;
        this.correu = correu;
    }

    public String getNom() { return nom; }
    public String getTelefon() { return telefon; }
    public String getCorreu() { return correu; }

    public void setNom(String nom) { this.nom = nom; }
    public void setTelefon(String telefon) { this.telefon = telefon; }
    public void setCorreu(String correu) { this.correu = correu; }
}
