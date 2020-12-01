/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend.ClientesView;

import Frontend.MuroDelCliente.EditarClienteDialog;
import Frontend.MuroDelCliente.MuroDelCliente;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import InstanciaADMIN.InstanciaADMIN;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author edisonjpp
 */
public class ClientesMain extends javax.swing.JFrame {

    /**
     * Creates new form ProductosMain
     */
    public ClientesMain() {
        initComponents();
        setModel();
        getData();
        this.setLocationRelativeTo(null);
//        this.setUndecorated(false);
        this.setVisible(true);
    }
    DefaultTableModel model;
    ArrayList<String> dataToShow;

    private void setModel() {

        Vector columnas = new Vector<String>();
        columnas.addElement("id");
        columnas.addElement("Nombre");
        columnas.addElement("Telefono");
        columnas.addElement("Correo");
        columnas.addElement("Cantidad de Productos ");
        Vector filas = new Vector<String>();

        model = new DefaultTableModel(filas, columnas);
        rSTableMetroCustom1.setModel(model);
    }

    private void getData() {
        String query = " SELECT cliente.id, cliente.nombre, cliente.telefono , cliente.email,\n"
                + " count(producto.clienteId) as cantidadProdutos \n"
                + "FROM cliente JOIN producto on producto.clienteId = cliente.id \n"
                + "GROUP BY cliente.id  ORDER BY cantidadProdutos DESC ";
        if (InstanciaADMIN.Bot.getClientes(query)) {
            this.dataToShow = InstanciaADMIN.Bot.getDataClienteOrClientes();
            System.out.println("cliente Manin");
            System.out.println(this.dataToShow);
            show(this.dataToShow);
        } else {
            alerta("coneccion defectuaosa");
        }
    }

    private void show(ArrayList<String> dataToShows) {
        for (Object g : dataToShows) {
            Object[] dataOfClients;
            dataOfClients = (Object[]) g;
            this.model.addRow(dataOfClients);
        }
    }

    private void goToTheMuro() {
        if (rSTableMetroCustom1.getSelectedRow() != -1) {
            int index = rSTableMetroCustom1.getSelectedRow();
            int id = (int) Integer.parseInt((String) String.valueOf((Object) model.getValueAt(index, 0)));
            InstanciaADMIN.MuroDelCliente = new MuroDelCliente(id);
            cerrar(false);
        } else {
            alerta("Favor de seleccionar un cliente");
        }
    }

    private void editarCliente() {
        if (rSTableMetroCustom1.getSelectedRow() != -1) {
            int index = rSTableMetroCustom1.getSelectedRow();
            String id = String.valueOf((Object) model.getValueAt(index, 0));
            String nombre = String.valueOf((Object) model.getValueAt(index, 1));
            String telefono = String.valueOf((Object) model.getValueAt(index, 2));
            String email = String.valueOf((Object) model.getValueAt(index, 3));

            ArrayList dataCliente = new ArrayList();

            dataCliente.add(id);
            dataCliente.add(nombre);
            dataCliente.add(telefono);
            dataCliente.add(email);

            InstanciaADMIN.EditarClienteDialog = new EditarClienteDialog(dataCliente);
        } else {
            alerta("Favor de Seleccionar uno");
        }

    }

    public boolean eliminarProducto() {
        if (rSTableMetroCustom1.getSelectedRow() != -1) {
            int index = rSTableMetroCustom1.getSelectedRow();
            int id = (int) Integer.parseInt(String.valueOf((Object) model.getValueAt(index, 0)));
           String query = "CALL DeleteToClienteView (  "+ id +" )" ; 

            if (InstanciaADMIN.Bot.bootAgregarEliminarActualizar(query)) {
                alerta("Eliminado correctamente");
                model.removeRow(index);

                return true;
            } else {
                alerta("No se elimino correctamente");
                return false;
            }
        } else {

            alerta("favor de seleccionar el producto que desea eliminar");
            return false;
        }
    }

    public void cerrar(boolean cierto) {
        this.dataToShow.clear();
        limpiarTabla();
        this.dispose();
        InstanciaADMIN.Inicio.cerrar(cierto);

    }

    public void limpiarTabla() {
        try {
            int filas = model.getRowCount();
            for (int i = 0; i < filas; i++) {
                model.removeRow(0);
            }
        } catch (Exception e) {
            alerta("Error al limiar la tabla");
        }
    }

