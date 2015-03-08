package br.unipe.academia.persistence.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name="id_ficha",sequenceName="ficha_seq",allocationSize=1)
public class Ficha extends AbstractEntity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="id_ficha")
	private Long id;
	
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@ManyToOne
	private AlunoModalidade alunoModalidade;
	
	@ManyToOne
	private Aluno aluno;
	
	@ManyToOne
	private Instrutor instrutor;
	
	@OneToMany(mappedBy="ficha")
	private List<FichaExercicio> fichaExercicios; 
	
	private String periodo;

	public Ficha() {

	}

	public Ficha(Long id) {
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

	public AlunoModalidade getAlunoModalidade() {
		return alunoModalidade;
	}

	public void setAlunoModalidade(AlunoModalidade alunoModalidade) {
		this.alunoModalidade = alunoModalidade;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public List<FichaExercicio> getFichaExercicios() {
		return fichaExercicios;
	}

	public void setFichaExercicios(List<FichaExercicio> fichaExercicios) {
		this.fichaExercicios = fichaExercicios;
	}

	public Instrutor getInstrutor() {
		return instrutor;
	}

	public void setInstrutor(Instrutor instrutor) {
		this.instrutor = instrutor;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

}