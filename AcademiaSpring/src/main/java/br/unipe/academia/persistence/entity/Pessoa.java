package br.unipe.academia.persistence.entity;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import br.unipe.academia.daos.AbstractDao;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name="id_pessoa",sequenceName="pessoa_seq",allocationSize=1)
public class Pessoa extends AbstractEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="id_pessoa")
	private Long id;
	
	@NotEmpty(message="O nome não pode ser vazio")
	@Size(min=4, message="O nome deve ter no minimo 4 caracteres")
	private String nome;
	
	private String telFixo;
	private String telCelular;
	
	@Email(message="Email invalido")
	private String email;
	
	@CPF(message="CPF incorreto")
	private String cpf;
	
	@Temporal(TemporalType.DATE)
	private Date dataNasc;
	
	@Embedded
	private Endereco endereco;
	
	@OneToOne(mappedBy="pessoa")
	private Usuario usuario;
	
	@Transient
	private String dataTemporaria;
	
	public Pessoa(){
		
	}

	public Pessoa(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelFixo() {
		return telFixo;
	}

	public void setTelFixo(String telFixo) {
		this.telFixo = telFixo;
	}

	public String getTelCelular() {
		return telCelular;
	}

	public void setTelCelular(String telCelular) {
		this.telCelular = telCelular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataTemporaria() {
		return dataTemporaria;
	}

	public void setDataTemporaria(String dataTemporaria) {
		this.dataTemporaria = dataTemporaria;
	}

}
