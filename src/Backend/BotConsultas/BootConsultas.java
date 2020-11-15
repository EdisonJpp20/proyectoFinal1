package Backend.BotConsultas;

import Backend.DTBconeccion;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import InstanciaADMIN.InstanciaADMIN;

public class BootConsultas {
//    public BootConsultas() {
//    }

    static ArrayList<String> data = new ArrayList<String>();
    static ArrayList<Object[]> dataProducto = new ArrayList<Object[]>();
    static ArrayList<String>  dataCliente = new ArrayList<String>();

    static PreparedStatement ps;
    static ResultSet rs;
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

    public  ArrayList getData() {
        return this.data;
    }
    
    public ArrayList getDataCliente(){
        return this.dataCliente;
    }

    public ArrayList getDataProducto() {
        return this.dataProducto;
    }

}
