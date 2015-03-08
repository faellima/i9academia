package br.unipe.academia.persistence.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name="id_exercico",sequenceName="exercico_seq")
public class Exercicio extends AbstractEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="id_exercico")
	private Long id;
	
	private String nome;
	
	@OneToMany(mappedBy="exercicio")
	private List<FichaExercicio> fichaExercicios;
	
	public Exercicio() {
		super();
	}
	public Exercicio(Long id) {
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
	public List<FichaExercicio> getFichaExercicios() {
		return fichaExercicios;
	}
	public void setFichaExercicios(List<FichaExercicio> fichaExercicios) {
		this.fichaExercicios = fichaExercicios;
	}
	
}