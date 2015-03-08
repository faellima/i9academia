package br.unipe.academia.daos;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.unipe.academia.persistence.entity.Modalidade;

@Repository
public class ModalidadeDao extends AbstractDao<Modalidade> {

	public ModalidadeDao() {
		super();
	}

	@Override
	public Class<Modalidade> getEntityClass() {
		return Modalidade.class;
	}

}
