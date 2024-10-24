package jl.software.lojavirtual.loja_virtual_mentoria.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jl.software.lojavirtual.loja_virtual_mentoria.model.Pessoa_Juridica;
import jl.software.lojavirtual.loja_virtual_mentoria.model.Usuario;
import jl.software.lojavirtual.loja_virtual_mentoria.repository.PessoaRepository;
import jl.software.lojavirtual.loja_virtual_mentoria.repository.UsuarioRepository;

@Service
public class PessoaUserService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public Pessoa_Juridica salvarPessoaJuridica(Pessoa_Juridica juridica) {
		juridica=pessoaRepository.save(juridica);
		
		Usuario usuarioPj=usuarioRepository.findUserByPessoa(juridica.getId(),juridica.getEmail());
		
		if(usuarioPj == null) {
			
			String constraint=usuarioRepository.consultaContraintAcesso();
			
			// begin pula todo framework e vai direto no banco de dados , sql puro
			if(constraint != null) {
				jdbcTemplate.execute("begin; alter table usuarios_acesso drop constraint " + constraint + "; commit;");
			}
			
			usuarioPj=new Usuario();
			usuarioPj.setDataAtualSenha(Calendar.getInstance().getTime());
			usuarioPj.setEmpresa(juridica);
			usuarioPj.setPessoa(juridica);
			usuarioPj.setLogin(juridica.getEmail());
			
			String senha= "" + Calendar.getInstance().getTimeInMillis();
			String senhaCript= new BCryptPasswordEncoder().encode(senha);
			
			usuarioPj.setSenha(senhaCript);
			usuarioPj= usuarioRepository.save(usuarioPj);
			
			usuarioRepository.insereAcessoUserPj(usuarioPj.getId());
		}
		return juridica;
	}
}
