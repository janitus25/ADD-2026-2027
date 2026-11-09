import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
 * APUNTES 2: FICHEROS DE TEXTO (Reader / Writer)
 * ------------------------------------------------------------
 * Formato de los datos de ejemplo (jugadores.txt):  nombre;nivel;puntos
 *     Paco;20;1000
 *     Ariel;21;5000
 *     Carlos;33;5
 *
 * - Leer:    BufferedReader(new FileReader(...))   -> readLine() devuelve null al final
 * - Escribir: BufferedWriter(new FileWriter(ruta, true))
 *       true  = AÑADIR al final
 *       false (o sin parámetro) = BORRAR y escribir de cero
 * - try-with-resources: el recurso entre paréntesis se cierra solo.
 * - split(";") corta la línea por cada ; (recibe el SEPARADOR, no la línea).
 * - Integer.parseInt(String) convierte a int (NumberFormatException si no es número).
 * - Strings se comparan con equals(), NUNCA con ==.
 * - Asegúrate de que el fichero acaba en salto de línea, o lo que añadas se pega a la última línea.
 */
public class FicherosDeTexto {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        menu();   // cambia por la receta que quieras probar
    }

    // ------------------------------------------------------------
    // Leer carácter a carácter (funciona pero es lento). read() devuelve -1 al final
    // ------------------------------------------------------------
    static void leerCaracteres(String ruta) {
        try {
            FileReader fr = new FileReader(new File(ruta));
            int c;
            while ((c = fr.read()) != -1) {
                System.out.print((char) c);
            }
            fr.close();   // en este estilo hay que cerrar a mano
        } catch (IOException e) {
            System.err.println("No se ha podido leer: " + e.getMessage());
        }
    }

    // ------------------------------------------------------------
    // Leer línea a línea (LA FORMA RECOMENDADA)
    // ------------------------------------------------------------
    static void leerLineas(String ruta) {
        File fichero = new File(ruta);

        if (!fichero.exists()) {                    // fichero que puede no existir
            System.out.println("El fichero no existe");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {      // null = fin de fichero
                String[] campos = linea.split(";");
                System.out.println(campos[0] + " | " + campos[1] + " | " + campos[2]);
            }
        } catch (IOException e) {
            System.err.println("Error de lectura: " + e.getMessage());
        }
    }

    // ------------------------------------------------------------
    // Escribir / añadir una línea al final
    // ------------------------------------------------------------
    static void anadirLinea(String ruta, String linea) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true))) {
            bw.write(linea);
            bw.newLine();          // salto de línea (si no, todo queda en la misma línea)
        } catch (IOException e) {
            System.err.println("Error al escribir: " + e.getMessage());
        }
    }

    // ------------------------------------------------------------
    // Sobrescribir un fichero con un array (false = borra lo anterior)
    // ------------------------------------------------------------
    static void sobrescribir(String ruta, String[] lineas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, false))) {
            for (String l : lineas) {
                bw.write(l);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error de escritura: " + e.getMessage());
        }
    }

    // ------------------------------------------------------------
    // RECETA: quién tiene más puntos (sin array)
    // Las variables van ANTES del while; el resultado se imprime después del while,
    // pero DENTRO del try (las variables solo existen ahí).
    // ------------------------------------------------------------
    static void masPuntos(String ruta) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            String nombreMax = "";
            int edadMax = 0;
            int puntosMax = Integer.MIN_VALUE;

            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(";");
                int puntos = Integer.parseInt(campos[2]);

                if (puntos > puntosMax) {
                    puntosMax = puntos;
                    nombreMax = campos[0];
                    edadMax = Integer.parseInt(campos[1]);
                }
            }
            System.out.println("Más puntos: " + nombreMax + " (" + edadMax + ") con " + puntosMax);
        } catch (IOException e) {
            System.err.println("Error al leer: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Hay un dato que no es un número: " + e.getMessage());
        }
    }

    // ------------------------------------------------------------
    // RECETA: sumar y contar
    // ------------------------------------------------------------
    static void sumarYContar(String ruta) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            int n = 0;
            long total = 0;
            while ((linea = br.readLine()) != null) {
                total += Integer.parseInt(linea.split(";")[2]);
                n++;
            }
            System.out.println("Registros: " + n + " | Suma: " + total);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    // ------------------------------------------------------------
    // RECETA: pedir datos por teclado y añadirlos al fichero
    // ------------------------------------------------------------
    static void anadirContacto() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();
        System.out.print("Correo: ");
        String correo = sc.nextLine();

        anadirLinea("contactes.txt", nombre + ";" + telefono + ";" + correo);
    }

    // ------------------------------------------------------------
    // RECETA: listar contactos (formato nom;telefon;correu)
    // ------------------------------------------------------------
    static void listarContactos() {
        File fichero = new File("contactes.txt");
        if (!fichero.exists()) {
            System.out.println("Todavía no hay contactos.");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] c = linea.split(";");
                System.out.println(c[0] + " | " + c[1] + " | " + c[2]);
            }
        } catch (IOException e) {
            System.err.println("Error de lectura: " + e.getMessage());
        }
    }

    // ------------------------------------------------------------
    // RECETA: buscar por nombre (equals, nunca ==)
    // ------------------------------------------------------------
    static void buscarContacto() {
        File fichero = new File("contactes.txt");
        if (!fichero.exists()) {
            System.out.println("Todavía no hay contactos.");
            return;
        }

        System.out.print("Nombre a buscar: ");
        String buscado = sc.nextLine();
        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] c = linea.split(";");
                if (c[0].equals(buscado)) {
                    System.out.println(c[0] + " | " + c[1] + " | " + c[2]);
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

    // ------------------------------------------------------------
    // RECETA: menú que se repite hasta salir
    // Orden de cada vuelta: mostrar menú -> leer opción -> actuar.
    // La opción se lee DENTRO del while o el bucle es infinito.
    // ------------------------------------------------------------
    static void menu() {
        int opcion = 0;

        while (opcion != 4) {
            System.out.println("1. Añadir contacto");
            System.out.println("2. Listar contactos");
            System.out.println("3. Buscar contacto");
            System.out.println("4. Salir");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0;           // opción no válida
            }

            if (opcion == 1) {
                anadirContacto();
            } else if (opcion == 2) {
                listarContactos();
            } else if (opcion == 3) {
                buscarContacto();
            } else if (opcion == 4) {
                System.out.println("Saliendo");
            } else {
                System.out.println("Opción no válida");
            }
        }
    }
}