    private void alerta(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Volverr = new RSMaterialComponent.RSButtonMaterialTwo();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        rSTableMetroCustom1 = new RSMaterialComponent.RSTableMetroCustom();
        jPanel4 = new javax.swing.JPanel();
        rSButtonMaterialTwo1 = new RSMaterialComponent.RSButtonMaterialTwo();
        rSButtonMaterialTwo2 = new RSMaterialComponent.RSButtonMaterialTwo();
        rSButtonMaterialTwo3 = new RSMaterialComponent.RSButtonMaterialTwo();
        jLabel2 = new javax.swing.JLabel();

        jPanel6.setBackground(new java.awt.Color(255, 51, 51));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel5MouseEntered(evt);
            }
        });
        jPanel6.setLayout(null);

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Volver");
        jPanel6.add(jLabel4);
        jLabel4.setBounds(27, 11, 84, 24);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(884, 521));

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));

        jLabel1.setFont(new java.awt.Font("Dubai Medium", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Bienvenido al main de los clientes....___");

        Volverr.setBackground(new java.awt.Color(255, 51, 51));
        Volverr.setText("Volver");
        Volverr.setBackgroundHover(new java.awt.Color(0, 204, 204));
        Volverr.setFont(new java.awt.Font("Dubai Medium", 1, 14)); // NOI18N
        Volverr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VolverrActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Volverr, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Volverr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        rSTableMetroCustom1.setModel(new javax.swing.table.DefaultTableModel(
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
        rSTableMetroCustom1.setBackgoundHead(new java.awt.Color(102, 204, 255));
        rSTableMetroCustom1.setBackgoundHover(new java.awt.Color(153, 255, 255));
        rSTableMetroCustom1.setColorSecondary(new java.awt.Color(255, 255, 255));
        rSTableMetroCustom1.setFont(new java.awt.Font("Dubai Medium", 1, 11)); // NOI18N
        rSTableMetroCustom1.setFontHead(new java.awt.Font("Dubai Medium", 1, 12)); // NOI18N
        rSTableMetroCustom1.setFontRowHover(new java.awt.Font("Dubai Medium", 1, 11)); // NOI18N
        rSTableMetroCustom1.setFontRowSelect(new java.awt.Font("Dubai Medium", 1, 11)); // NOI18N
        jScrollPane1.setViewportView(rSTableMetroCustom1);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        rSButtonMaterialTwo1.setBackground(new java.awt.Color(255, 51, 51));
        rSButtonMaterialTwo1.setText("Eliminar");
        rSButtonMaterialTwo1.setBackgroundHover(new java.awt.Color(0, 204, 204));
        rSButtonMaterialTwo1.setFont(new java.awt.Font("Dubai Medium", 1, 14)); // NOI18N
        rSButtonMaterialTwo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMaterialTwo1ActionPerformed(evt);
            }
        });

        rSButtonMaterialTwo2.setBackground(new java.awt.Color(51, 153, 255));
        rSButtonMaterialTwo2.setText("Editar");
        rSButtonMaterialTwo2.setBackgroundHover(new java.awt.Color(0, 204, 204));
        rSButtonMaterialTwo2.setFont(new java.awt.Font("Dubai Medium", 1, 14)); // NOI18N
        rSButtonMaterialTwo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMaterialTwo2ActionPerformed(evt);
            }
        });

        rSButtonMaterialTwo3.setBackground(new java.awt.Color(51, 153, 255));
        rSButtonMaterialTwo3.setText("Ver muro  del Cliente");
        rSButtonMaterialTwo3.setBackgroundHover(new java.awt.Color(0, 204, 204));
        rSButtonMaterialTwo3.setFont(new java.awt.Font("Dubai Medium", 1, 14)); // NOI18N
        rSButtonMaterialTwo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMaterialTwo3ActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/customer-review255px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSButtonMaterialTwo1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSButtonMaterialTwo3, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonMaterialTwo2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rSButtonMaterialTwo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rSButtonMaterialTwo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSButtonMaterialTwo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonMaterialTwo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMaterialTwo1ActionPerformed
        eliminarProducto();
    }//GEN-LAST:event_rSButtonMaterialTwo1ActionPerformed

    private void rSButtonMaterialTwo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMaterialTwo3ActionPerformed
        goToTheMuro();
    }//GEN-LAST:event_rSButtonMaterialTwo3ActionPerformed

    private void rSButtonMaterialTwo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMaterialTwo2ActionPerformed
        editarCliente();
    }//GEN-LAST:event_rSButtonMaterialTwo2ActionPerformed

    private void jPanel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel5MouseEntered

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked

    }//GEN-LAST:event_jPanel5MouseClicked

    private void VolverrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VolverrActionPerformed
        cerrar(true);
    }//GEN-LAST:event_VolverrActionPerformed

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
            java.util.logging.Logger.getLogger(ClientesMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientesMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientesMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientesMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientesMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonMaterialTwo Volverr;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private RSMaterialComponent.RSButtonMaterialTwo rSButtonMaterialTwo1;
    private RSMaterialComponent.RSButtonMaterialTwo rSButtonMaterialTwo2;
    private RSMaterialComponent.RSButtonMaterialTwo rSButtonMaterialTwo3;
    private RSMaterialComponent.RSTableMetroCustom rSTableMetroCustom1;
    // End of variables declaration//GEN-END:variables
}
