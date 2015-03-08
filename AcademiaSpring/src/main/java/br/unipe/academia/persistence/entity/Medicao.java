package br.unipe.academia.persistence.entity;


import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name="id_medicao",sequenceName="medicao_seq",allocationSize=1)
public class Medicao extends AbstractEntity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="id_medicao")
	private Long id;
	private String nome;
	
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@OneToMany(mappedBy="medicao")
	private List<MedidaAvaliacao> medidaavaliacoes;

	public Medicao() {
		super();
	}

	public Medicao(long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<MedidaAvaliacao> getMedidaavaliacoes() {
		return medidaavaliacoes;
	}

	public void setMedidaavaliacoes(List<MedidaAvaliacao> medidaavaliacoes) {
		this.medidaavaliacoes = medidaavaliacoes;
	}
	
}
