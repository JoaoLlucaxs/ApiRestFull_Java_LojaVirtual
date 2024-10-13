package jl.software.lojavirtual.loja_virtual_mentoria.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(name = "seq_usuario",sequenceName = "seq_usuario", initialValue = 1,allocationSize = 1)
public class Usuario implements UserDetails {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;
    
    @Column(nullable = false)
    private String senha;
   
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataAtualSenha;

    // Inserindo LAZY para um carregamento preguiçoso, só chamar quando precisar
    // Estou criando uma tabela intermediaria entre Usuario e Acesso onde irá se chamar "usuarios_acesso"
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_acesso", uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id","acesso_id"},
    name = "unique_acesso_user"),
    joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id",table = "usuario",
            unique = false, foreignKey = @ForeignKey(name = "usuario_fk", value = ConstraintMode.CONSTRAINT)),
    inverseJoinColumns = @JoinColumn(name = "acesso_id", unique = false, referencedColumnName = "id", table = "acesso",
    foreignKey = @ForeignKey(name = "acesso_fk",value = ConstraintMode.CONSTRAINT)))
    private List<Acesso>acessos;

    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(name = "pessoa_id",nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fk"))
    private Pessoa pessoa;
    
    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(name = "empresa_id",nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_fk"))
    private Pessoa empresa;

    /*
    Autoridade= São os acesso, ou seja ROLE_ADMIN, ROLE_SECRETARIO, ROLE_FINANCEIRO
    * */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.acessos;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Date getDataAtualSenha() {
        return dataAtualSenha;
    }

    public void setDataAtualSenha(Date dataAtualSenha) {
        this.dataAtualSenha = dataAtualSenha;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Acesso> getAcessos() {
		return acessos;
	}

	public void setAcessos(List<Acesso> acessos) {
		this.acessos = acessos;
	}

	public Pessoa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Pessoa empresa) {
		this.empresa = empresa;
	}

	@Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
