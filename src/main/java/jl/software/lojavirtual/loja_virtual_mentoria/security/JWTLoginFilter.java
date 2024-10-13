package jl.software.lojavirtual.loja_virtual_mentoria.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import jl.software.lojavirtual.loja_virtual_mentoria.model.Usuario;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter{

	public JWTLoginFilter(String url,AuthenticationManager authenticationManager) { // configurando o gerenciador de autenticação
		super(new AntPathRequestMatcher(url)); // Obriga a autenticar a url
		setAuthenticationManager(authenticationManager); // gerenciador de autenticação
	}
	
	
	// Retorna o usuário ao processar a autenticação

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
			
			// Obtem o usuário
			Usuario user=new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
			
			// Retorna o user com login e senha
			return getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(user.getLogin(), user.getSenha())
					);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
			try {
				new JWTTokenAutenticacaoService().addAuthentication(response, authResult.getName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		
		if(failed instanceof BadCredentialsException) {
			response.getWriter().write("User e senha não encontrado");
		}else {
			response.getWriter().write("Falha ao logar " + failed.getMessage());
		}
	}
}
