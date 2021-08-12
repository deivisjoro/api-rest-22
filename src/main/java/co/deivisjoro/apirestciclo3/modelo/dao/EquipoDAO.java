package co.deivisjoro.apirestciclo3.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import co.deivisjoro.apirestciclo3.bd.Conexion;
import co.deivisjoro.apirestciclo3.modelo.entidad.Equipo;

public class EquipoDAO {

    private Connection connection;
    
    public EquipoDAO(Conexion conexion){  
        this.connection = conexion.getConexion();    
        if(this.connection==null){
        	this.connection = (Connection) new Conexion(); 
        }
    }
        
    public ArrayList<Equipo> getEquipos(){
        ArrayList<Equipo> equipos = new ArrayList<>();            
        try {
            String sql = "SELECT * FROM equipos";
            PreparedStatement pst;
            pst = this.connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Equipo equipo = new Equipo();
                equipo.setId(rs.getInt("id"));
                equipo.setNombre(rs.getString("nombre"));

                equipos.add(equipo);

            }
        } catch (SQLException ex) {
        	System.out.println("Error: "+ex.getMessage());
        }        
        return equipos;    
    }
    
    public Equipo getEquipo(int id){        
        for(Equipo equipo: this.getEquipos()){
            if(equipo.getId()==id){
                return equipo;
            }
        }        
        return null;
    }
    
    public Equipo addEquipo(Equipo equipo){
        try{
            String sql = "INSERT INTO equipos (nombre) VALUES (?)";        
            PreparedStatement pst = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, equipo.getNombre());
            int result = pst.executeUpdate();        
            ResultSet rs = pst.getGeneratedKeys();
            if(result>0){            
                if (rs.next()) {
                    int id = rs.getInt(1);
                    equipo.setId(id);
                }
            }        

            
        }
        catch(SQLException e){
        	System.out.println("Error: "+e.getMessage());
        }
        
        return equipo;
    }
    
    public boolean updateEquipo(Equipo equipo){
        try{
            String sql = "UPDATE equipos SET nombre=? WHERE id=?";        
            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setString(1, equipo.getNombre());
            pst.setInt(2, equipo.getId());
            int result = pst.executeUpdate();

            if(result>0){
                return true;
            }
        }
        catch(SQLException e){
        	System.out.println("Error: "+e.getMessage());        
        }        
        return false;
    }
    
    public boolean deleteEquipo(int id){
        try{
            String sql = "DELETE FROM equipos WHERE id=?";        
            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setInt(1, id);
            int result = pst.executeUpdate();
            if(result>0){
                return true;
            }
        }
        catch(SQLException e){
        	System.out.println("Error: "+e.getMessage());
        }
        
        return false;
    
    }
}