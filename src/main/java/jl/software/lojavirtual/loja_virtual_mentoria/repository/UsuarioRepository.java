package jl.software.lojavirtual.loja_virtual_mentoria.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jl.software.lojavirtual.loja_virtual_mentoria.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
	
	@Query(value = "select u from Usuario u where u.login=?1 ")
	Usuario findUserLogin(String login);
}
