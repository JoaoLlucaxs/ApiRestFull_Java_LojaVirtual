package jl.software.lojavirtual.loja_virtual_mentoria.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "pessoa_fisica")
@PrimaryKeyJoinColumn(name = "id") // apontando para o id Pessoa
public class Pessoa_Fisica extends Pessoa implements Serializable {
    private static final long serialVersionUID=1L;

    @Column(nullable = false)
    private String cpf;
    @Temporal(TemporalType.DATE)
    private Date data_Nascimento;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getData_Nascimento() {
        return data_Nascimento;
    }

    public void setData_Nascimento(Date data_Nascimento) {
        this.data_Nascimento = data_Nascimento;
    }

}
