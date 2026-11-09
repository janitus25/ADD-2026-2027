import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class agendaDeContactos {
    public static void main(String[] args) {
        
        Menu();

    }

    public static void Menu(){
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        System.out.println("Que quieres hacer: ");
        
        while(opcion!=4){
            System.out.println("1. Añadir contacto: ");
            System.out.println("2. Listar contactos: ");
            System.out.println("3. Buscar contactos: ");
            System.out.println("4. Salir: ");
            opcion = Integer.parseInt(sc.nextLine());

            if(opcion==1){
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            System.out.println();

            System.out.print("Telefono: ");
            String telefono = sc.nextLine();
            System.out.println();

            System.out.print("Correo: ");
            String correo = sc.nextLine();
            System.out.println();

            String linea = nombre.toUpperCase() + ";" + telefono + ";" + correo;

            try(BufferedWriter bw = new BufferedWriter(new FileWriter("contactes.txt", true))) { //True se usa para ponerlo debajo, con false borrariamos los archivos
                bw.write(linea);
                bw.newLine();
            } 
            
            catch (IOException e) {
                System.err.println("Error al escribir: " + e.getMessage());
            }
        }

        else if(opcion == 2){

    File fichero = new File("contactes.txt");
    if (!fichero.exists()) {
        System.out.println("Todavía no hay contactos.");
        continue;
    }

    try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] campos = linea.split(";");
            System.out.println(campos[0] + " | " + campos[1] + " | " + campos[2]);
        }
    } catch (IOException e) {
        System.err.println("Error al leer el archivo: " + e.getMessage());
    }
}

        else if(opcion == 3){
                
                File fichero = new File("contactes.txt");
            if (!fichero.exists()) {
                System.out.println("Todavía no hay contactos.");
                continue;
            }

            System.out.print("Nombre a buscar: ");
            String buscado = sc.nextLine();
            boolean encontrado = false;

            try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] c = linea.split(";");
                    if (c[0].equalsIgnoreCase(buscado)) {
                        System.out.println(c[0].toUpperCase() + " | " + c[1] + " | " + c[2]);
                        encontrado = true;
                    }
                }
                if (!encontrado) {
                    System.out.println("No existe ese contacto.");
                }
            } catch (IOException e) {
                System.err.println("Error de lectura: " + e.getMessage());
            }
    
            
        }

        else if(opcion==4){
            System.out.println("Saliendo");
        }
        
        }

    }
}
