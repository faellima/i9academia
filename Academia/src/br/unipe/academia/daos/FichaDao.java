package br.unipe.academia.daos;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import br.unipe.academia.persistence.entity.Ficha;
import br.unipe.academia.persistence.entity.Modalidade;

@Repository
public class FichaDao extends AbstractDao<Ficha>{
	
	public FichaDao() {
		super();
	}

	@Override
	public Class<Ficha> getEntityClass() {
		return Ficha.class;
	}


}
