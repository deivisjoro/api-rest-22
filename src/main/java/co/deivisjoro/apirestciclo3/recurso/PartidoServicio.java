package co.deivisjoro.apirestciclo3.recurso;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.deivisjoro.apirestciclo3.bd.Conexion;
import co.deivisjoro.apirestciclo3.modelo.dao.PartidoDAO;
import co.deivisjoro.apirestciclo3.modelo.entidad.Partido;

@RestController
@RequestMapping("/partidos")
public class PartidoServicio {
	
	Conexion conexion = new Conexion();    
    PartidoDAO partidoDAO = new PartidoDAO(conexion);
    
    @RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Partido>> getPartidos(){
    	List<Partido> partidos = partidoDAO.getPartidos();
		return ResponseEntity.ok(partidos);
	}
    
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Partido> getPartido(@PathVariable("id") int id){		
    	Partido partido = partidoDAO.getPartido(id);   
		return ResponseEntity.ok(partido);				
	}
    
    @RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Partido> setPartido(@RequestBody Partido partido){
		Partido newPartido = partidoDAO.addPartido(partido);
		return ResponseEntity.ok(newPartido);
	}
    
    @RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Partido> updatePartido(@RequestBody Partido partido){
    	Partido p = partidoDAO.updatePartido(partido);
		return ResponseEntity.ok(p);			
	}

}
