public class Conditionals {
    public static void main (String[] args){


        //Condicionales
        int age = 18;

        if(age>18){
            System.out.println("El usuario es mayor de edad");
        }

        else if(age ==18){
            System.out.println("El usuario acaba de hacer 18 años");
        }

        else{
            System.out.println("Es menor de edad");
        }


        //Switch

        var day=4;

        switch (day) {
            case 1:
                System.out.println("Lunes");
                break;
            
            case 2:
                System.out.println("Martes");
                break;
                
            case 3:
                System.out.println("Miercoles");
                break;

            default:
                System.out.println("Error");
        }

        int a=5, b=6;

        if(a>=b){
            System.out.println("a es mayor");
        }

        else{
            System.err.println("b es mayor");
        }

        if(a<0){
            System.err.println("Negativo");
        }

        else if(a==0){
            System.err.println("Cero");
        }

        else{
            System.err.println("Positivo");
        }

        if(a%2==0){
            System.out.println("Numero par");
        }

        else{
            System.out.println("Numero impar");
        }

        if(a>=1 && a<=100){
            System.out.println("Dentro del rango");
        }

        else{
            System.out.println("Fuera del rango");
        }

        int edadd=16;
        boolean acompanyado=false;


        if (edadd >= 15 || acompanyado==true) {
            System.out.println("Puedes pasar");
        }       
        else {
            System.out.println("No puedes pasar");
        }

        char letra = 'u';

        if(letra != 'a' && letra != 'e' && letra != 'i' && letra != 'o' && letra != 'u'){
            System.out.println("Consonante");
        }

        else{
            System.out.println("Vocal");
        }
    }
}
