package br.unipe.academia.services;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.unipe.academia.daos.AvaliacaoDao;
import br.unipe.academia.daos.InstrutorDao;
import br.unipe.academia.daos.MedidaAvaliacaoDao;
import br.unipe.academia.daos.ModalidadeDao;
import br.unipe.academia.persistence.entity.Aluno;
import br.unipe.academia.persistence.entity.AlunoModalidade;
import br.unipe.academia.persistence.entity.Avaliacao;
import br.unipe.academia.persistence.entity.Instrutor;
import br.unipe.academia.persistence.entity.MedidaAvaliacao;
import br.unipe.academia.persistence.entity.Modalidade;
import br.unipe.academia.persistence.entity.Usuario;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class InstrutorService {
	
	@Autowired
	private InstrutorDao instrutorDao;
	
	@Autowired
	private MedidaAvaliacaoDao medidaAvaliacaoDao;
	
	public InstrutorService() {
		super();
	}

	@Transactional
	public void SalvarInstrutor(Instrutor instrutor){
		instrutorDao.salavar(instrutor);		
	}
	
	@Transactional
	public void salvarInstrutorMedida(Instrutor instrutor, List<MedidaAvaliacao> medidadasAvaliacoes){
		instrutor.setMedidaAvaliacoes(medidadasAvaliacoes);
		instrutorDao.salavar(instrutor);		
	
		for (MedidaAvaliacao medidaAvaliacao : medidadasAvaliacoes) {
			medidaAvaliacao.setInstrutor(instrutor);
			medidaAvaliacaoDao.salavar(medidaAvaliacao);
		}

	}
	
	@Transactional
	public void ExcluirInstrutor(Instrutor instrutor){
		instrutorDao.excluir(instrutor);
	}
	
	@Transactional
	public void AtualizarInstrutor(Instrutor instrutor){
		instrutorDao.atualizar(instrutor);			
	}
	
	public List<Instrutor> listar(){
		return instrutorDao.listar();
	}
	
	public List<Instrutor> listarPorNome(String nome){
		return instrutorDao.listarPorAtributo("nome", nome);
	}
	
	public List<Instrutor> listarPorAtributoNome(String nome){
		return instrutorDao.listarPorAtributo("nome", nome);
	}
	
	public Instrutor buscar(Instrutor instrutor){
		return (Instrutor) instrutorDao.buscaPorId(instrutor);
	}
	
	public Instrutor buscarPorId(Long id) {
		return instrutorDao.buscarPorIdLong(id);
	}
	
	public List<Instrutor> listarPorAtributoModalidadeEmInstrutor(String nome){
		return instrutorDao.listarPorAtributoModalidadeEmInstrutor(nome);
	}
}
