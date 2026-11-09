import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.*;
import org.w3c.dom.*;

public class Ejercicio4_LeerXML {
    public static void main(String[] args) {
        File ruta = new File("colegio.xml");
        try {
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            // Protección adicional al leer XML no confiable: impedir DTD externos.
            
            fabrica.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            DocumentBuilder db = fabrica.newDocumentBuilder();
            Document doc = db.parse(ruta);
            doc.getDocumentElement().normalize();
            NodeList colegio = doc.getElementsByTagName("colegio"); // CAMBIA etiqueta
            System.out.println("Colegio: " + colegio.getLength());

            NodeList director = doc.getElementsByTagName("director"); // CAMBIA etiqueta
            System.out.println("Colegio: " + director.getLength());
           
            for (int i = 0; i < colegio.getLength(); i++) {
                Node nodo = colegio.item(i);
                if (nodo.getNodeType() != Node.ELEMENT_NODE) continue;
                Element e = (Element) nodo;
                String alumno = e.getAttribute("alumnos");
                String curso = e.getElementsByTagName("curso");
                float nota = e.getElementsByTagName("nota");
                System.out.println("alumno=" + alumno + " | " + curso + " " + nota);
            }
        } 
        
        catch (Exception e) { System.out.println("Error XML DOM: " + e.getMessage()); }
    }
    
    /* 
    static void leerNotasYDecirlas(String ruta) {
        File fichero = new File(ruta);

        if (!fichero.exists()) {                    
            System.out.println("El fichero no existe");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            
            String linea;
            int aprobaos=0;
            float media=0;
            int totalAlumnos=0;
            int suspendidos=0;
            
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(";");
                System.out.println(campos[0] + ": " + campos[1]);

                int notas = Integer.parseInt(campos[1]);
                totalAlumnos++;

                if(notas >=5){
                    aprobaos++;
                }

                else{
                    suspendidos++;
                }
                media += notas;
                
                
            }

            media = media / totalAlumnos;

            System.out.println("");
            System.out.println("Aprobados: " + aprobaos);
            System.out.println("Nota media: "+ media);

        } 
        
        catch (IOException e) {
            System.err.println("Error de lectura: " + e.getMessage());
        }
        */
}

