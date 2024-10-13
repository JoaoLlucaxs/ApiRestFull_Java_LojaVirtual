package jl.software.lojavirtual.loja_virtual_mentoria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jl.software.lojavirtual.loja_virtual_mentoria.repository.UsuarioRepository;

@Service
public class PessoaUserService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
}
