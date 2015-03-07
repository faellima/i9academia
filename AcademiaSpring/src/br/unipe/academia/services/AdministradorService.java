package br.unipe.academia.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.unipe.academia.daos.AdministradorDao;
import br.unipe.academia.daos.AvaliacaoDao;
import br.unipe.academia.persistence.entity.Administrador;
import br.unipe.academia.persistence.entity.Aluno;
import br.unipe.academia.persistence.entity.Avaliacao;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AdministradorService {
	
	@Autowired
	private AdministradorDao administradorDao;

	public AdministradorService() {
		super();
	}

	@Transactional
	public void salvarAdministrador(Administrador administrador){
		administradorDao.salavar(administrador);	
	}
		
	@Transactional
	public void atualizarAdministrador(Administrador administrador){
		administradorDao.atualizar(administrador);
	}
	
	@Transactional
	public void exluirAdministrador(Administrador administrador){
		administradorDao.excluir(administrador);
	}

	public List<Administrador> listarAdministrador(){
		return administradorDao.listar();
	}
	
	public Administrador buscarPorId(Long id) {
		return administradorDao.buscarPorIdLong(id);
	}
}
