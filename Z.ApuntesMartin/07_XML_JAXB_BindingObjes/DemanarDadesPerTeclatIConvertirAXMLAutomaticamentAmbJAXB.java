/** Recoge datos por teclado, instancia el objeto de la clase mapeada y utiliza el Marshaller de JAXB para generar un XML automáticamente sin tratar con etiquetas manuales. */
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import java.io.File;
import java.util.Scanner;

public class DemanarDadesPerTeclatIConvertirAXMLAutomaticamentAmbJAXB {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introdueix el nom del curs: ");
        String nom = sc.nextLine();
        System.out.print("Introdueix les hores del curs: ");
        int hores = sc.nextInt();

        try {
            JAXBContext context = JAXBContext.newInstance(ClasseModelAmbAnotacionsJAXBXmlRootElement.class);
            ClasseModelAmbAnotacionsJAXBXmlRootElement c = new ClasseModelAmbAnotacionsJAXBXmlRootElement(nom, hores);

            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); 
            
            File fitxerSortida = new File("curs_jaxb.xml");
            m.marshal(c, fitxerSortida); 

            System.out.println("Objecte convertit a XML i desat a " + fitxerSortida.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        sc.close();
    }
}