package br.unipe.academia.persistence.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Size;

@Entity
@SequenceGenerator(name="id_modalidade", sequenceName="modalidade_seq", allocationSize=1)
public class Modalidade extends AbstractEntity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="id_modalidade")
	private Long id;
	
	private String nome;

	@OneToMany(mappedBy="modalidade")
	private List<AlunoModalidade> alunosModalidade;

	public Modalidade(Long id) {
		super();
		this.id = id;
	}

	public Modalidade() {
		super();
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

	public List<AlunoModalidade> getAlunosModalidade() {
		return alunosModalidade;
	}

	public void setAlunosModalidade(List<AlunoModalidade> alunosModalidade) {
		this.alunosModalidade = alunosModalidade;
	}

}
