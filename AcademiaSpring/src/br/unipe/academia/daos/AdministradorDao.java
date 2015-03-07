package br.unipe.academia.daos;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import br.unipe.academia.persistence.entity.Administrador;
import br.unipe.academia.persistence.entity.Modalidade;

@Repository
public class AdministradorDao extends AbstractDao<Administrador> {
	
	public AdministradorDao() {
		super();
	}

	@Override
	public Class<Administrador> getEntityClass() {
		return Administrador.class;
	}

}
