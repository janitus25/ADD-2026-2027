/** Clase Java etiquetada con anotaciones especiales de JAXB (@XmlRootElement, @XmlAccessorType) para establecer cómo se mapeará automáticamente al formato XML. */
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "curs") 
@XmlAccessorType(XmlAccessType.FIELD)
public class ClasseModelAmbAnotacionsJAXBXmlRootElement {
    private String nom;
    private int hores;

    public ClasseModelAmbAnotacionsJAXBXmlRootElement() {}

    public ClasseModelAmbAnotacionsJAXBXmlRootElement(String nom, int hores) {
        this.nom = nom;
        this.hores = hores;
    }
}