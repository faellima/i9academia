package br.unipe.academia.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="id_medidaavaliacao", sequenceName="medidaavaliacao_seq", allocationSize=1)
public class MedidaAvaliacao extends AbstractEntity{

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="id_medidaavaliacao")
	private Long id;
	
	private double valor;
	
	@ManyToOne
	private Avaliacao avaliacao;
	
	@ManyToOne
	private Medicao medicao;
	
	@ManyToOne
	private Instrutor instrutor; 
	
	public MedidaAvaliacao() {
		super();
	}

	public MedidaAvaliacao(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Medicao getMedicao() {
		return medicao;
	}

	public void setMedicao(Medicao medicao) {
		this.medicao = medicao;
	}

	public Instrutor getInstrutor() {
		return instrutor;
	}

	public void setInstrutor(Instrutor instrutor) {
		this.instrutor = instrutor;
	}
	
}
