package jl.software.lojavirtual.loja_virtual_mentoria.controller;

import jl.software.lojavirtual.loja_virtual_mentoria.ExceptionMentoriaJava;
import jl.software.lojavirtual.loja_virtual_mentoria.model.Acesso;
import jl.software.lojavirtual.loja_virtual_mentoria.repository.AcessoRepository;
import jl.software.lojavirtual.loja_virtual_mentoria.service.AcessoService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class AcessoController {

    @Autowired
    private AcessoService acessoService;

    @Autowired
    private AcessoRepository acessoRepository;

    @ResponseBody // dar um retorno da api
    @PostMapping("**/salvarAcesso") // mapeando a url para receber um json
    public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso) throws ExceptionMentoriaJava{ // request recebe json e converte em obj
       
    	if(acesso.getId() == null) {// cadastrando um novo
    		List<Acesso>acessos=acessoRepository.buscarAcesso(acesso.getDescricao().toUpperCase()); // verificar se ja existe um acesso igual
    		if(!acessos.isEmpty()) {
    			throw new ExceptionMentoriaJava("Já existe acesso com esta descrição " + acesso.getDescricao());
    		}
    	}
    	Acesso acessoSalvo=acessoService.save(acesso);
        return new ResponseEntity<Acesso>(acessoSalvo, HttpStatus.OK);
    }

    @ResponseBody 
    @PostMapping("**/deletarAcesso") 
    public ResponseEntity<?> deletarAcesso(@RequestBody Acesso acesso){ 
     acessoRepository.deleteById(acesso.getId());
        return new ResponseEntity("Acesso removido",HttpStatus.OK);
    }
    
    @ResponseBody
    @DeleteMapping("**/deletarAcessoPorId/{id}") 
    public ResponseEntity<?> deletarAcessoPorId(@PathVariable("id") Long id){
     acessoRepository.deleteById(id);
        return new ResponseEntity("Acesso por id removido",HttpStatus.OK);
    }
    
    @ResponseBody
    @GetMapping("**/obterAcesso/{id}") 
    public ResponseEntity<Acesso> obterAcesso(@PathVariable("id") Long id) throws ExceptionMentoriaJava{ 
    	Acesso acesso=	acessoRepository.findById(id).orElse(null);
    	if(acesso == null) {
    		throw new ExceptionMentoriaJava("Não encontrou acesso com o código : " + id);
    	}
    	
        return new ResponseEntity<Acesso>(acesso,HttpStatus.OK);
    }
    
    @ResponseBody
    @GetMapping("**/buscarPorDesc/{desc}") 
    public ResponseEntity<List<Acesso>> obterAcesso(@PathVariable("desc") String desc){ 
    	List<Acesso> acesso=acessoRepository.buscarAcesso(desc.toUpperCase());
    	
        return new ResponseEntity<List<Acesso>>(acesso,HttpStatus.OK);
    }
}
