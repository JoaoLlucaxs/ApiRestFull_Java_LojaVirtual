package jl.software.lojavirtual.loja_virtual_mentoria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jl.software.lojavirtual.loja_virtual_mentoria.ExceptionMentoriaJava;
import jl.software.lojavirtual.loja_virtual_mentoria.model.Pessoa_Juridica;
import jl.software.lojavirtual.loja_virtual_mentoria.repository.PessoaRepository;
import jl.software.lojavirtual.loja_virtual_mentoria.service.PessoaUserService;

@RestController
public class PessoaController {
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	@Autowired
	PessoaUserService pessoaUserService;
	
	@ResponseBody
	@PostMapping(value = "**/salvarPj")
	public ResponseEntity<Pessoa_Juridica> salvarPj(@RequestBody Pessoa_Juridica pessoajuridica) throws ExceptionMentoriaJava{
		
		if(pessoajuridica == null) {
			throw new ExceptionMentoriaJava("Pessoa juridica não pode ser NULL");
		}
		if(pessoajuridica.getId() == null && pessoaRepository.existeCnpjCadastrado(pessoajuridica.getCnpj()) != null) {
			throw new ExceptionMentoriaJava("Já existe cnpj cadastrado com número : " + pessoajuridica.getCnpj());
		}
		
		pessoajuridica=pessoaUserService.salvarPessoaJuridica(pessoajuridica);
		
		return new ResponseEntity<Pessoa_Juridica>(pessoajuridica, HttpStatus.OK);
	}
}
