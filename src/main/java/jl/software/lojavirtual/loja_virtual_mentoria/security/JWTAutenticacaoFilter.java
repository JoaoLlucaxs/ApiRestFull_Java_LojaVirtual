package jl.software.lojavirtual.loja_virtual_mentoria.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

// Filtro onde todas as requisições serão capturadas para autenticar
public class JWTAutenticacaoFilter extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		try {
		// Estabelece a autenticação do user
			Authentication authentication=new JWTTokenAutenticacaoService()
				.getAuthentication((HttpServletRequest)request,(HttpServletResponse) response);
		
		// Coloca o processo de autenticação para o spring security
			SecurityContextHolder.getContext().setAuthentication(authentication);
		
			chain.doFilter(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("Ocorreu um erro no sistema, avise ao administrador \n" + e.getMessage());
		}
	}

}
