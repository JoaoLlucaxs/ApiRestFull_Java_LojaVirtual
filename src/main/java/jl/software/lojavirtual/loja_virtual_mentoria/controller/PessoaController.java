package jl.software.lojavirtual.loja_virtual_mentoria.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jl.software.lojavirtual.loja_virtual_mentoria.model.Pessoa_Juridica;

@RestController
public class PessoaController {
	
	@ResponseBody
	@PostMapping(value = "**/salvarPj")
	public ResponseEntity<Pessoa_Juridica> salvarPj(@RequestBody Pessoa_Juridica pessoajuridica){
		
		return new ResponseEntity<Pessoa_Juridica>(pessoajuridica, HttpStatus.OK);
	}
}
