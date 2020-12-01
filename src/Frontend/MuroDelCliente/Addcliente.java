/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend.MuroDelCliente;

import Frontend.TablaProductos.AbstraccionProductos;
import InstanciaADMIN.InstanciaADMIN;
import javax.swing.JOptionPane;

/**
 *
 * @author Huáscar
 */
public class Addcliente extends javax.swing.JDialog {

    /**
     * Creates new form Addcliente
     */
    public Addcliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        AñadirClient = new RSMaterialComponent.RSButtonMaterialTwo();
        TxtTel = new RSMaterialComponent.RSTextFieldTwo();
        jLabel3 = new javax.swing.JLabel();
        TxtCorreo = new RSMaterialComponent.RSTextFieldTwo();
        jLabel2 = new javax.swing.JLabel();
        TxtName = new RSMaterialComponent.RSTextFieldTwo();
        jLabel1 = new javax.swing.JLabel();
        Salir = new RSMaterialComponent.RSButtonMaterialTwo();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(530, 550));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(530, 550));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(530, 550));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));
        jPanel2.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Dubai Medium", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/documento.png"))); // NOI18N
        jLabel5.setText("Añade un nuevo cliente!");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(14, 19, 295, 64);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 530, 90);

        AñadirClient.setBackground(new java.awt.Color(51, 153, 255));
        AñadirClient.setText("Añadir");
        AñadirClient.setFocusable(false);
        AñadirClient.setFont(new java.awt.Font("Dubai Medium", 1, 14)); // NOI18N
        AñadirClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AñadirClientActionPerformed(evt);
            }
        });
        jPanel1.add(AñadirClient);
        AñadirClient.setBounds(260, 480, 184, 50);

        TxtTel.setForeground(new java.awt.Color(51, 153, 255));
        TxtTel.setBorderColor(new java.awt.Color(51, 153, 255));
        TxtTel.setPhColor(new java.awt.Color(51, 153, 255));
        TxtTel.setPlaceholder("Telefono...");
        TxtTel.setSelectionColor(new java.awt.Color(51, 153, 255));
        jPanel1.add(TxtTel);
        TxtTel.setBounds(10, 380, 250, 42);

        jLabel3.setFont(new java.awt.Font("Dubai Medium", 1, 18)); // NOI18N
        jLabel3.setText("Telefono:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(10, 340, 140, 22);

        TxtCorreo.setForeground(new java.awt.Color(51, 153, 255));
        TxtCorreo.setBorderColor(new java.awt.Color(51, 153, 255));
        TxtCorreo.setPhColor(new java.awt.Color(51, 153, 255));
        TxtCorreo.setPlaceholder("Correo elcetronico...");
        TxtCorreo.setSelectionColor(new java.awt.Color(51, 153, 255));
        jPanel1.add(TxtCorreo);
        TxtCorreo.setBounds(10, 280, 250, 42);

        jLabel2.setFont(new java.awt.Font("Dubai Medium", 1, 18)); // NOI18N
        jLabel2.setText("Email: ");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(10, 240, 140, 22);

        TxtName.setForeground(new java.awt.Color(51, 153, 255));
        TxtName.setBorderColor(new java.awt.Color(51, 153, 255));
        TxtName.setPhColor(new java.awt.Color(51, 153, 255));
        TxtName.setPlaceholder("Nombre...");
        TxtName.setSelectionColor(new java.awt.Color(51, 153, 255));
        jPanel1.add(TxtName);
        TxtName.setBounds(10, 180, 250, 42);

        jLabel1.setFont(new java.awt.Font("Dubai Medium", 1, 18)); // NOI18N
        jLabel1.setText("Nombre:");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 150, 140, 22);

        Salir.setBackground(new java.awt.Color(255, 51, 51));
        Salir.setText("Salir");
        Salir.setFont(new java.awt.Font("Dubai Medium", 1, 14)); // NOI18N
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });
        jPanel1.add(Salir);
        Salir.setBounds(90, 480, 167, 50);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/rick.gif"))); // NOI18N
        jPanel1.add(jLabel4);
        jLabel4.setBounds(310, 110, 205, 320);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(-1, -1, 530, 550);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_SalirActionPerformed

    private void AñadirClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AñadirClientActionPerformed
        ValidarYanadir();
        this.dispose();
        
    }//GEN-LAST:event_AñadirClientActionPerformed
    public static void agregarClient(String[] fiels) {
        String query = " INSERT INTO cliente (nombre,telefono,email ) \n"
                        + " VALUES (' "+ fiels[0] + "','" + fiels[1] + "', '" + fiels[2] + "')";
//        ," + id + "
        if(InstanciaADMIN.Bot.bootAgregarEliminarActualizar(query)){
            JOptionPane.showMessageDialog(null, "Agregado Correctamente");
        }
    }
    
    public void ValidarYanadir(){
        String nombre = this.TxtName.getText();
        String tel = this.TxtTel.getText();
        String correo = this.TxtCorreo.getText();

      

        String[] campos = {nombre, tel, correo   };
        boolean validate = true;

        for (String value : campos) {
            if (value.length() < 2) {
                validate = false;
            }
        }

        if (validate) {
            agregarClient(campos);
            
        } else {
            JOptionPane.showMessageDialog(this, "faltan campos");
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Addcliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Addcliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Addcliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Addcliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Addcliente dialog = new Addcliente(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonMaterialTwo AñadirClient;
    private RSMaterialComponent.RSButtonMaterialTwo Salir;
    private RSMaterialComponent.RSTextFieldTwo TxtCorreo;
    private RSMaterialComponent.RSTextFieldTwo TxtName;
    private RSMaterialComponent.RSTextFieldTwo TxtTel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
