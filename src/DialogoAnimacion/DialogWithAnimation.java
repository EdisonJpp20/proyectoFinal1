package DialogoAnimacion;

import java.awt.Dimension;
//import java.awt.Point;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.swing.JFrame;
//import javax.swing.JOptionPane;
import rojeru_san.complementos.RSAnimation;

public class DialogWithAnimation extends javax.swing.JDialog {

    @SuppressWarnings("unchecked")
    private void initComponents() {//GEN-BEGIN:initComponents
        //setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        // setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        //setUndecorated(true);
        pack();
    }//GEN-END:initComponents

//    public DialogWithAnimation(java.awt.Frame parent, boolean modal, Dimension dim) {
//        super(parent, modal);
//
//        initComponents();
//        RSAnimation f = new RSAnimation();
////        f.setBajar(-360, 30, 1, 1, this);
//        f.setBajar(-350, 30, 1, 1, this);
//        ////JOptionPane.showMessageDialog(null,"edison th ebst ");
//
//    }
    
    public DialogWithAnimation(java.awt.Frame parent, boolean modal, Dimension dim) {
        super(parent, modal);
        initComponents();
//
//        setPreferredSize(new Dimension(588, 644));
//        setMinimumSize(new Dimension(588, 644));
        RSAnimation f = new RSAnimation();
        f.setBajar(-350, 30, 1, 1, this);
    }

    public void cerrar() {
//         RSAnimation f = new RSAnimation();
//         f.setSubir(170, -350, 1, 2, this);

        RSAnimation f = new RSAnimation();
        f.setSubir(170, -350, 1, 2, this);
//         Thread.sleep(500);
        this.dispose();

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
