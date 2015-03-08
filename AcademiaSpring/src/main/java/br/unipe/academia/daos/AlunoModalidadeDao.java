package br.unipe.academia.daos;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import br.unipe.academia.persistence.entity.AlunoModalidade;
import br.unipe.academia.persistence.entity.Modalidade;

@Repository
public class AlunoModalidadeDao extends AbstractDao<AlunoModalidade> {

	public AlunoModalidadeDao() {
	}

	@Override
	public Class<AlunoModalidade> getEntityClass() {
		return AlunoModalidade.class;
	}

}
