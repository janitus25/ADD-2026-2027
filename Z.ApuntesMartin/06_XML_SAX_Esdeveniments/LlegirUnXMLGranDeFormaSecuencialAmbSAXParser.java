/** Utiliza el parser SAX para analizar un archivo XML de forma puramente secuencial (mediante eventos), ideal para ficheros enormes que no caben en memoria. */
import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class LlegirUnXMLGranDeFormaSecuencialAmbSAXParser {
    public static void main(String[] args) {
        try {
            File f = new File("cataleg.xml");
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser saxParser = spf.newSAXParser();

            ClasseManejadoraDeEsdevenimentsPerSAXDefaultHandler handler = new ClasseManejadoraDeEsdevenimentsPerSAXDefaultHandler();
            saxParser.parse(f, handler); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}