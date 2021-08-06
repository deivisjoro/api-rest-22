package co.deivisjoro.apirestciclo3.recurso;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.deivisjoro.apirestciclo3.bd.Conexion;
import co.deivisjoro.apirestciclo3.modelo.dao.EquipoDAO;
import co.deivisjoro.apirestciclo3.modelo.entidad.Equipo;

@RestController
@RequestMapping("/equipos")
public class EquipoServicio {
	
	Conexion conexion = new Conexion();    
    EquipoDAO equipoDAO = new EquipoDAO(conexion);
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Equipo>> getEquipos(){
    	List<Equipo> equipos = equipoDAO.getEquipos();
		return ResponseEntity.ok(equipos);
	}

}
