import java.io.*;
import java.util.ArrayList;

public class Gestor {

    public static void main(String[] args) {
        Gestor g = new Gestor();
        g.GuardarPelicula();
        g.LeerPelicula();
        g.LeerPeliculaObjetos();
    }

    void GuardarPelicula() {

        ArrayList<Pelicula> peliculas = new ArrayList<>();

        peliculas.add(new Pelicula("Avatar", "James Cameron", 2009, 2.42f, 7.9f));
        peliculas.add(new Pelicula("Interstellar", "Christopher Nolan", 2014, 2.49f, 8.7f));
        peliculas.add(new Pelicula("Titanic", "James Cameron", 1997, 3.14f, 7.9f));
        peliculas.add(new Pelicula("Matrix", "Lana Wachowski", 1999, 2.16f, 8.2f));
        peliculas.add(new Pelicula("Gladiator", "Ridley Scott", 2000, 2.35f, 8.5f));
        peliculas.add(new Pelicula("Jurassic Park", "Steven Spielberg", 1993, 2.07f, 8.1f));


        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("PeliculaObjetos.dat"))) {
            
            for(int i=0;i < peliculas.size();i++){
                oos.writeObject(peliculas.get(i));
            }
            

        } 
        
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("PeliculaLista.dat"))) {

            oos.writeObject(peliculas); //Para guardar la lista entera, no necesitamos el for

        } 
        
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    void LeerPelicula() {
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("PeliculaLista.dat"))) {
                ArrayList<Pelicula> peliculas = (ArrayList<Pelicula>) ois.readObject();     
                
                for(int i=0;i<peliculas.size();i++){
                    System.out.println("Titulo: " + peliculas.get(i).GetTitulo());
                    System.out.println("Director: " + peliculas.get(i).GetDirector());
                    System.out.println("Año: " + peliculas.get(i).GetAnyo());
                    System.out.println("Duracion: " + peliculas.get(i).GetDuracion());
                    System.out.println("Valoracion: " + peliculas.get(i).GetValoracion());
                }
        } 
        
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }


        try (ObjectInputStream ois = new ObjectInputStream (new FileInputStream("PeliculaLista.dat"))){
            
            ArrayList<Pelicula> peliculas = (ArrayList<Pelicula>) ois.readObject();
            Pelicula peliculaMasLarga = peliculas.get(0);

            for (int i = 0; i < peliculas.size(); i++) {
                
                if(peliculaMasLarga.GetDuracion() < peliculas.get(i).GetDuracion()){
                    peliculaMasLarga = peliculas.get(i);
                }
                
            }   

            System.out.println("La pelicula mas larga es: ");
            System.out.println("Titulo: " + peliculaMasLarga.GetTitulo());
            System.out.println("Director: " + peliculaMasLarga.GetDirector());
            System.out.println("Año: " + peliculaMasLarga.GetAnyo());
            System.out.println("Duracion: " + peliculaMasLarga.GetDuracion());

            System.out.println("Valoracion: " + peliculaMasLarga.GetValoracion());
        } 

        catch (Exception e) {
            System.err.println("Error" + e.getMessage());
        }


        

    }


    void LeerPeliculaObjetos(){

        

        try (ObjectInputStream ois = new ObjectInputStream (new FileInputStream("PeliculaObjetos.dat"))) {
        
            for(int i=0;i<6;i++){
                Pelicula pelicula = (Pelicula) ois.readObject();
                
                System.out.println("Titulo: " + pelicula.GetTitulo());
                System.out.println("Director: " + pelicula.GetDirector());
                System.out.println("Año: " + pelicula.GetAnyo());
                System.out.println("Duracion: " + pelicula.GetDuracion());
                System.out.println("Valoracion: " + pelicula.GetValoracion());
            }
            
            
        } 
        
        catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }

    }
}