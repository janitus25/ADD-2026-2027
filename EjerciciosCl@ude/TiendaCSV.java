import java.io.*;
import java.util.Scanner;

public class TiendaCSV {
    
    static final String FICHERO = "productos.txt";

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Menu();
    }

    public static void Menu() {
        int opcion = 0;

        do {
            System.out.println("\n===== TIENDA =====");
            System.out.println("1. Afegir producto");
            System.out.println("2. Llistar producto");
            System.out.println("3. Buscar por nombre");
            System.out.println("4. Producto mas caro");
            System.out.println("5. Valor total del inventario");
            System.out.println("6. Salir");
            System.out.print("Opcio: ");

            try {
                opcion = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                opcion = 0;             // fuerza el "opción incorrecta"
            }

            switch (opcion) {
                case 1:
                    añadirProducto();
                    break;
                case 2:
                    leerListaCSV();
                    break;
                case 3:
                    Cercar();
                    break;

                 case 4:
                    ProductoMasCaro();
                    break;
                    
                 case 5:
                    ValorTotalInventario();
                    break;

                case 6:
                    System.out.println("Adeu!");
                    break;
                default:
                    System.out.println("Opcio incorrecta");
            }

        } while (opcion != 6);
    }

    public static void añadirProducto() {

        System.out.print("Nombre ");
        String nombre = sc.nextLine().trim();
        System.out.print("Precio: ");
        float precio = sc.nextFloat();
        System.out.print("Stock: ");
        int stock = sc.nextInt();

        // El separador no puede aparecer dentro de un campo: lo cambiamos por una coma
        nombre = nombre.replace(";", ",");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("productos.txt", true))) {
            bw.write(nombre + ";" + precio + ";" + stock);
            bw.newLine();
            System.out.println("Producto añadido.");
        } catch (IOException e) {
            System.err.println("Error d'escriptura: " + e.getMessage());
        }
    }

    public static void leerListaCSV() {

        File fichero = new File("productos.txt");

        if (!fichero.exists()) {
            System.out.println("Todavia no hay productos.");
            return;
        }

        int n = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] c = linea.split(";");

                if (c.length < 3) {
                    continue;
                }

                System.out.println("Nombre: " + c[0] + " | Precio: " + c[1] + " | Stock: " + c[2]);
                n++;
            }

            if (n == 0) {
                System.out.println("La tienda esta vacia.");
            }

        } catch (IOException e) {
            System.err.println("Error de lectura: " + e.getMessage());
        }
    }

    public static void Cercar() {

        File fichero = new File("productos.txt");

        if (!fichero.exists()) {
            System.out.println("Todavia no hay productos.");
            return;
        }

        System.out.print("Nombre a cercar: ");
        String buscado = sc.nextLine().trim();
        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] c = linea.split(";");

                if (c.length < 3) {
                    continue;
                }

                if (c[0].equalsIgnoreCase(buscado)) {       // sin distinguir mayúsculas
                    System.out.println(c[0] + " | " + c[1] + " | " + c[2]);
                    encontrado = true;
                }
            }

            if (!encontrado) {
                System.out.println("No existe este producto.");
            }

        } catch (IOException e) {
            System.err.println("Error de lectura: " + e.getMessage());
        }
    }


    public static void ProductoMasCaro() {

       try (BufferedReader br = new BufferedReader(new FileReader("productos.txt"))) {
            
            String linea;
            String nombre = "";
            float precioMax = 0;
            int stock = Integer.MIN_VALUE;

            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(";");
                
                float precio = Float.parseFloat(campos[1]);

                if (precio > precioMax) {
                    precioMax = precio ;
                    nombre = campos[0];
                    stock = Integer.parseInt(campos[2]);
                }
            }
            System.out.println("Producto mas caro: " + nombre + " (" + precioMax + ") con " + stock + " de stock");
        } 
        
        catch (IOException e) {
            System.err.println("Error al leer: " + e.getMessage());
        } 
        
        catch (NumberFormatException e) {
            System.err.println("Hay un dato que no es un número: " + e.getMessage());
        }

    }

    

    public static void ValorTotalInventario() {

        try (BufferedReader br = new BufferedReader(new FileReader("productos.txt"))) {
            
            String linea;
            float resultado=0;

            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(";");
                
                float precio = Float.parseFloat(campos[1]);
                int stock = Integer.parseInt(campos[2]);

                resultado += precio*stock; 
                
            }
            System.out.println("Precio del inventario: " + resultado);
        } 
        
        catch (IOException e) {
            System.err.println("Error al leer: " + e.getMessage());
        } 
        
        catch (NumberFormatException e) {
            System.err.println("Hay un dato que no es un número: " + e.getMessage());
        }

    }


}
