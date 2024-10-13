package jl.software.lojavirtual.loja_virtual_mentoria.model;

import jl.software.lojavirtual.loja_virtual_mentoria.enums.StatusContaPagar;
import jl.software.lojavirtual.loja_virtual_mentoria.enums.StatusContaReceber;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "conta_pagar")
@SequenceGenerator(name = "seq_conta_pagar",sequenceName = "seq_conta_pagar", initialValue = 1,allocationSize = 1)
public class ContaPagar implements Serializable {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_conta_pagar")
    private Long id;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private BigDecimal valorTotal;
    private BigDecimal valorDesconto;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusContaPagar statusContaPagar;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data_Vencimento;
    @Temporal(TemporalType.DATE)
    private Date data_Pagamento;

    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(name = "pessoa_id",nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fk"))
   private Pessoa pessoa;

    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(name = "pessoa_fornecedor_id",nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fornecedor_fk"))
    private Pessoa pessoa_fornecedor;
    
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData_Vencimento() {
        return data_Vencimento;
    }

    public void setData_Vencimento(Date data_Vencimento) {
        this.data_Vencimento = data_Vencimento;
    }

    public Date getData_Pagamento() {
        return data_Pagamento;
    }

    public void setData_Pagamento(Date data_Pagamento) {
        this.data_Pagamento = data_Pagamento;
    }


    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public StatusContaPagar getStatusContaPagar() {
        return statusContaPagar;
    }

    public void setStatusContaPagar(StatusContaPagar statusContaPagar) {
        this.statusContaPagar = statusContaPagar;
    }

    public Pessoa getPessoa_fornecedor() {
        return pessoa_fornecedor;
    }

    public void setPessoa_fornecedor(Pessoa pessoa_fornecedor) {
        this.pessoa_fornecedor = pessoa_fornecedor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContaPagar that = (ContaPagar) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
