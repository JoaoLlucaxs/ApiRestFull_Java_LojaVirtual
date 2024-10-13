package jl.software.lojavirtual.loja_virtual_mentoria.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "cupom_desconto")
@SequenceGenerator(name = "seq_cupom_desconto",sequenceName = "seq_cupom_desconto", initialValue = 1,allocationSize = 1)
public class CupomDesconto implements Serializable {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cupom_desconto")
    private Long id;
    @Column(name = "cod_descricao", nullable = false)
    private String cod_Desc;

    private BigDecimal valorRealDesc;
    private BigDecimal valorPorcentoDesc;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataValidadeCupom;
    
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

    public String getCod_Desc() {
        return cod_Desc;
    }

    public void setCod_Desc(String cod_Desc) {
        this.cod_Desc = cod_Desc;
    }

    public BigDecimal getValorRealDesc() {
        return valorRealDesc;
    }

    public void setValorRealDesc(BigDecimal valorRealDesc) {
        this.valorRealDesc = valorRealDesc;
    }

    public BigDecimal getValorPorcentoDesc() {
        return valorPorcentoDesc;
    }

    public void setValorPorcentoDesc(BigDecimal valorPorcentoDesc) {
        this.valorPorcentoDesc = valorPorcentoDesc;
    }

    public Date getDataValidadeCupom() {
        return dataValidadeCupom;
    }

    public void setDataValidadeCupom(Date dataValidadeCupom) {
        this.dataValidadeCupom = dataValidadeCupom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CupomDesconto that = (CupomDesconto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
