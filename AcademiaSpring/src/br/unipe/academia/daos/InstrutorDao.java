package br.unipe.academia.daos;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import br.unipe.academia.daos.AbstractDao;
import br.unipe.academia.persistence.entity.Aluno;
import br.unipe.academia.persistence.entity.Instrutor;

@Repository
public class InstrutorDao extends AbstractDao<Instrutor> {

	public InstrutorDao() {
		super();
	}

	@Override
	public Class<Instrutor> getEntityClass() {
		return Instrutor.class;
	}
}
