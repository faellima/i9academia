package br.unipe.academia.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.unipe.academia.daos.MedidaAvaliacaoDao;
import br.unipe.academia.daos.AvaliacaoDao;
import br.unipe.academia.persistence.entity.MedidaAvaliacao;
import br.unipe.academia.persistence.entity.Avaliacao;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class MedidaAvaliacaoService {

	@Autowired
	private MedidaAvaliacaoDao medidaAvaliacaoDao;
	
	public MedidaAvaliacaoService() {
		super();
	}

	@Transactional
	public void salvarMedidaAvaliacao(MedidaAvaliacao medidaAvaliacao){
		medidaAvaliacaoDao.salavar(medidaAvaliacao);	
	}
		
	@Transactional
	public void atualizarMedidaAvaliacao(MedidaAvaliacao medidaAvaliacao){
		medidaAvaliacaoDao.atualizar(medidaAvaliacao);
	}
	
	@Transactional
	public void excluirMedidaAvaliacao(MedidaAvaliacao medidaAvaliacao){
		medidaAvaliacaoDao.excluir(medidaAvaliacao);
	}

	@Transactional
	public List<MedidaAvaliacao> listarMedidaAvaliacao(){
		return medidaAvaliacaoDao.listar();
	}
	
	@Transactional
	public List<MedidaAvaliacao> listarMedidaAvaliacaoApartirDeAvaliacao(Long id){
		return medidaAvaliacaoDao.listarPorMedidaAvaliacao(id);
	}
}
