package jl.software.lojavirtual.loja_virtual_mentoria.repository;

import jl.software.lojavirtual.loja_virtual_mentoria.model.Acesso;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional // irei gerenciar as transações com o banco
public interface AcessoRepository extends JpaRepository<Acesso,Long> {
	
	@Query("select a from Acesso a where upper(trim(a.descricao)) like %?1%")
	List<Acesso> buscarAcesso(String desc);
}
