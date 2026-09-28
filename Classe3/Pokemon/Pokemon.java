import java.io.Serializable;

    public class Pokemon implements Serializable{

        private String nombre;
        private int vida;
        
        public Pokemon(){
            nombre = "Ditto";
            vida = 60;
        }

        public Pokemon(String nombre, int vida){
            this.nombre = nombre; //Esto en c++ es nombre = _nombre 
            this.vida = vida;
        }

        public String getNombre(){
            return nombre;
        }


        public int Vida(){
            return vida;
        }

    }


