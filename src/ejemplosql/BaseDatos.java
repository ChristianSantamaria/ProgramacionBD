package ejemplosql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class BaseDatos {
    private Connection conex;

    
    public BaseDatos(String ruta) throws SQLException {
        this.conex = DriverManager.getConnection(ruta);
    }

    public void crearTabla() throws SQLException {
        Statement stmt = conex.createStatement();
        
        ResultSet rs = stmt.executeQuery("CREATE TABLE USUARIO "
                + "(Id INTEGER PRIMARY KEY,"
                + "Nombre STRING, "
                + "Apellido STRING, "
                + "Edad INTEGER)");
    }

    public void insertar(int Id, String Nombre, String Apellido, int Edad) throws SQLException {
        String Qry = "INSERT INTO 'USUARIO' ('Id','Nombre', 'Apellido', 'Edad') VALUES (?,?,?,?)";
        PreparedStatement rs = conex.prepareStatement(Qry);
        rs.setInt(1, Id);
        rs.setString(2, Nombre);
        rs.setString(3, Apellido);
        rs.setInt(4, Edad);
        rs.execute();
        JOptionPane.showMessageDialog(null, "El usuario de ha creado");
    }

    public ResultSet selertTodo() throws SQLException {
        Statement stmt = conex.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT Id FROM USUARIO");
        return rs;

    }

    public ResultSet selertNombre(String Id) throws SQLException {
        Statement stmt = conex.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT Id, Nombre, Apellido, Edad FROM USUARIO WHERE Id = '" + Id + "'");
        return rs;

    }

    public void modificar(int Id, String Nombre, String Apellido, int Edad) throws SQLException {
        String Qry = "UPDATE 'USUARIO' SET Nombre = ?, Apellido= ?, Edad = ? WHERE Id = ?";     
        PreparedStatement rs = conex.prepareStatement(Qry);
        rs.setString(1, Nombre);
        rs.setString(2, Apellido);
        rs.setInt(3, Edad);
        rs.setInt (4,Id);
        rs.execute();
        JOptionPane.showMessageDialog(null, "Se ha modificado al usuario");
    }
    
    public void eliminar(int Id) throws SQLException{
        String Qry = "DELETE FROM USUARIO WHERE Id= ? ";
        PreparedStatement rs = conex.prepareStatement(Qry);
        rs.setInt(1, Id);
        rs.execute();   
        JOptionPane.showMessageDialog(null, "Se ha eliminado al usuario correctamente");
    }


}
