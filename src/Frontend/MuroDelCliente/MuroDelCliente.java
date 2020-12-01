/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend.MuroDelCliente;

//import Backend.BotConsultas.BootConsultas;
import Backend.BotConsultas.BootConsultas;
import Frontend.ClientesView.ClientesMain;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import InstanciaADMIN.InstanciaADMIN;

/**
 *
 * @author edisonjpp
 */
public class MuroDelCliente extends javax.swing.JFrame implements ClienteMuroF {

    /**
     * Creates new form MuroDelCliente
     */
    public MuroDelCliente(int id) {
        this.id = id;
        getDataCliente(false);
        initComponents();
        setModel();
        mostrarDatos();
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
//        setUndecorated(true);
        this.setVisible(true);
    }

    public static int id;
    private String query;
    public ArrayList dataCliente;
    private ArrayList<Object[]> dataProducto;
    private DefaultTableModel model;
    private int deuda = 0, cantidadDeProductos = 0;

    BootConsultas Bot = InstanciaADMIN.Bot;

    public void getDataCliente(boolean actualizar) {

        if (actualizar) {
            this.query = "SELECT * FROM producto INNER JOIN cliente on producto.clienteId = cliente.id \n"
                    + "  WHERE producto.clienteId = " + this.id + "\n "
                    + "ORDER BY producto.productoId DESC  LIMIT 1";
        } else {
            this.query = " SELECT * FROM cliente INNER JOIN producto on producto.clienteId = cliente.id where cliente.id =" + this.id;
        }

        if (this.Bot.getClienteAndClientes(this.query)) {
            this.dataCliente = this.Bot.getDataCliente();
            this.dataProducto = this.Bot.getDataProducto();
        }
    }

    private void setModel() {

        Vector columnas = new Vector<String>();
        columnas.addElement("id");
        columnas.addElement("Nombre");
        columnas.addElement("Entrega");
        columnas.addElement("Costo incial");
        columnas.addElement("Costo final ");
        columnas.addElement("Interes");
        Vector filas = new Vector<Object>();

        model = new DefaultTableModel(filas, columnas);
        rSTableMetroCustom1.setModel(model);

    }

    public void mostrarDatos() {

        if (this.dataCliente.size() > 0) {
            jLabel2.setText(String.valueOf(this.dataCliente.get(1)));
            jLabel6.setText(String.valueOf(this.dataCliente.get(2)));
            jLabel7.setText(String.valueOf(this.dataCliente.get(3)));
            if (this.dataProducto.size() > 0) {
                this.cantidadDeProductos = 0;
                this.deuda = 0;
                for (Object[] o : this.dataProducto) {
                    updateLabels(1, (int) o[4]);
                    model.addRow(o);
                }
            }
        }
    }

    public void updateLabels(int cantidad, int deuda) {
        this.cantidadDeProductos += cantidad;
        this.deuda += deuda;
        this.jLabel4.setText("Productos empeñados: " + this.cantidadDeProductos);
        this.jLabel5.setText("Deuda: $" + this.deuda);
    }

