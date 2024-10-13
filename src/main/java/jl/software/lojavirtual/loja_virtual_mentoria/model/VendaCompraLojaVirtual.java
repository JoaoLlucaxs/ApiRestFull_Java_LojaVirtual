package jl.software.lojavirtual.loja_virtual_mentoria.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "vd_compra_loja_virtual")
@SequenceGenerator(name = "seq_vd_compra_loja_virtual",sequenceName = "seq_vd_compra_loja_virtual", initialValue = 1,allocationSize = 1)
public class VendaCompraLojaVirtual implements Serializable {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_vd_compra_loja_virtual")
    private Long id;

    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(name = "pessoa_id",nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fk"))
    private Pessoa pessoa;
    @ManyToOne
    @JoinColumn(name = "endereco_entrega_id",nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "endereco_entrega_fk"))
    private Endereco enderecoEntrega;
    @ManyToOne
    @JoinColumn(name = "endereco_cobranca_id",nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "endereco_cobranca_fk"))
    private Endereco enderecoCobranca;
    @Column(nullable = false)
    private BigDecimal valorTotal;
    private BigDecimal valorDesconto;

    @ManyToOne
    @JoinColumn(name = "forma_pagamento_id",nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "forma_pagamento_fk"))
    private FormaPagamento formaPagamento;
    @OneToOne
    @JoinColumn(name = "nota_fiscal_venda_id",nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "nota_fiscal_venda_fk"))
    private NotaFiscalVenda notaFiscalVenda;

    @ManyToOne
    @JoinColumn(name = "cupom_desconto_id", foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "cupom_desconto_fk"))
    private CupomDesconto cupomDesconto;

    @Column(nullable = false)
    private BigDecimal valorFrete;
    @Column(nullable = false)
    private Integer diaEntrega;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataVenda;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataEntrega;

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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(Endereco enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public Endereco getEnderecoCobranca() {
        return enderecoCobranca;
    }

    public void setEnderecoCobranca(Endereco enderecoCobranca) {
        this.enderecoCobranca = enderecoCobranca;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public NotaFiscalVenda getNotaFiscalVenda() {
        return notaFiscalVenda;
    }

    public void setNotaFiscalVenda(NotaFiscalVenda notaFiscalVenda) {
        this.notaFiscalVenda = notaFiscalVenda;
    }

    public CupomDesconto getCupomDesconto() {
        return cupomDesconto;
    }

    public void setCupomDesconto(CupomDesconto cupomDesconto) {
        this.cupomDesconto = cupomDesconto;
    }

    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(BigDecimal valorFrete) {
        this.valorFrete = valorFrete;
    }

    public Integer getDiaEntrega() {
        return diaEntrega;
    }

    public void setDiaEntrega(Integer diaEntrega) {
        this.diaEntrega = diaEntrega;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendaCompraLojaVirtual that = (VendaCompraLojaVirtual) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
