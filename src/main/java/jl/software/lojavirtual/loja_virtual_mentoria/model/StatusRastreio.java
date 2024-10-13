package jl.software.lojavirtual.loja_virtual_mentoria.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "status_rastreio")
@SequenceGenerator(name = "seq_status_rastreio",sequenceName = "seq_status_rastreio", initialValue = 1,allocationSize = 1)
public class StatusRastreio implements Serializable {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_status_rastreio")
    private Long id;

    private String centroDistribuicao;
    private String cidade;
    private String estado;
    private String status;

    @ManyToOne
    @JoinColumn(name = "vd_compra_loja_virtual_id",nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "vd_compra_loja_virtual_fk"))
    private VendaCompraLojaVirtual vendaCompraLojaVirtual;
    
    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(name = "empresa_id",nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_fk"))
    private Pessoa empresa;
   
   
    public VendaCompraLojaVirtual getVendaCompraLojaVirtual() {
		return vendaCompraLojaVirtual;
	}

	public void setVendaCompraLojaVirtual(VendaCompraLojaVirtual vendaCompraLojaVirtual) {
		this.vendaCompraLojaVirtual = vendaCompraLojaVirtual;
	}

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

    public String getCentroDistribuicao() {
        return centroDistribuicao;
    }

    public void setCentroDistribuicao(String centroDistribuicao) {
        this.centroDistribuicao = centroDistribuicao;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusRastreio that = (StatusRastreio) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
