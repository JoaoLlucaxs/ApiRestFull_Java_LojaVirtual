package jl.software.lojavirtual.loja_virtual_mentoria.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import jl.software.lojavirtual.loja_virtual_mentoria.ApplicationContextLoad;
import jl.software.lojavirtual.loja_virtual_mentoria.model.Usuario;
import jl.software.lojavirtual.loja_virtual_mentoria.repository.UsuarioRepository;

// Criar autenticação e retornar autenticação JWT
@Component
@Service
public class JWTTokenAutenticacaoService {

	
	private static final long EXPIRATION_TIME=959990000;// Token validade 11 dias
	
	private static final String SECRET="ss/-*-*sds565dsd-s/d-s*dsds";// Chave de senha para juntar com JWT
	
	private static final String TOKEN_PREFIX="Bearer";
	
	private static final String HEADER_TOKEN="Authorization";
	
	
	public void addAuthentication(HttpServletResponse response, String username) throws Exception{// Gerar o token e dar resposta para o cliente
			//montagem do token
		
		String JWT=Jwts.builder() // chama o gerador de token
				.setSubject(username)// adiciona o user
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact(); // Tempo de expiração
		
		String token=TOKEN_PREFIX + " " + JWT;
		
		response.addHeader(HEADER_TOKEN, token);// Da resposta para a tela e para o cliente ... outra api , navegador , aplicativo , javascript ou até outra chamada Java
		
		liberacaoDeCors(response);// Evitando problema de CORS no navegador
		
		response.getWriter().write("{\"Authorization\":\"" + token + "\"}");// Usado para ver no Postman para teste
	}
	
	
	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {/*Retorna o usuário validado com o token, caso o token não seja válido retorna null*/
		
		String token=request.getHeader(HEADER_TOKEN);
		try {
			
		
		if(token != null) {
			
			String tokenLimpo=token.replace(TOKEN_PREFIX, "").trim();
			// Faz validação do token do usuário e obtem o USER
			String user=Jwts.parser()
					.setSigningKey(SECRET).parseClaimsJws(tokenLimpo)
					.getBody().getSubject(); // isso é para pegar o usuário exemplo ADMIN ou João
			
			if(user != null) {
				Usuario usuario=ApplicationContextLoad.getApplicationContext()
						.getBean(UsuarioRepository.class)
						.findUserLogin(user);
				
				if(usuario != null) {
					return new UsernamePasswordAuthenticationToken
							(
									usuario.getLogin(),
									usuario.getSenha(), 
									usuario.getAuthorities()
									);
						}
					}
				}
			} catch (SignatureException e) {
				response.getWriter().write("Token inválido");
			}catch (ExpiredJwtException e) {
				response.getWriter().write("Token está expirado, efetue o login novamente");
			}
		
			finally {
				liberacaoDeCors(response);
			}	
	
		return null;
	}
	
	
	private void liberacaoDeCors(HttpServletResponse response) {// Fazendo a liberação de cors para não causar erro no navegador
		if(response.getHeader("Access-Control-Allow-Origin") == null) {
			response.addHeader("Access-Control-Allow-Origin", "*");
		}
		
		if(response.getHeader("Access-Control-Allow-Headers") == null) {
			response.addHeader("Access-Control-Allow-Headers", "*");
		}
		
		if(response.getHeader("Access-Control-Request-Headers") == null) {
			response.addHeader("Access-Control-Request-Headers", "*");
		}
		
		if(response.getHeader("Access-Control-Allow-Methods") == null) {
			response.addHeader("Access-Control-Allow-Methods", "*");
		}
	}
}
