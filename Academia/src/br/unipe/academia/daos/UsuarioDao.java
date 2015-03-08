package br.unipe.academia.daos;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import br.unipe.academia.persistence.entity.Aluno;
import br.unipe.academia.persistence.entity.Usuario;

@Repository
public class UsuarioDao extends AbstractDao<Usuario> {
	
	public UsuarioDao() {
		super();
	}

	@Override
	public Class<Usuario> getEntityClass() {
		return Usuario.class;
	}

}
