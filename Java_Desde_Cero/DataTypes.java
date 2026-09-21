
public class DataTypes{

    public static void main(String[] args){
       
        //Tipos de datos primitimos

        int myInt = 37;
        System.out.println(myInt);

        double myDouble = 1.77; // Tambien hay float, long, byte
        System.out.println(myDouble);

        char myChar = 's';
        System.out.println(myChar);

        boolean myBoolean = true;
        System.out.println(myBoolean);
        myBoolean = false;
        System.out.println(myBoolean);

        String myString = "Hola, Java";
        System.out.println(myString); 

        System.out.println(myString.getClass(). getSimpleName());
    }

}