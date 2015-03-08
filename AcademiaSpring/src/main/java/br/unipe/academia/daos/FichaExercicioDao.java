package br.unipe.academia.daos;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import br.unipe.academia.persistence.entity.FichaExercicio;
import br.unipe.academia.persistence.entity.Modalidade;

@Repository
public class FichaExercicioDao extends AbstractDao<FichaExercicio> {
	
	public FichaExercicioDao() {
		super();
	}

	@Override
	public Class<FichaExercicio> getEntityClass() {
		return FichaExercicio.class;
	}

}
