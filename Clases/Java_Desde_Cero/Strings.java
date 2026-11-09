public class Strings {
    public static void main(String[] args) {
        
        String name = "Jan";
        String age = "19";

        System.out.println(name + " " + age);

        System.out.println(name.length()); //Para ver la cantidad de caracteres de un string por ejemplo
    
         //Obtener caracter
        System.out.println(name.charAt(1)); //En este caso con el charAt, sacamos el caracter de la posicion 1 en este caso la a, la J seria la posicion 0
        
        System.out.println(name.charAt(name.length() - 1)); //Usando esto nos devolvera el ultimo caracter, asi no es necesario saber los caracteres que tiene la palabra

        //Subcadena
        //Ctrl + espacio te lanza suggestions

        System.out.println(name.substring(0)); //Esto te muestra todo el nombre por ejemplo, porque empieza en el punto 0
        
        System.out.println(name.substring(0, 3));//Es lo mismo que el de arriba pero indicandole principio y final
        
        //Mayusculas y minusculas

        System.out.println(name.toUpperCase()); //Imprime el nombre en mayusculas
        System.out.println(name.toLowerCase());//Imprime el nombre en minusculas
        System.out.println(name); //Sigue manteniendo su valor original
    
        // Comprobar si contiene
        System.out.println("Hola, Java".contains("Jan")); //Esto quiere decir, si la primera frase, contiene la palabra Jan en este caso, como no la contiene devuelve falso
        
        System.out.println("Hola, Java".toLowerCase().contains("hola")); //En este caso devuelve true porque pasamos todo el mensaje a minusculas, y comparamos con un mensaje escrito ya en minusculas
        
        // Comparacion

        System.out.println(name.equals("Jan")); //Usamos el .equals para comparar caracteres
        System.out.println(name.equalsIgnoreCase("jan")); //Lo mismo que el equals pero ignorando si es mayuscula o minuscula
        
        // Trim, limpia los espacios de las cadenas de texto
        System.out.println("   Hola me llamo Jan   ");
        System.out.println("   Hola me llamo Jan   ".trim());
        
        //Replace, sirve para remplazar cosas en el texto, en este caso los espacios pero podria poner Jan, y quitarlo por Paco por ejemplo

        System.out.println("   Hola me llamo Jan   ".replace(" ", ""));
    
        // Format
        
        var agee = 19;
        System.out.println(String.format("Hola, %s. Tengo %d años", name, agee)); ///Esto es muy parecido a C, %s para strings, y %d para int
    }
}
