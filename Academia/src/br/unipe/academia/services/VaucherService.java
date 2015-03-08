package br.unipe.academia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import br.unipe.academia.daos.VaucherDao;
import br.unipe.academia.persistence.entity.Vaucher;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class VaucherService {
	
	@Autowired
	private VaucherDao vaucherDao;
	
	public VaucherService() {
		super();
	}

	@Transactional
	public void salvarVaucher(Vaucher vaucher){
		vaucherDao.salavar(vaucher);	
	}
	
	@Transactional
	public void atualizarVaucher(Vaucher vaucher){
		vaucherDao.atualizar(vaucher);
	}
	
	@Transactional
	public void exluirVaucher(Vaucher vaucher){
		vaucherDao.excluir(vaucher);
	}

	public List<Vaucher> listarVaucher(){
		return vaucherDao.listar();
	}
}
