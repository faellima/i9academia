package br.unipe.academia.daos;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import br.unipe.academia.persistence.entity.Avaliacao;
import br.unipe.academia.persistence.entity.Modalidade;

@Repository
public class AvaliacaoDao extends AbstractDao<Avaliacao> {
	
	public AvaliacaoDao() {
		super();
	}

	@Override
	public Class<Avaliacao> getEntityClass() {
		return Avaliacao.class;
	}

}
