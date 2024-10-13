package jl.software.lojavirtual.loja_virtual_mentoria.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

// Media de tamanho de uma imagem 500kb até menos 200kb isso é leve para um banco
@Entity
@Table(name = "imagem_produto")
@SequenceGenerator(name = "seq_imagem_produto",sequenceName = "seq_imagem_produto", initialValue = 1,allocationSize = 1)
public class Imagem_Produto implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_imagem_produto")
    private Long id;
    @Column(columnDefinition = "text",nullable = false)
    private String imagem_original;
    @Column(columnDefinition = "text",nullable = false)
    private String imagem_miniatura;
    @ManyToOne(targetEntity = Produto.class)
    @JoinColumn(name = "produto_id",nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "produto_fk"))
    private Produto produto;
    
    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(name = "empresa_id",nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_fk"))
    private Pessoa empresa;
   
    public Pessoa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Pessoa empresa) {
		this.empresa = empresa;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagem_original() {
        return imagem_original;
    }

    public void setImagem_original(String imagem_original) {
        this.imagem_original = imagem_original;
    }

    public String getImagem_miniatura() {
        return imagem_miniatura;
    }

    public void setImagem_miniatura(String imagem_miniatura) {
        this.imagem_miniatura = imagem_miniatura;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Imagem_Produto that = (Imagem_Produto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
