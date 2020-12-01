package Frontend.TablaProductos;

import Backend.BotConsultas.BootConsultas;
import static Frontend.TablaProductos.Productoss.dataProducto;
import InstanciaADMIN.InstanciaADMIN;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import RSMaterialComponent.*;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Huáscar
 */

public abstract class AbstraccionProductos implements MetodosP {

    private static void model(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    BootConsultas Bot = InstanciaADMIN.Bot;
      // ArrayList<String> dataCliente = new ArrayList<String>(); 
       
          
    /*    ============================
    METODOS PARA Productoss
    */
    public static void limpiarTabla(DefaultTableModel model) {
        
        try {
            int filas = model.getRowCount();
            for (int i = 0; i < filas; i++) {
                model.removeRow(0);
            }
        } catch (Exception e) {
//           ("Error al limpiar la tabla.");
        }
    }
    
    public static void mostrarProduct(DefaultTableModel model) {
          for (Object[] o  :  InstanciaADMIN.Productoss.dataProducto) {
           model.addRow(o);
       }         
    }
    
    public static void getDataProducto(DefaultTableModel model){
        String query = "SELECT * FROM producto ";
    
        if(InstanciaADMIN.Bot.getProdcutoProductos(query)){
            
            
            InstanciaADMIN.Productoss.dataProducto = InstanciaADMIN.Bot.getDataProducto();
            if(dataProducto.size() > 0){
               AbstraccionProductos.mostrarProduct(model);
               InstanciaADMIN.Productoss.dataProducto.clear();
            }else{
                System.out.println("no tienes productos "); 
            }
        }else{
            JOptionPane.showMessageDialog(null, "Fallo en la consulta");
       }
    }
    
    public static boolean eliminarProducto(RSTableMetroCustom tabe) {
        if (tabe.getSelectedRow() != -1) {
            int index = tabe.getSelectedRow();
            int id = (int) InstanciaADMIN.Productoss.model.getValueAt(index, 0);
            String query = "DELETE FROM producto where productoId =" + id;
            
            if (InstanciaADMIN.Bot.bootAgregarEliminarActualizar(query)) {
                int costoFinal = (int) Integer.valueOf((String) String.valueOf(InstanciaADMIN.Productoss.model.getValueAt(index, 4)));
                int costoFinalTipo = -costoFinal;
                InstanciaADMIN.Productoss.model.removeRow(index);
                InstanciaADMIN.Productoss.dataProducto.remove(index);
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
    
    public static void alerta(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje);
    }
    
    public static boolean updateProducto(RSTableMetroCustom tabe) {

        if (tabe.getSelectedRow() != -1) {
            int index = tabe.getSelectedRow();
            int id = (int) Integer.parseInt((String) String.valueOf((Object) InstanciaADMIN.Productoss.model.getValueAt(index, 0)));
            String nombre = (String) InstanciaADMIN.Productoss.model.getValueAt(index, 1);
            String entrega = (String) InstanciaADMIN.Productoss.model.getValueAt(index, 2);
            int costoInicial = (int) Integer.parseInt(String.valueOf((Object) InstanciaADMIN.Productoss.model.getValueAt(index, 3)));
            int costoFinal = (int) Integer.parseInt((String) String.valueOf((Object) InstanciaADMIN.Productoss.model.getValueAt(index, 4)));
            int interes = (int) Integer.parseInt((String) String.valueOf((Object) InstanciaADMIN.Productoss.model.getValueAt(index, 5)));

            String query = "UPDATE producto SET  nombre_producto = '" + nombre + "' , validoHasta= '" + entrega
                    + "' , costoInicial= " + costoInicial + ", costoFinal = " + costoFinal
                    + " , interesPorSemana= " + interes + " WHERE productoId = " + id;

            if (InstanciaADMIN.Bot.bootAgregarEliminarActualizar(query)) {

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    
/*  ============================
    METODOS PARA DialogAñadir
*/
    
    

    public static void agregarProducto(String[] fiels) {
        String query = " INSERT INTO producto (nombre_producto , validoHasta, interesPorSemana, costoInicial, costoFinal ) \n"
                + " VALUES (' "+ fiels[0] + "','" + fiels[1] + "' ," + Integer.parseInt(fiels[4]) + " ," + Integer.parseInt(fiels[2]) + "," + Integer.parseInt(fiels[3]) + " )";
//        ," + id + "
        if(InstanciaADMIN.Bot.bootAgregarEliminarActualizar(query)){
            JOptionPane.showMessageDialog(null, "Agregado Correctamente");
        }
    }
    
    
}
