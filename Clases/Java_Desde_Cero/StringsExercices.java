public class StringsExercices{
    public static void main(String[] args){
        String text1="Hola", text2 = "Mundo";
        System.out.println(text1 + " " + text2);

        System.out.println(text1.length());

        System.out.println(text1.charAt(0));
        System.out.println(text2.charAt(text2.length() -1));
        
        System.out.println(text1.toUpperCase());
        System.out.println(text2.toLowerCase());

        System.out.println(text1.contains("Hola"));

        System.out.println("   Hola que tal    ".trim()); //podemos usar trim or replacer
        
        System.out.println("Hola que tal, los espacios extra seran cambiados por -".replace(" ", "-"));
        

        String a = "Hola";
        String b = "Hola";

        System.out.println(a.equals(b));

        System.out.println(a.length() == b.length());
    }
}