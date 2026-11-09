/** Muestra el ciclo completo de JAXB: Marshalling (transformar un objeto Java directamente en archivo XML) y Unmarshalling (recuperar el XML y transformarlo en objeto Java). */
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;

public class ConvertirObjectesAXMLIMappingInversAmbJAXB {
    public static void main(String[] args) {
        try {
            JAXBContext context = JAXBContext.newInstance(ClasseModelAmbAnotacionsJAXBXmlRootElement.class);
            File fitxerXML = new File("curs.xml");

            // 1. OBJECTE -> XML (Marshalling)
            ClasseModelAmbAnotacionsJAXBXmlRootElement cursOriginal = new ClasseModelAmbAnotacionsJAXBXmlRootElement("Accés a Dades", 120);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(cursOriginal, fitxerXML);

            // 2. XML -> OBJECTE (Unmarshalling)
            Unmarshaller um = context.createUnmarshaller();
            ClasseModelAmbAnotacionsJAXBXmlRootElement cursRecuperat = (ClasseModelAmbAnotacionsJAXBXmlRootElement) um.unmarshal(fitxerXML);
            System.out.println("Conversió JAXB completada amb èxit.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}