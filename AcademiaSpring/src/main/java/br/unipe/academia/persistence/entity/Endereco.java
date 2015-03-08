package br.unipe.academia.persistence.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Endereco {
	
	private String rua;
	
	private String bairro;
	
	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	private String complemento;

}
