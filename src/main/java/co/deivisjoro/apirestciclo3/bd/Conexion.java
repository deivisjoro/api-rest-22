package co.deivisjoro.apirestciclo3.bd;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private Connection conexion;

    public Conexion() {
        this.conexion = null;
    }

    public Connection getConexion(){
      
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conexion = DriverManager.getConnection("jdbc:mysql://ba063f7c4753f9:75f18016@eu-cdbr-west-01.cleardb.com/heroku_854d9842dabc2a5?reconnect=true&useSSL=false");
        } catch (Exception ex) {
            System.out.println("Error: "+ex.getMessage());
            
        } 
       
        return conexion;
    }

}