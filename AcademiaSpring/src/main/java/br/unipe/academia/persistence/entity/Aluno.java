package br.unipe.academia.persistence.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Aluno extends Pessoa{
	
	@OneToMany(mappedBy="aluno")
	private List<AlunoModalidade> alunosModalidade;
	
	@OneToMany(mappedBy="aluno")
	private List<Avaliacao>	avaliacoes;
	
	@OneToMany(mappedBy="aluno")
	private List<Ficha> fichas;
	
	public Aluno(Long id) {
		super(id);
	}

	public Aluno() {
		super();
	}

	public List<AlunoModalidade> getAlunosModalidade() {
		return alunosModalidade;
	}

	public void setAlunosModalidade(List<AlunoModalidade> alunosModalidade) {
		this.alunosModalidade = alunosModalidade;
	}

	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public List<Ficha> getFichas() {
		return fichas;
	}

	public void setFichas(List<Ficha> fichas) {
		this.fichas = fichas;
	}	
	
}
