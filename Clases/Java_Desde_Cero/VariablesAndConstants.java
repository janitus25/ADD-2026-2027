
public class VariablesAndConstants{

    public static void main(String[] args) {
        
        String name = "Jan";
        System.out.println(name);

        name = "Janitus25";
        System.out.println(name);

        int edad=19;
        System.out.println(edad);

        //Constantes

        final String EMAIL = "mouredev@gmail.com"; //Para hacer una variable constante hay que ponerle delante un final
        System.out.println(EMAIL); // Como buena practica ponemos el nombre entero de las constantes en mayusculas

        var anyo = 2026; //Podemos poner var como variable, y automaticamente nos detecta que en este caso es un int

        System.out.println(anyo);
    }

}