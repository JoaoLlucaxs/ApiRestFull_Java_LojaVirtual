package jl.software.lojavirtual.loja_virtual_mentoria;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
<<<<<<< HEAD
import org.springframework.context.annotation.Profile;
=======
>>>>>>> 164872c1c4da4936cd98dd3ad5bae73f5f3e4fee

import jl.software.lojavirtual.loja_virtual_mentoria.controller.PessoaController;
import jl.software.lojavirtual.loja_virtual_mentoria.model.Pessoa_Fisica;
import jl.software.lojavirtual.loja_virtual_mentoria.model.Pessoa_Juridica;
import jl.software.lojavirtual.loja_virtual_mentoria.repository.PessoaRepository;
import jl.software.lojavirtual.loja_virtual_mentoria.service.PessoaUserService;
import junit.framework.TestCase;

<<<<<<< HEAD
@Profile("test")
@SpringBootTest(classes = LojaVirtualMentoriaApplication.class)
public class testPessoaUsuario extends TestCase{
	
	/*@Autowired
	private PessoaController pessoaController;*/
	
	@Autowired
	private PessoaUserService pessoaUserService;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Test
	public void testCadPessoaFisica() {
		
		Pessoa_Juridica pessoa_Juridica=new Pessoa_Juridica();
		pessoa_Juridica.setNome("Claudio Onorato");
		pessoa_Juridica.setTelefone("21987766655");
		pessoa_Juridica.setCnpj("9938372828038");
		pessoa_Juridica.setEmail("claudiojr02@gmail.com");
		pessoa_Juridica.setInsc_Estadual("2324332534535");
		pessoa_Juridica.setNomeFantasia("hksoe22");
		pessoa_Juridica.setInsc_Municipal("9489797998434388");
		pessoa_Juridica.setRazaoSocial("jhek4342343");
		
		pessoaRepository.save(pessoa_Juridica);
		
		/*Pessoa_Fisica pessoa_fisica=new Pessoa_Fisica();
		
		pessoa_fisica.setNome("Claudio Jr");
		pessoa_fisica.setTelefone("21987766655");
		pessoa_fisica.setCpf("888.333.444.22");
		pessoa_fisica.setEmail("claudiojr02@gmail.com");
		pessoa_fisica.setEmpresa(pessoa_fisica);
		*/
=======
@SpringBootTest(classes = LojaVirtualMentoriaApplication.class)
public class testPessoaUsuario extends TestCase{
	
	@Autowired
	private PessoaController pessoaController;
	
	@Test
	public void testCadPessoaFisica() {
		Pessoa_Juridica pessoa_juridica=new Pessoa_Juridica();
		pessoa_juridica.setCnpj("43443242432");
		pessoa_juridica.setNome("Queiroz System LTDA");
		pessoa_juridica.setEmail("systemltdaqueiroz@gmail.com");
		pessoa_juridica.setTelefone("21978765444");
		pessoa_juridica.setInsc_Estadual("554453343437");
		pessoa_juridica.setNomeFantasia("54665554545");
		pessoa_juridica.setRazaoSocial("665554545");
		
		pessoaController.salvarPj(pessoa_juridica);
		
		
>>>>>>> 164872c1c4da4936cd98dd3ad5bae73f5f3e4fee
	}
}
