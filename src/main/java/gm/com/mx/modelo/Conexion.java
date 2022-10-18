
package gm.com.mx.modelo;

import java.sql.*;

public class Conexion {
    
    Connection conexion = null;
    
    public Conexion(){
        
    }
    
    public Connection getConexion(){
        try{
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
                    + "proyecto_restaurante", "root", "admin");
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return conexion;
    }
}
