/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend.TablaProductos;

import Backend.BotConsultas.BootConsultas;
import InstanciaADMIN.InstanciaADMIN;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Huáscar
 */
public class Productoss extends javax.swing.JFrame {

    /**
     * Creates new form Productoss
     */
    public Productoss() {
        initComponents();
        setModel();
        getDataProducto();
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);  /// lo cierra entero cuando 
        // ta buebcuierras , quitale eso y ponle un metodo this.dispose();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    
    public static int id;
    private String query;
    public  ArrayList <Object[]> dataProducto;
    private DefaultTableModel model;
    
    BootConsultas Bot = InstanciaADMIN.Bot;
    
    private void setModel() {

        Vector columnas = new Vector<String>();
        columnas.addElement("id");
        columnas.addElement("Nombre de producto");
        columnas.addElement("Valio hasta");
        columnas.addElement("Interes");
        columnas.addElement("Costo inicial");
        columnas.addElement("Costo final");
        columnas.addElement("Cliente");
        Vector filas = new Vector<Object[]>();
        
        model = new DefaultTableModel(filas, columnas);
        tabe.setModel(model);
    }
    
    public void getDataProducto(){
        this.query = "SELECT * FROM producto ";
    
        if(this.Bot.getProdcutoProductos(query)){
            
            this.dataProducto = this.Bot.getDataProducto();
            if(dataProducto.size() > 0){
               mostrarProduct();
            }else{
                System.out.println("no tienes productos "); 
            }
        }else{
            JOptionPane.showMessageDialog(null, "Fallo en la consulta");
       }
    }
    public void mostrarProduct() {
          for (Object[] o  :  dataProducto) {
           model.addRow(o);
       }         
     }

    public void limpiarTabla() {
        try {
            int filas = model.getRowCount();
            for (int i = 0; i < filas; i++) {
                model.removeRow(0);
            }
        } catch (Exception e) {
//            alerta("Error al limpiar la tabla.");
        }
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
        rSButtonMaterialTwo2 = new RSMaterialComponent.RSButtonMaterialTwo();
        rSButtonMaterialTwo3 = new RSMaterialComponent.RSButtonMaterialTwo();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabe = new RSMaterialComponent.RSTableMetroCustom();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        rSButtonMaterialTwo2.setBackground(new java.awt.Color(0, 204, 255));
        rSButtonMaterialTwo2.setText("Admin info");
        rSButtonMaterialTwo2.setBackgroundHover(new java.awt.Color(102, 153, 255));
        rSButtonMaterialTwo2.setFont(new java.awt.Font("Dubai Medium", 1, 14)); // NOI18N

        rSButtonMaterialTwo3.setBackground(new java.awt.Color(0, 204, 255));
        rSButtonMaterialTwo3.setText("Admin info");
        rSButtonMaterialTwo3.setBackgroundHover(new java.awt.Color(102, 153, 255));
        rSButtonMaterialTwo3.setFont(new java.awt.Font("Dubai Medium", 1, 14)); // NOI18N

        tabe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tabe);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(rSButtonMaterialTwo3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSButtonMaterialTwo2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rSButtonMaterialTwo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonMaterialTwo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(85, 85, 85))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Productoss.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Productoss.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Productoss.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Productoss.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Productoss().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private RSMaterialComponent.RSButtonMaterialTwo rSButtonMaterialTwo2;
    private RSMaterialComponent.RSButtonMaterialTwo rSButtonMaterialTwo3;
    private RSMaterialComponent.RSTableMetroCustom tabe;
    // End of variables declaration//GEN-END:variables
}
