package br.unipe.academia.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@SequenceGenerator(name="id_fichaexercicio", sequenceName="fichaexercicio_seq", allocationSize=1)
public class FichaExercicio extends AbstractEntity {
 
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="id_fichaexercicio")
	private Long id;

	private int qtdRepeticoes;
	
	private int qtdSeries;
	
	@ManyToOne
	private Ficha ficha;
	
	@ManyToOne
	private Exercicio exercicio;

	public FichaExercicio(Long id) {
		super();
		this.id = id;
	}

	public FichaExercicio() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQtdRepeticoes() {
		return qtdRepeticoes;
	}

	public void setQtdRepeticoes(int qtdRepeticoes) {
		this.qtdRepeticoes = qtdRepeticoes;
	}

	public int getQtdSeries() {
		return qtdSeries;
	}

	public void setQtdSeries(int qtdSeries) {
		this.qtdSeries = qtdSeries;
	}

	public Ficha getFicha() {
		return ficha;
	}

	public void setFicha(Ficha ficha) {
		this.ficha = ficha;
	}

	public Exercicio getExercicio() {
		return exercicio;
	}

	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}
	
}
