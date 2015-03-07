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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name="id_avaliacao",sequenceName="avaliacao_seq",allocationSize=1)
public class Avaliacao extends AbstractEntity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="id_avaliacao")
	private Long id;
	
	@ManyToOne
	private Aluno aluno;
	
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@OneToMany(mappedBy="avaliacao")
	private List<MedidaAvaliacao> medidaavaliacoes;
	
	public Avaliacao(long id) {
		super();
		this.id = id;
	}
	public Avaliacao() {
	
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
	public List<MedidaAvaliacao> getMedidasavaliacoes() {
		return medidaavaliacoes;
	}
	public void setMedidasavaliacoes(List<MedidaAvaliacao> medidaavaliacoes) {
		this.medidaavaliacoes = medidaavaliacoes;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
}