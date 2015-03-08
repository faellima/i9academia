package br.unipe.academia.daos;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import br.unipe.academia.persistence.entity.Aluno;
import br.unipe.academia.persistence.entity.MedidaAvaliacao;

@Repository
public class MedidaAvaliacaoDao extends AbstractDao<MedidaAvaliacao> {

	public MedidaAvaliacaoDao() {
		super();
	}

	@Override
	public Class<MedidaAvaliacao> getEntityClass() {
		return MedidaAvaliacao.class;
	}
}
