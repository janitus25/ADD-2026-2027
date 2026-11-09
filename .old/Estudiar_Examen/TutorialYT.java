
import java.io.EOFException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;

public class TutorialYT {
    
    private File fichero;
    private ArrayList objetos;


    public void GestorFicheroSerializado(String fichero){
        this.fichero = new File(fichero);
        this.objetos = new ArrayList<>();
        obtenerDatos();
    }

    public void GestorFicheroSerializado(File fichero){
        this.fichero = fichero;
        this.objetos = new ArrayList<>();
        obtenerDatos();
    }

    public void obtenerDatos(){
        
        if(fichero.exists()){

            T elemento;

            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero));) {

                while (true) { 
                    elemento=ois.readObject();
                    objetos.add(elemento);
                }

            } 
            
            catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }

            catch (EOFException e) {

            }

            catch (IOException e) {

            }

            catch (ClassNotFoundException e) {

            }
            
        }

    }

}
