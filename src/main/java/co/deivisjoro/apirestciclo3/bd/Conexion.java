package co.deivisjoro.apirestciclo3.bd;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    public static Connection conexion;

    public Conexion() {
        conexion = null;
    }

    public Connection getConexion(){
      
        try {
        	if(conexion==null || conexion.isClosed()) {
        		Class.forName("com.mysql.cj.jdbc.Driver");
        		//conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/ciclo3unab", "root", "");
        		conexion = DriverManager.getConnection("jdbc:mysql://ba063f7c4753f9:75f18016@eu-cdbr-west-01.cleardb.com/heroku_854d9842dabc2a5?reconnect=true&useSSL=false");
        	}		
        } catch (Exception ex) {
            System.out.println("Error: "+ex.getMessage());
            
        } 
       
        return conexion;
    }

}