package Backend.BotConsultas;

import Backend.DTBconeccion;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import InstanciaADMIN.InstanciaADMIN;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;
import javax.lang.model.util.Types;

public class BootConsultas {
    
    static ArrayList<String> dataEmpresa = new ArrayList<String>();
    static ArrayList<String> data = new ArrayList<String>();
    static ArrayList<Object[]> dataProducto = new ArrayList<Object[]>();
    static ArrayList<String> dataCliente = new ArrayList<String>();
    static ArrayList<Object[]> dataClienteOrClientes = new ArrayList<Object[]>();
    static int cantidadDeProducto;
    
    static PreparedStatement ps;
    static ResultSet rs;
    static Connection con;
    static DTBconeccion coneccion = InstanciaADMIN.coneccion;
    
    public static boolean bootAgregarEliminarActualizar(String query) {
        
        try {
            
            ps = null;
            
            ps = (PreparedStatement) coneccion.getConeccion().prepareStatement(query);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    
    public static boolean validarUsuario(String query) {
        try {
            ps = (PreparedStatement) coneccion.getConeccion().prepareStatement(query);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                String[] dataCruda = {String.valueOf(rs.getString(1)), String.valueOf(rs.getString(2)), String.valueOf(rs.getString(3)), String.valueOf(rs.getString(4))};
                data.add(dataCruda[0]);
                data.add(dataCruda[1]);
                data.add(dataCruda[2]);
                data.add(dataCruda[3]);
            }
            
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    
    public static boolean getClienteAndClientes(String query) {
        try {
            ps = (PreparedStatement) coneccion.getConeccion().prepareStatement(query);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                String[] dataCruda = {String.valueOf(rs.getString("id")), String.valueOf(rs.getString("nombre")), String.valueOf(rs.getString("telefono")), String.valueOf(rs.getString("email"))};
                
                dataCliente.add(dataCruda[0]);
                dataCliente.add(dataCruda[1]);
                dataCliente.add(dataCruda[2]);
                dataCliente.add(dataCruda[3]);
                
                String nombreProducto = rs.getString("nombre_producto");
                String validoHasta = rs.getString("validoHasta");
                
                int costoInicial = rs.getInt("costoInicial");
                int costoFinal = rs.getInt("costoFinal");
                int clienteId = rs.getInt("clienteId");
                int interesPorSemana = rs.getInt("interesPorSemana");
                int id = rs.getInt("productoId");
                
                Object[] dataCrudaProducto;
                
                dataCrudaProducto = (Object[]) new Object[]{id, nombreProducto, validoHasta, costoInicial, costoFinal, interesPorSemana, clienteId};
                dataProducto.add(dataCrudaProducto);
            }
            
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        
    }
    
    public static boolean getClientes(String query) {
        try {
            ps = (PreparedStatement) coneccion.getConeccion().prepareStatement(query);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                String[] dataCruda = {String.valueOf(rs.getInt("id")), String.valueOf(rs.getString("nombre")),
                    String.valueOf(rs.getString("telefono")), String.valueOf(rs.getString("email")),
                    String.valueOf(rs.getInt("cantidadProdutos"))
                };
             
                dataClienteOrClientes.add(dataCruda);
            }
            
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    
    public static boolean getProdcutoProductos(String query) {
        try {
            ps = (PreparedStatement) coneccion.getConeccion().prepareStatement(query);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                
                String nombreProducto = rs.getString("nombre_producto");
                String validoHasta = rs.getString("validoHasta");
                
                int costoInicial = rs.getInt("costoInicial");
                int costoFinal = rs.getInt("costoFinal");
                int clienteId = rs.getInt("clienteId");
                int interesPorSemana = rs.getInt("interesPorSemana");
                int id = rs.getInt("productoId");
                
                Object[] dataCrudaProducto;
                
                dataCrudaProducto = (Object[]) new Object[]{id, nombreProducto, validoHasta, interesPorSemana, costoInicial, costoFinal, clienteId};
                dataProducto.add(dataCrudaProducto);
                System.out.println(nombreProducto);
                
            }
            
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        
    }
    
    public boolean getDatosDeLaEmpresa(String query) {
        try {
            ps = (PreparedStatement) coneccion.getConeccion().prepareStatement(query);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                dataEmpresa.add(String.valueOf(rs.getInt("id")));
                dataEmpresa.add(rs.getString("nombre_tienda"));
                dataEmpresa.add(String.valueOf(rs.getInt("presupuesto")));
                dataEmpresa.add(rs.getString("telefono"));
                dataEmpresa.add(rs.getString("direccion"));
            }
            
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    
    public boolean getCantidadDeProductos(String query) {
        try {
            ps = (PreparedStatement) coneccion.getConeccion().prepareStatement(query);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                cantidadDeProducto = rs.getInt(1);
            }
            
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    
//    public static void storedProc(query){
//        int ifcaret;
//        int ifcareas;
//        int xsbytes;
//        String errbuff;
//        Connection con = null;
//        CallableStatement cstmt;
//        ResultSet rs;
//       comentalo o borralo VcOoy
//        cstmt = (CallableStatement) con.prepareCall("CALL DeleteToClienteView ");                
//                                          // Create a CallableStatement object
//        cstmt.setString (InstanciaADMIN);                                
//                                          // Set input parameter (Db2 command) 
//                               lo haremos con el metodo de 
//     T aba buscando eI cargador que se me iba a apagar AYYAAYAYAYA OK LIMPIA EL CODE este?
//        cstmt.close();                                                           
//    }
    
    public ArrayList getData() {
        return this.data;
    }
    
    public ArrayList getDataCliente() {
        return this.dataCliente;
    }
    
    public ArrayList getDataProducto() {
        return this.dataProducto;
    }
    
    public ArrayList getDataClienteOrClientes() {
        return this.dataClienteOrClientes;
    }
    
    public ArrayList getDataEmpresa() {
        return this.dataEmpresa;
    }
    
    public int getCantidadProductos() {
        return this.cantidadDeProducto;
    }
    
}
