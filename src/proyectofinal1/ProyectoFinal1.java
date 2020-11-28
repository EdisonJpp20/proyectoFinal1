
package proyectofinal1;
import Backend.BotConsultas.BootConsultas;
import Backend.DTBconeccion ; 
import Frontend.Login.Login; 
import InstanciaADMIN.InstanciaADMIN ;

public class ProyectoFinal1 {
    public static Login login;
    public static void main(String[] args) {
        InstanciaADMIN.coneccion  = new DTBconeccion();
        InstanciaADMIN.coneccion.connectar();
        InstanciaADMIN.Bot = new BootConsultas();
        InstanciaADMIN.Login = new Login();
               
    }
}
