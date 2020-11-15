/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend.MuroDelCliente;

import java.awt.Dimension;
import java.util.ArrayList;

/**
 *
 * @author edisonjpp
 */
public class EditarClienteDialog extends javax.swing.JDialog {

    public EditarClienteDialog(ArrayList clienteData) {
        this.add(new EditarCliente( clienteData));
        this.setMinimumSize(new Dimension(483, 555));
        this.setPreferredSize(new Dimension(483, 555));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    public void cerrar(){
        this.dispose();
    }

}
