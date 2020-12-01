/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend.Inicio;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JDialog;

/**
 *
 * @author edisonjpp
 */
public class MuroADMINDialog extends JDialog {
    public MuroADMINDialog(ArrayList<String> dataADMIN){
        this.add(new MuroADMIN(dataADMIN));
       // this.setPreferredSize(new Dimension(325, 377));
        this.setMinimumSize(new Dimension(325, 385));
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setVisible(true);
   
    }
    

    
    public void cerrar(){
        this.dispose();
    }
    
}
