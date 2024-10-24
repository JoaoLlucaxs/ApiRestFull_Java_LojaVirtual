package jl.software.lojavirtual.loja_virtual_mentoria;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import jl.software.lojavirtual.loja_virtual_mentoria.controller.PessoaController;
import jl.software.lojavirtual.loja_virtual_mentoria.model.Pessoa_Juridica;
import jl.software.lojavirtual.loja_virtual_mentoria.repository.PessoaRepository;
import jl.software.lojavirtual.loja_virtual_mentoria.service.PessoaUserService;
import junit.framework.TestCase;


@Profile("test")
@SpringBootTest(classes = LojaVirtualMentoriaApplication.class)
public class testPessoaUsuario extends TestCase{
	
	@Autowired
	private PessoaController pessoaController;
	
	
	@Test
	public void testCadPessoaFisica() throws ExceptionMentoriaJava {
		
		Pessoa_Juridica pessoa_Juridica=new Pessoa_Juridica();
		pessoa_Juridica.setNome("Claudio Onorato");
		pessoa_Juridica.setTelefone("21987766655");
		pessoa_Juridica.setCnpj("" + Calendar.getInstance().getTimeInMillis());
		pessoa_Juridica.setEmail("testesalvarpj@gmail.com");
		pessoa_Juridica.setInsc_Estadual("2324332534535");
		pessoa_Juridica.setNomeFantasia("hksoe22");
		pessoa_Juridica.setInsc_Municipal("9489797998434388");
		pessoa_Juridica.setRazaoSocial("jhek4342343");
		
		pessoaController.salvarPj(pessoa_Juridica);
		
		/*Pessoa_Fisica pessoa_fisica=new Pessoa_Fisica();
		
		pessoa_fisica.setNome("Claudio Jr");
		pessoa_fisica.setTelefone("21987766655");
		pessoa_fisica.setCpf("888.333.444.22");
		pessoa_fisica.setEmail("claudiojr02@gmail.com");
		pessoa_fisica.setEmpresa(pessoa_fisica);
		*/
	}
}
