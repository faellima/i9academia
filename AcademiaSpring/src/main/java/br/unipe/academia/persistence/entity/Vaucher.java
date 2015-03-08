package br.unipe.academia.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


@Entity
@SequenceGenerator(name="id_vaucher",sequenceName="vaucher_seq")
public class Vaucher extends AbstractEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="id_vaucher")
	private Long id;
	
	private String sequenciador;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSequenciador() {
		return sequenciador;
	}

	public void setSequenciador(String sequenciador) {
		this.sequenciador = sequenciador;
	}
	
	
}
