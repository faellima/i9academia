package br.unipe.academia.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.unipe.academia.daos.AlunoDao;
import br.unipe.academia.daos.AvaliacaoDao;
import br.unipe.academia.daos.AvaliacaoDao;
import br.unipe.academia.daos.MedidaAvaliacaoDao;
import br.unipe.academia.daos.ModalidadeDao;
import br.unipe.academia.persistence.entity.Aluno;
import br.unipe.academia.persistence.entity.Avaliacao;
import br.unipe.academia.persistence.entity.Avaliacao;
import br.unipe.academia.persistence.entity.Medicao;
import br.unipe.academia.persistence.entity.MedidaAvaliacao;
import br.unipe.academia.persistence.entity.Modalidade;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AvaliacaoService {
	
	@Autowired
	private AvaliacaoDao avaliacaoDao;
	
	@Autowired
	private MedidaAvaliacaoDao medidaAvaliacaoDao;
	
	public AvaliacaoService() {
		super();
	}

	@Transactional
	public void salvarAvaliacao(Avaliacao avaliacao){
		avaliacaoDao.salavar(avaliacao);	
	}
	
	@Transactional
	public void salvarAvaliacaoMedida(Avaliacao avaliacao, List<MedidaAvaliacao> medidadasAvaliacoes){
		avaliacao.setMedidasavaliacoes(medidadasAvaliacoes);
		avaliacaoDao.salavar(avaliacao);		
		
		for (MedidaAvaliacao medidaAvaliacao : medidadasAvaliacoes) {
			medidaAvaliacao.setAvaliacao(avaliacao);
			medidaAvaliacaoDao.salavar(medidaAvaliacao);
		}
	}
	
	@Transactional
	public void atualizarAvaliacao(Avaliacao avaliacao){
		avaliacaoDao.atualizar(avaliacao);
	}
	
	@Transactional
	public void exluirAvaliacao(Avaliacao avaliacao){
		avaliacaoDao.excluir(avaliacao);
	}
	
	public List<Avaliacao> listarAvaliacao(){
		return avaliacaoDao.listar();
	}
	
	
	public Avaliacao buscar(Avaliacao avaliacao){
		return avaliacaoDao.buscaPorId(avaliacao);
	}
	
	public Avaliacao buscarPorId(Long id) {
		return avaliacaoDao.buscarPorIdLong(id);
	}
	
	public List<Avaliacao> listarAvaliacaoApartirDeAluno(String nome){
		return avaliacaoDao.listarAvaliacaoApartirDeAluno(nome);
	}
}
