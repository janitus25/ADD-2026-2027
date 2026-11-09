/** Pide la ruta del documento XML por teclado e inicia el análisis secuencial SAX, disparando los métodos de captura solo en las etiquetas que nos interesan. */
import java.io.File;
import java.util.Scanner;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DemanarRutaXMLPerTeclatIParsejarloSecuencialmentAmbSAX {

    public static class CustomHandler extends DefaultHandler {
        private boolean captura = false;
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("titol") || qName.equals("nom")) {
                captura = true; 
            }
        }
        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            if (captura) {
                System.out.println("Llegit per SAX: " + new String(ch, start, length));
                captura = false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introdueix la ruta del fitxer XML a analitzar amb SAX: ");
        String ruta = sc.nextLine();

        File f = new File(ruta);
        if (!f.exists()) {
            System.out.println("El fitxer no existeix.");
            sc.close();
            return;
        }

        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser parser = spf.newSAXParser();
            CustomHandler handler = new CustomHandler();
            
            parser.parse(f, handler);
            System.out.println("Anàlisi SAX completat.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        sc.close();
    }
}