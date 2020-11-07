package Backend.BotConsultas;

import Backend.DTBconeccion;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BootConsultas {

    public BootConsultas(String query) {
        this.query = query;
    }

    String query;

    ArrayList<String> data = new ArrayList<String>();

    PreparedStatement ps;
    ResultSet rs;
    DTBconeccion connection = new DTBconeccion();

    public boolean agregarADMIN() {
        ps = null;
        try {
            ps = (PreparedStatement) connection.getConeccion().prepareStatement(this.query);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean validarUsuario() {
        try {
            ps = (PreparedStatement) connection.getConeccion().prepareStatement(this.query);
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
    
    
    public ArrayList getData(){
        return this.data; 
    }

}
