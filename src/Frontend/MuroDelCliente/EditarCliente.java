/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend.MuroDelCliente;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import InstanciaADMIN.InstanciaADMIN;

/**
 *
 * @author edisonjpp
 */
public class EditarCliente extends javax.swing.JPanel {

    /**
     * Creates new form EditarCliente
     */
    public EditarCliente(ArrayList clienteData) {
        this.id = (int) Integer.parseInt((String) String.valueOf(clienteData.get(0)));
        initComponents();
        setDatosCliente(clienteData);
    }
    private int id;

    private void setDatosCliente(ArrayList data) {
        rSTextFieldTwo1.setText(String.valueOf(data.get(1)));
        rSTextFieldTwo3.setText(String.valueOf(data.get(2)));
        rSTextFieldTwo2.setText(String.valueOf(data.get(3)));
        
    }

    private void editarCliente() {
        String nombre = rSTextFieldTwo1.getText();
        String email = rSTextFieldTwo2.getText();
        String telefono = rSTextFieldTwo3.getText();

        String[] arr = {nombre, email, telefono};
        boolean validate = true;
        for (String value : arr) {
            if (value.length() < 1) {
                alerta("Faltan campos por llenar");
                validate = false;
            }
        }

        if (validate) {
            String query = "UPDATE cliente SET nombre = '" + nombre + "' ,email = '" + email + "', telefono = '" + telefono + "' WHERE id=" + id;
            if (InstanciaADMIN.Bot.bootAgregarEliminarActualizar(query)) {
                InstanciaADMIN.MuroDelCliente.jLabel2.setText(nombre);
                InstanciaADMIN.MuroDelCliente.dataCliente.set(1, nombre);
                alerta("Editado correctamente!");
                cerrar();
            } else {
                alerta("no se edito correctamente");
            }
        } else {
            alerta("Faltan campos por llenar");
        }
    }

    private void alerta(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private void cerrar() {
        InstanciaADMIN.EditarClienteDialog.cerrar();
    }

//    private void cerrar2() {
//        InstanciaADMIN.MuroDelCliente = new MuroDelCliente(this.id);
//    }
    /**
     * ^
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        rSTextFieldTwo1 = new RSMaterialComponent.RSTextFieldTwo();
        rSTextFieldTwo2 = new RSMaterialComponent.RSTextFieldTwo();
        rSTextFieldTwo3 = new RSMaterialComponent.RSTextFieldTwo();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        rSButtonMaterialTwo1 = new RSMaterialComponent.RSButtonMaterialTwo();
        rSButtonMaterialTwo2 = new RSMaterialComponent.RSButtonMaterialTwo();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/documento.png"))); // NOI18N
        jLabel5.setText("Edison, puedes editar lo que deseas! ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel5)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        rSTextFieldTwo1.setForeground(new java.awt.Color(51, 153, 255));
        rSTextFieldTwo1.setBorderColor(new java.awt.Color(51, 153, 255));
        rSTextFieldTwo1.setPhColor(new java.awt.Color(51, 153, 255));
        rSTextFieldTwo1.setPlaceholder("Nombre...");
        rSTextFieldTwo1.setSelectionColor(new java.awt.Color(51, 153, 255));

        rSTextFieldTwo2.setForeground(new java.awt.Color(51, 153, 255));
        rSTextFieldTwo2.setBorderColor(new java.awt.Color(51, 153, 255));
        rSTextFieldTwo2.setPhColor(new java.awt.Color(51, 153, 255));
        rSTextFieldTwo2.setPlaceholder("Correo elcetronico...");
        rSTextFieldTwo2.setSelectionColor(new java.awt.Color(51, 153, 255));

        rSTextFieldTwo3.setForeground(new java.awt.Color(51, 153, 255));
        rSTextFieldTwo3.setBorderColor(new java.awt.Color(51, 153, 255));
        rSTextFieldTwo3.setPhColor(new java.awt.Color(51, 153, 255));
        rSTextFieldTwo3.setPlaceholder("Telefono...");
        rSTextFieldTwo3.setSelectionColor(new java.awt.Color(51, 153, 255));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel1.setText("Nombre:");

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel2.setText("Email: ");

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel3.setText("Telefono:");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/rick.gif"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSTextFieldTwo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(rSTextFieldTwo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(rSTextFieldTwo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(23, 23, 23))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSTextFieldTwo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSTextFieldTwo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel3)
                        .addGap(8, 8, 8)
                        .addComponent(rSTextFieldTwo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        rSButtonMaterialTwo1.setBackground(new java.awt.Color(255, 51, 51));
        rSButtonMaterialTwo1.setText("Cancelar");
        rSButtonMaterialTwo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMaterialTwo1ActionPerformed(evt);
            }
        });

        rSButtonMaterialTwo2.setBackground(new java.awt.Color(51, 153, 255));
        rSButtonMaterialTwo2.setText("Editar");
        rSButtonMaterialTwo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMaterialTwo2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(rSButtonMaterialTwo1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSButtonMaterialTwo2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rSButtonMaterialTwo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonMaterialTwo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonMaterialTwo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMaterialTwo2ActionPerformed
        editarCliente();
    }//GEN-LAST:event_rSButtonMaterialTwo2ActionPerformed

    private void rSButtonMaterialTwo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMaterialTwo1ActionPerformed
        cerrar();
    }//GEN-LAST:event_rSButtonMaterialTwo1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private RSMaterialComponent.RSButtonMaterialTwo rSButtonMaterialTwo1;
    private RSMaterialComponent.RSButtonMaterialTwo rSButtonMaterialTwo2;
    private RSMaterialComponent.RSTextFieldTwo rSTextFieldTwo1;
    private RSMaterialComponent.RSTextFieldTwo rSTextFieldTwo2;
    private RSMaterialComponent.RSTextFieldTwo rSTextFieldTwo3;
    // End of variables declaration//GEN-END:variables
}