package jl.software.lojavirtual.loja_virtual_mentoria.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jl.software.lojavirtual.loja_virtual_mentoria.model.Pessoa_Juridica;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa_Juridica, Long>{

}
