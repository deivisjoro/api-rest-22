package co.deivisjoro.apirestciclo3.recurso.filtro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.security.Key;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.crypto.MacProvider;

import org.springframework.stereotype.Component;

@Component
public class Autorizacion implements Filter {
	
    public static final Key KEY = MacProvider.generateKey();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;		
		
		String url = httpServletRequest.getRequestURI();
        if(url.contains("usuarios/login") || url.contains("usuarios") || url.contains("index") || url.contains(".js") || url.contains(".css") || url.contains(".ico") || url.contains("assets") || url.contains("#")){
        	chain.doFilter(request, response);
        }
        else {
        	
        	String hash = httpServletRequest.getHeader("Authorization");
        	if(hash==null || hash.trim().equals("")){
        		response.setContentType("application/json");
        		String body = "{\"autorizacion\":\"NO\"}";
        		response.getWriter().write(body);
        	}
        	
        	try{
                Jws<Claims> claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(hash);
                chain.doFilter(request, response);
            }
            catch(MalformedJwtException e){
            	response.setContentType("application/json");
        		String body = "{\"autorizacion\":\"NO\"}";
        		response.getWriter().write(body);
            }
            catch(SignatureException e){
            	response.setContentType("application/json");
        		String body = "{\"autorizacion\":\"NO\"}";
        		response.getWriter().write(body);
            }
            catch(ExpiredJwtException e){
            	response.setContentType("application/json");
        		String body = "{\"autorizacion\":\"NO\"}";
        		response.getWriter().write(body);
            }
        	catch (Exception e) {
        		response.setContentType("application/json");
        		String body = "{\"autorizacion\":\"NO\"}";
        		response.getWriter().write(body);
			}
        }
        
		
	}

}
