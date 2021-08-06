package co.deivisjoro.apirestciclo3.recurso;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.deivisjoro.apirestciclo3.bd.Conexion;
import co.deivisjoro.apirestciclo3.modelo.dao.UsuarioDAO;
import co.deivisjoro.apirestciclo3.modelo.entidad.Usuario;
import co.deivisjoro.apirestciclo3.recurso.filtro.Autorizacion;

@RestController
@RequestMapping("/usuarios")
public class UsuarioServicio {
	
	Conexion conexion = new Conexion();    
    UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Usuario> setUsuario(@RequestBody Usuario usuario){
		Usuario newUsuario = usuarioDAO.addUsuario(usuario);	
		return ResponseEntity.ok(newUsuario);
	}
    
    @CrossOrigin(origins = "http://localhost:4200", methods = RequestMethod.POST)
    @RequestMapping(value = "login", method = RequestMethod.POST)
	public ResponseEntity<Usuario> checkLogin(@RequestBody Usuario usuario){
    	Usuario u = usuarioDAO.checkLogin(usuario);
		String hash = "";         
	    long tiempo = System.currentTimeMillis();
	     
	    if(u.getId()!=0){
	        hash = Jwts.builder()
	                    .signWith(SignatureAlgorithm.HS256, Autorizacion.KEY)
	                    .setSubject(u.getNombre())
	                    .setIssuedAt(new Date(tiempo))
	                    .setExpiration(new Date(tiempo + 900000))
	                    .claim("username", u.getUsername())
	                    .claim("correo", u.getCorreo())
	                    .compact();
	    }
        
	    u.setHash(hash);
         
         
        return ResponseEntity.ok(u);
    }

}
