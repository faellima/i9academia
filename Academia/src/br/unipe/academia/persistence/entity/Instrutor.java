package br.unipe.academia.persistence.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Instrutor extends Pessoa {

	@OneToMany(mappedBy="instrutor")
	private List<Ficha> finchas;
	
	@OneToMany(mappedBy="instrutor")
	private List<MedidaAvaliacao> medidaAvaliacoes;
	
	public Instrutor() {
		super();
	}

	public Instrutor(Long id){
		super(id);
	}

	public List<Ficha> getFinchas() {
		return finchas;
	}

	public void setFinchas(List<Ficha> finchas) {
		this.finchas = finchas;
	}

	public List<MedidaAvaliacao> getMedidaAvaliacoes() {
		return medidaAvaliacoes;
	}

	public void setMedidaAvaliacoes(List<MedidaAvaliacao> medidaAvaliacoes) {
		this.medidaAvaliacoes = medidaAvaliacoes;
	}
	
}
