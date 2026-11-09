import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Gestor {
    
    public static void main(String[] args) {
        Gestor g = new Gestor();
        g.GuardarJuego();
        g.LeerJuego();
        g.LeerJuegosObjetos();
    }


    void GuardarJuego() {

        ArrayList<Videojuego> juego = new ArrayList<>();

        juego.add(new Videojuego("GTA V", "Rockstar Games", 2013, 20f, 9.9f));
        juego.add(new Videojuego("Minecraft", "Mojang", 2011, 50f, 9.5f));
        juego.add(new Videojuego("The Witcher 3", "CD Projekt Red", 2015, 80f, 9.7f));
        juego.add(new Videojuego("Red Dead Redemption 2", "Rockstar Games", 2018, 60f, 9.8f));


        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Videojuegos.dat"))) {
            
            for(int i=0;i < juego.size();i++){
                oos.writeObject(juego.get(i));
            }
            

        } 
        
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("VideojuegosLista.dat"))) {

            oos.writeObject(juego); //Para guardar la lista entera, no necesitamos el for

        } 
        
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    void LeerJuego() {
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("VideojuegosLista.dat"))) {
                ArrayList<Videojuego> juego = (ArrayList<Videojuego>) ois.readObject();     
                
                for(int i=0;i<juego.size();i++){
                    System.out.println("Titulo: " + juego.get(i).GetTitulo());
                    System.out.println("Desarrolladora: " + juego.get(i).GetDesarrolladora());
                    System.out.println("Año: " + juego.get(i).GetAnyo());
                    System.out.println("Horas de juego: " + juego.get(i).GetHoras());
                    System.out.println("Valoracion: " + juego.get(i).GetValoracion());
                }
        } 
        
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }


        try (ObjectInputStream ois = new ObjectInputStream (new FileInputStream("VideojuegosLista.dat"))){
            
            ArrayList<Videojuego> juego = (ArrayList<Videojuego>) ois.readObject();
            Videojuego MashorasJuego = juego.get(0);

            for (int i = 0; i < juego.size(); i++) {
                
                if(MashorasJuego.GetHoras() < juego.get(i).GetHoras()){
                    MashorasJuego = juego.get(i);
                }
                
            }   

            System.out.println("La pelicula mas larga es: ");
            System.out.println("Titulo: " + MashorasJuego.GetTitulo());
            System.out.println("Desarrolladora: " + MashorasJuego.GetDesarrolladora());
            System.out.println("Año: " + MashorasJuego.GetHoras());
            System.out.println("Horas de juego: " + MashorasJuego.GetHoras());
            System.out.println("Valoracion: " + MashorasJuego.GetValoracion());
            
        } 

        catch (Exception e) {
            System.err.println("Error" + e.getMessage());
        }

    }

    void LeerJuegosObjetos(){

        try (ObjectInputStream ois = new ObjectInputStream (new FileInputStream("Videojuegos.dat"))) {
        
            for(int i=0;i<4;i++){
                Videojuego juego = (Videojuego) ois.readObject();
                
                System.out.println("Titulo: " + juego.GetTitulo());
                System.out.println("Desarrolladora: " + juego.GetDesarrolladora());
                System.out.println("Año: " + juego.GetAnyo());
                System.out.println("Horas de juego: " + juego.GetHoras());
                System.out.println("Valoracion: " + juego.GetValoracion());
            }  
            
        } 
        
        catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }

    }
}

