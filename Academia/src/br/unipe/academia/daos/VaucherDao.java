package br.unipe.academia.daos;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import br.unipe.academia.persistence.entity.Vaucher;

@Repository
public class VaucherDao extends AbstractDao<Vaucher> {
	
	public VaucherDao() {
		super();
	}

	@Override
	public Class<Vaucher> getEntityClass() {
		return Vaucher.class;
	} 
}
