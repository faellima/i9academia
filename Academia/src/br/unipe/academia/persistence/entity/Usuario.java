package br.unipe.academia.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@SequenceGenerator(name="id_usuario",sequenceName="usuario_seq", allocationSize=1)
public class Usuario extends AbstractEntity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="id_usuario")
	private Long id;
	
	@NotEmpty(message="O Login não pode ser vazio")
	private String login;
	
	@NotEmpty(message="A senha não pode ser vazia")
	private String senha;
	
	@OneToOne
	private Pessoa pessoa;
	
	public Usuario() {
		super();
	}

	public Usuario(long id) {
		super();
		this.id = id;
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

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}
