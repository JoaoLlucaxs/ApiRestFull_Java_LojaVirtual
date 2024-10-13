package jl.software.lojavirtual.loja_virtual_mentoria.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "item_venda_loja")
@SequenceGenerator(name = "seq_item_venda_loja",sequenceName = "seq_item_venda_loja", initialValue = 1,allocationSize = 1)
public class ItemVendaLoja implements Serializable {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_item_venda_loja")
    private Long id;

    @ManyToOne(targetEntity = Produto.class)
    @JoinColumn(name = "produto_id",nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "produto_fk"))
    Produto produto;
    @ManyToOne(targetEntity =VendaCompraLojaVirtual.class)
    @JoinColumn(name = "vd_compra_loja_virtual_id",nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "vd_compra_loja_virtual_fk"))
    private VendaCompraLojaVirtual vendaCompraLojaVirtual;

    @Column(nullable = false)
    private Double quantidade;
    
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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public VendaCompraLojaVirtual getVendaCompraLojaVirtual() {
        return vendaCompraLojaVirtual;
    }

    public void setVendaCompraLojaVirtual(VendaCompraLojaVirtual vendaCompraLojaVirtual) {
        this.vendaCompraLojaVirtual = vendaCompraLojaVirtual;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemVendaLoja that = (ItemVendaLoja) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
