package br.unipe.academia.persistence.entity;

import javax.persistence.Entity;

@Entity
public class Administrador extends Pessoa {
	private String vaucher;
	
	public Administrador() {
		// TODO Auto-generated constructor stub
	}
	
	public Administrador(Long id) {
		super(id);
	}

	public String getVaucher() {
		return vaucher;
	}

	public void setVaucher(String vaucher) {
		this.vaucher = vaucher;
	}
}
