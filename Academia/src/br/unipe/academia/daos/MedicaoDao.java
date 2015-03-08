package br.unipe.academia.daos;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import br.unipe.academia.persistence.entity.Aluno;
import br.unipe.academia.persistence.entity.Medicao;

@Repository
public class MedicaoDao extends AbstractDao<Medicao> {

	public MedicaoDao() {
		super();
	}

	@Override
	public Class<Medicao> getEntityClass() {
		return Medicao.class;
	}
}
