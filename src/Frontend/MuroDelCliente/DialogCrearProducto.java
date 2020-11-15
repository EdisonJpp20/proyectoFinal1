/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend.MuroDelCliente;

//import DialogoAnimacion.DialogWithAnimation;
//import Frontend.Login.Login;
import java.awt.Dimension;
//import InstanciaADMIN.InstanciaADMIN ;


/**
 *
 * @author edisonjpp
 */
public class DialogCrearProducto extends javax.swing.JDialog {
    public DialogCrearProducto(){
        setPreferredSize(new Dimension(600, 665));
        setMinimumSize(new Dimension(600, 665));
        this.setLocationRelativeTo(null);
        this.add(new CrearProductos());
        this.setVisible(true);
    }
    
   public void  cerrar (){
       System.out.println("holaaaa");
           this.dispose();
   }
}

