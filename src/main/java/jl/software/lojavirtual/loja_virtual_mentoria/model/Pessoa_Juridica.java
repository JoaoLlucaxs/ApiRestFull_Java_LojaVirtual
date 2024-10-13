package jl.software.lojavirtual.loja_virtual_mentoria.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "pessoa_juridica")
@PrimaryKeyJoinColumn(name = "id") // apontando para o id Pessoa
public class Pessoa_Juridica extends Pessoa implements Serializable {
    private static final long serialVersionUID=1L;
    @Column(nullable = false)
    private String cnpj;
    @Column(name = "inscricao_estadual", nullable = false)
    private String insc_Estadual;
    @Column(name = "inscricao_municipal")
    private String insc_Municipal;
    @Column(nullable = false)
    private String nomeFantasia;
    @Column(nullable = false)
    private String razaoSocial;

    private String categoria;

    public String getInsc_Estadual() {
        return insc_Estadual;
    }

    public void setInsc_Estadual(String insc_Estadual) {
        this.insc_Estadual = insc_Estadual;
    }

    public String getInsc_Municipal() {
        return insc_Municipal;
    }

    public void setInsc_Municipal(String insc_Municipal) {
        this.insc_Municipal = insc_Municipal;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }


}
