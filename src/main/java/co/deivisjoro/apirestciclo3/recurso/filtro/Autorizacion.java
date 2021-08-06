package co.deivisjoro.apirestciclo3.recurso.filtro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.security.Key;
import io.jsonwebtoken.impl.crypto.MacProvider;

import org.springframework.stereotype.Component;

//@Component
public class Autorizacion implements Filter {
	
    public static final Key KEY = MacProvider.generateKey();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		
		
		String url = httpServletRequest.getRequestURI();
		System.out.println("url: "+url);
        if(url.contains("usuarios/login") || url.contains("usuarios")){
        	chain.doFilter(request, response);
        }
        else {
        	
        }
		System.out.println("pase por aqui");
		
	}

}
