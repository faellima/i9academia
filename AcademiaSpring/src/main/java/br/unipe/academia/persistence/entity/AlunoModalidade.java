package br.unipe.academia.persistence.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name="id_alunomodalidade", sequenceName="alunomodalidade_seq", allocationSize=1)
public class AlunoModalidade extends AbstractEntity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="id_alunomodalidade")
	private Long id;
	
	@Temporal(TemporalType.DATE)
	private Date dataNasc;
	
	@ManyToOne
	private Aluno aluno;
	
	@ManyToOne
	private Modalidade modalidade;
	
	@OneToMany(mappedBy="alunoModalidade")
	private List<Ficha> fichas;

	public AlunoModalidade(Long id) {
		super();
		this.id = id;
	}

	public AlunoModalidade() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Modalidade getModalidade() {
		return modalidade;
	}

	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
	}

	public List<Ficha> getFichas() {
		return fichas;
	}

	public void setFichas(List<Ficha> fichas) {
		this.fichas = fichas;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

}