    public boolean eliminarProducto() {
        if (rSTableMetroCustom1.getSelectedRow() != -1) {
            int index = rSTableMetroCustom1.getSelectedRow();
            int id = (int) model.getValueAt(index, 0);
            this.query = "DELETE FROM producto where productoId =" + id;
            if (Bot.bootAgregarEliminarActualizar(this.query)) {
                int costoFinal = (int) Integer.valueOf((String) String.valueOf(this.model.getValueAt(index, 4)));
                int costoFinalTipo = -costoFinal;
                updateLabels(-1, costoFinalTipo);
                model.removeRow(index);
                this.dataProducto.remove(index);
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

    public boolean updateProducto() {

        if (rSTableMetroCustom1.getSelectedRow() != -1) {
            int index = rSTableMetroCustom1.getSelectedRow();
            int id = (int) Integer.parseInt((String) String.valueOf((Object) this.model.getValueAt(index, 0)));
            String nombre = (String) this.model.getValueAt(index, 1);
            String entrega = (String) this.model.getValueAt(index, 2);
            int costoInicial = (int) Integer.parseInt(String.valueOf((Object) this.model.getValueAt(index, 3)));
            int costoFinal = (int) Integer.parseInt((String) String.valueOf((Object) this.model.getValueAt(index, 4)));
            int interes = (int) Integer.parseInt((String) String.valueOf((Object) this.model.getValueAt(index, 5)));

            this.query = "UPDATE producto SET  nombre_producto = '" + nombre + "' , validoHasta= '" + entrega
                    + "' , costoInicial= " + costoInicial + ", costoFinal = " + costoFinal
                    + " , interesPorSemana= " + interes + " WHERE productoId = " + id;

            if (Bot.bootAgregarEliminarActualizar(this.query)) {

                updateLabels(0, -this.deuda);
                int rows = model.getRowCount();
                for (int i = 0; i < rows; i++) {
                    updateLabels(0, Integer.parseInt((String) String.valueOf(this.model.getValueAt(i, 4))));
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void actualizarData() {
        limpiarTabla();
        getDataCliente(true);
        mostrarDatos();
    }

    private void alerta(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public void limpiarTabla() {
        try {
            int filas = model.getRowCount();
            for (int i = 0; i < filas; i++) {
                model.removeRow(0);
            }
        } catch (Exception e) {
            alerta("Error al limpiar la tabla.");
        }
    }

    private void pagarTodo() {
        int res = JOptionPane.showConfirmDialog(this, "quieres pagarlos todo?",
                "favor de confirmar!", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null);

        int rows = model.getRowCount();

        if (rows > 0) {

            if (res == 0) {
                /// presuspuesto de ejemplo
                int presupestoEmpresa = 0;
                for (int i = 0; i < rows; i++) {
                    presupestoEmpresa += Integer.parseInt((String) String.valueOf(this.model.getValueAt(i, 4)));
                }

                this.query = "UPDATE casa_de_empeno SET presupuesto = " + presupestoEmpresa + " WHERE id = 1";

                if (this.Bot.bootAgregarEliminarActualizar(this.query)) {

                    this.query = "DELETE FROM producto WHERE clienteId = " + this.id;
                    if (this.Bot.bootAgregarEliminarActualizar(this.query)) {
                        alerta("Se ha pagado correctamente!");
                        limpiarTabla();
                        this.dataProducto.clear();

                    } else {
                        alerta("No se pago correctamente!");
                    }

                } else {
                    alerta("Se ha pagado correctamente, pero no se puedo contar el presupuesto!");
                }
            }
        } else {
            alerta("No tienes productos para pagar!");
        }
    }

    void pagarUnSolo() {
        if (rSTableMetroCustom1.getSelectedRow() != -1) {
            int index = rSTableMetroCustom1.getSelectedRow();
            int costo = Integer.parseInt((String) String.valueOf(this.model.getValueAt(index, 4)));

            this.query = "UPDATE casa_de_empeno SET presupuesto = " + costo + " WHERE id = 1";
            if (Bot.bootAgregarEliminarActualizar(this.query)) {
                if (this.eliminarProducto()) {
                    alerta("Pagado correctamente");
                }
            }
        } else {
            alerta("selecciona un producto");
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
        jPanel2 = new javax.swing.JPanel();
        rSButtonMaterialTwo1 = new RSMaterialComponent.RSButtonMaterialTwo();
        rSButtonMaterialTwo2 = new RSMaterialComponent.RSButtonMaterialTwo();
        jScrollPane1 = new javax.swing.JScrollPane();
        rSTableMetroCustom1 = new RSMaterialComponent.RSTableMetroCustom();
        rSButtonMaterialShadow1 = new RSMaterialComponent.RSButtonMaterialShadow();
        rSButtonMaterialShadow2 = new RSMaterialComponent.RSButtonMaterialShadow();
        rSButtonMaterialShadow3 = new RSMaterialComponent.RSButtonMaterialShadow();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        rSButtonMaterialTwo3 = new RSMaterialComponent.RSButtonMaterialTwo();
        rSButtonMaterialTwo4 = new RSMaterialComponent.RSButtonMaterialTwo();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        rSButtonMaterialTwo1.setBackground(new java.awt.Color(51, 102, 255));
        rSButtonMaterialTwo1.setText("Empeñar producto");
        rSButtonMaterialTwo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMaterialTwo1ActionPerformed(evt);
            }
        });

        rSButtonMaterialTwo2.setBackground(new java.awt.Color(51, 102, 255));
        rSButtonMaterialTwo2.setText("Pagar producto");
        rSButtonMaterialTwo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMaterialTwo2ActionPerformed(evt);
            }
        });

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
        rSTableMetroCustom1.setBackgoundHead(new java.awt.Color(51, 102, 255));
        jScrollPane1.setViewportView(rSTableMetroCustom1);

        rSButtonMaterialShadow1.setBackground(new java.awt.Color(51, 102, 255));
        rSButtonMaterialShadow1.setText("Pagar todo");
        rSButtonMaterialShadow1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMaterialShadow1ActionPerformed(evt);
            }
        });

        rSButtonMaterialShadow2.setBackground(new java.awt.Color(51, 204, 255));
        rSButtonMaterialShadow2.setText("Editar producto");
        rSButtonMaterialShadow2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMaterialShadow2ActionPerformed(evt);
            }
        });

        rSButtonMaterialShadow3.setBackground(new java.awt.Color(255, 51, 51));
        rSButtonMaterialShadow3.setText("Eliminar prodcuto");
        rSButtonMaterialShadow3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMaterialShadow3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rSButtonMaterialTwo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rSButtonMaterialTwo2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(rSButtonMaterialShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rSButtonMaterialShadow2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rSButtonMaterialShadow3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 678, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rSButtonMaterialTwo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSButtonMaterialTwo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSButtonMaterialShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(rSButtonMaterialShadow2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSButtonMaterialShadow3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 204, 255));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/usuario (2).png"))); // NOI18N
        jLabel2.setText("Edison J. Padilla");

        rSButtonMaterialTwo3.setBackground(new java.awt.Color(51, 102, 255));
        rSButtonMaterialTwo3.setText("Editar cliente");
        rSButtonMaterialTwo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMaterialTwo3ActionPerformed(evt);
            }
        });

        rSButtonMaterialTwo4.setBackground(new java.awt.Color(51, 102, 255));
        rSButtonMaterialTwo4.setText("Eliminar cliente");

        jPanel4.setBackground(new java.awt.Color(255, 51, 51));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Volver atras");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(0, 204, 255));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/deuda124.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Cantidad De Productos: 0");

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Deuda Total :  0");

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Edison@gmail.com.do");

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("(809) 997-3338");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel3))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel7)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSButtonMaterialTwo4, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonMaterialTwo3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(rSButtonMaterialTwo3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSButtonMaterialTwo4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonMaterialShadow2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMaterialShadow2ActionPerformed
        if (updateProducto()) {
            alerta("actualizado correctamente ( la tabla es editable) ! ");
        } else {
            alerta("favor de seleccionar un producto (la tabla es editable)");
        }
    }//GEN-LAST:event_rSButtonMaterialShadow2ActionPerformed

    private void rSButtonMaterialShadow3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMaterialShadow3ActionPerformed
        if (eliminarProducto()) {
            alerta("Eliminando correctamente");
        } else {
            alerta("Favor de seleccionar un producto ");
        }
    }//GEN-LAST:event_rSButtonMaterialShadow3ActionPerformed

    private void rSButtonMaterialTwo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMaterialTwo1ActionPerformed
        InstanciaADMIN.DialogCrearProducto = new DialogCrearProducto();
    }//GEN-LAST:event_rSButtonMaterialTwo1ActionPerformed

    private void rSButtonMaterialShadow1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMaterialShadow1ActionPerformed
        pagarTodo();
    }//GEN-LAST:event_rSButtonMaterialShadow1ActionPerformed

    private void rSButtonMaterialTwo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMaterialTwo2ActionPerformed
        pagarUnSolo();
    }//GEN-LAST:event_rSButtonMaterialTwo2ActionPerformed

    private void rSButtonMaterialTwo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMaterialTwo3ActionPerformed
        InstanciaADMIN.EditarClienteDialog = new EditarClienteDialog(this.dataCliente);
    }//GEN-LAST:event_rSButtonMaterialTwo3ActionPerformed

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        this.dispose();
        InstanciaADMIN.ClientesMain = new ClientesMain();
    }//GEN-LAST:event_jPanel4MouseClicked

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(MuroDelCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(MuroDelCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(MuroDelCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MuroDelCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MuroDelCliente().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private RSMaterialComponent.RSButtonMaterialShadow rSButtonMaterialShadow1;
    private RSMaterialComponent.RSButtonMaterialShadow rSButtonMaterialShadow2;
    private RSMaterialComponent.RSButtonMaterialShadow rSButtonMaterialShadow3;
    private RSMaterialComponent.RSButtonMaterialTwo rSButtonMaterialTwo1;
    private RSMaterialComponent.RSButtonMaterialTwo rSButtonMaterialTwo2;
    private RSMaterialComponent.RSButtonMaterialTwo rSButtonMaterialTwo3;
    private RSMaterialComponent.RSButtonMaterialTwo rSButtonMaterialTwo4;
    private RSMaterialComponent.RSTableMetroCustom rSTableMetroCustom1;
    // End of variables declaration//GEN-END:variables
}
