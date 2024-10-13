package jl.software.lojavirtual.loja_virtual_mentoria.model;

import jl.software.lojavirtual.loja_virtual_mentoria.enums.TipoEndereco;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "endereco")
@SequenceGenerator(name = "seq_endereco",sequenceName = "seq_endereco", initialValue = 1,allocationSize = 1)
public class Endereco implements Serializable {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_endereco")
    private Long id;
    @Column(nullable = false)
    private String ruaLograd;
    @Column(nullable = false)
    private String cep;
    @Column(nullable = false)
    private String numero;
    private String complemento;
    @Column(nullable = false)
    private String bairro;
    @Column(nullable = false)
    private String uf;
    @Column(nullable = false)
    private String cidade;
    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(name = "pessoa_id",nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fk"))
   private Pessoa pessoa;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoEndereco tipoEndereco;
    
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

    public String getRuaLograd() {
        return ruaLograd;
    }

    public void setRuaLograd(String ruaLograd) {
        this.ruaLograd = ruaLograd;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public TipoEndereco getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(TipoEndereco tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(id, endereco.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
