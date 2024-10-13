package jl.software.lojavirtual.loja_virtual_mentoria.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jl.software.lojavirtual.loja_virtual_mentoria.service.ImplementacaoUserDetailsService;

import javax.servlet.http.HttpSessionListener;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class WebConfigSecurity extends WebSecurityConfigurerAdapter implements HttpSessionListener {

	@Autowired
	private ImplementacaoUserDetailsService implementacaoUserDetailService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		.disable().authorizeRequests().antMatchers("/").permitAll()
		.antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
		// Redireciona ou da um retorno para o index quando desloga
		.anyRequest().authenticated().and().logout().logoutSuccessUrl("/index")
		// Mapeia o logout do sistema
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		//Filtra as requisições para login de jwt
		.and().addFilterAfter(new JWTLoginFilter("/login", authenticationManager()), 
				UsernamePasswordAuthenticationFilter.class)
		.addFilterBefore(new JWTAutenticacaoFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	// Irá consultar o usuário no banco com Spring Security
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(implementacaoUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	// Ignora algumas urls livre de autenticação
    @Override
    public void configure(WebSecurity web) throws Exception {
        //web.ignoring().antMatchers(HttpMethod.GET, "/salvarAcesso","/deletarAcesso")
               // .antMatchers(HttpMethod.POST, "/salvarAcesso","/deletarAcesso"); // ignorando url para não autenticar no momento
    }
}
