
package proyectofinal1;
import Backend.DTBconeccion ; 
import Frontend.Login.Login; 
        

public class ProyectoFinal1 {

    public static void main(String[] args) {
               DTBconeccion connectToDTB = new DTBconeccion();
               connectToDTB.connectar();
               Login login = new Login();
    }
}
