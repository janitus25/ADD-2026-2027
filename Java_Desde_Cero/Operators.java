public class Operators{

    public static void main(String[] args) {
        
        var a=5.00;
        var b=2.00;

        System.out.println(a+b);
        System.out.println(a-b);
        System.out.println(a*b);
        System.out.println(a/b);
        System.out.println(a%b); //Esto es el modulo, es el resultado del resto de la division



        //Operadores de asignacion
        a = b;
        System.out.println(a);

        a = b * 2;
        System.out.println(a);

        a += 1; // a = a + 1
        System.out.println(a);

        a -= 1;
        System.out.println(a);
        a *= 2;
        System.out.println(a);
        a /= 2;
        System.out.println(a);

        //Operadores de comparacion o relacionales
        System.out.println(a == b);
        System.out.println(a == 4);

        System.out.println(a != b);

        System.out.println(a < b);
        System.out.println(a <= b);
        System.out.println(a > b);
        System.out.println(a >= b);

        //Operadores logicos, solo es verdadera cuando es verdadero y verdadero
        System.out.println(true && true);
        System.out.println(true && false);
        System.out.println(false && true);
        System.out.println(false && false);
        
        System.out.println(3>2 && 5==2);


        //Con el || si hay verdadero nos da verdadero, solo nos da falsa si es false y false
        System.out.println(true || true);
        System.out.println(true || false);
        System.out.println(false || true);
        System.out.println(false || false);

        System.out.println(3>2 || 5==2);


        //Este es el operador logico de negacion !, en este caso, si tenemos true y ponemos delante el !, se vuelve false y a la inversa
        System.out.println(!true);
        System.out.println(!false);

        System.out.println(!(3>2) || 5==2);

        //Operadores unarios
        System.out.println(+b);
        System.out.println(-b);
        System.out.println(b++);
        System.out.println(++b);
        System.out.println(--b);
        System.out.println(b--);

        b++;
        System.out.println(b);
    }
}