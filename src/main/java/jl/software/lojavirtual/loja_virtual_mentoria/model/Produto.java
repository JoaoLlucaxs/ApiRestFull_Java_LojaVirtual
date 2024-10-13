package jl.software.lojavirtual.loja_virtual_mentoria.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "produto")
@SequenceGenerator(name = "seq_produto",sequenceName = "seq_produto", initialValue = 1,allocationSize = 1)
public class Produto implements Serializable {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_produto")
    private Long id;
    @Column(nullable = false)
    private String tipoUnidade;
    @Column(nullable = false)
    private String nome;
    @Column(columnDefinition = "text",length = 2000, nullable = false)
    private String descricao;

    // NotaItemProduto associar
    @Column(nullable = false)
    private Double peso;
    @Column(nullable = false)
    private Boolean ativo= Boolean.TRUE;
    @Column(nullable = false)
    private Double altura;
    @Column(nullable = false)
    private Double largura;
    @Column(nullable = false)
    private Double profundidade;
    @Column(nullable = false)
    private BigDecimal valorDeVenda=BigDecimal.ZERO;
    @Column(nullable = false)
    private Integer qtdEstoque=0;

    private Integer qtdAlertaEstoque=0;
    private String linkVideoYoutube;

    private Boolean alertaQtdEstoque=Boolean.FALSE; // ligado ou desligado  para alertar

    private Integer qtdDeClique=0;
    
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

    public String getTipoUnidade() {
        return tipoUnidade;
    }

    public void setTipoUnidade(String tipoUnidade) {
        this.tipoUnidade = tipoUnidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Double getLargura() {
        return largura;
    }

    public void setLargura(Double largura) {
        this.largura = largura;
    }

    public Double getProfundidade() {
        return profundidade;
    }

    public void setProfundidade(Double profundidade) {
        this.profundidade = profundidade;
    }

    public BigDecimal getValorDeVenda() {
        return valorDeVenda;
    }

    public void setValorDeVenda(BigDecimal valorDeVenda) {
        this.valorDeVenda = valorDeVenda;
    }

    public Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public Integer getQtdAlertaEstoque() {
        return qtdAlertaEstoque;
    }

    public void setQtdAlertaEstoque(Integer qtdAlertaEstoque) {
        this.qtdAlertaEstoque = qtdAlertaEstoque;
    }

    public String getLinkVideoYoutube() {
        return linkVideoYoutube;
    }

    public void setLinkVideoYoutube(String linkVideoYoutube) {
        this.linkVideoYoutube = linkVideoYoutube;
    }

    public Boolean getAlertaQtdEstoque() {
        return alertaQtdEstoque;
    }

    public void setAlertaQtdEstoque(Boolean alertaQtdEstoque) {
        this.alertaQtdEstoque = alertaQtdEstoque;
    }

    public Integer getQtdDeClique() {
        return qtdDeClique;
    }

    public void setQtdDeClique(Integer qtdDeClique) {
        this.qtdDeClique = qtdDeClique;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
