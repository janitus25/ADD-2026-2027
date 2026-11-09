/** Clase manejadora de eventos que hereda de DefaultHandler. Define las acciones a ejecutar cuando el parser SAX detecta aperturas, textos o cierres de etiquetas. */
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ClasseManejadoraDeEsdevenimentsPerSAXDefaultHandler extends DefaultHandler {
    private boolean llegirTitol = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("titol")) { 
            llegirTitol = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (llegirTitol) {
            System.out.println("Text trobat: " + new String(ch, start, length));
            llegirTitol = false;
        }
    }
}