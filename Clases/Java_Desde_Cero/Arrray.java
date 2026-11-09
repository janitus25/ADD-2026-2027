import java.util.ArrayList;

public class Arrray {
    public static void main(String[] args) {
        
        //Declaracion y creacion de un array

        int[] numbers = new int[3];
        System.out.println(numbers);

        String[] names = {"Jan", "19", "33"};
        System.out.println(names);

        //Acceso
        System.out.println(numbers[2]);
        System.out.println(names[0]);
        System.out.println(names[1]);
        System.out.println(names[2]);

        
        System.out.println(numbers[0]);
        System.out.println(names[0]);
        
        System.out.println((new String [3])[0]);

        //Modificar
        numbers[0] = 1;
        numbers[1] = 10;
        System.out.println(numbers[0]);
        System.out.println(numbers[1]);
        System.out.println(numbers[2]);

        //Listas
        ArrayList <String> namesss = new ArrayList<>();
        
        //For-each: para cada uno de los elementos

        for(String name: names){ //Es simplemente, para almacenar cada elemento por separado, creo la variable name, que recorrera el array de names
            System.out.println(name);
        } //Tambien aplica a otro tipo de variables
    }
}
