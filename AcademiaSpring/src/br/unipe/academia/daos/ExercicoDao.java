package br.unipe.academia.daos;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import br.unipe.academia.daos.AbstractDao;
import br.unipe.academia.persistence.entity.Exercicio;
import br.unipe.academia.persistence.entity.Modalidade;

@Repository
public class ExercicoDao extends AbstractDao<Exercicio> {
	
	public ExercicoDao() {
		super();
	}

	@Override
	public Class<Exercicio> getEntityClass() {
		return Exercicio.class;
	}

}
