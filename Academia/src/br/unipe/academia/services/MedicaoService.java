package br.unipe.academia.services;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.unipe.academia.daos.AlunoDao;
import br.unipe.academia.daos.AvaliacaoDao;
import br.unipe.academia.daos.MedicaoDao;
import br.unipe.academia.daos.MedidaAvaliacaoDao;
import br.unipe.academia.persistence.entity.Aluno;
import br.unipe.academia.persistence.entity.Avaliacao;
import br.unipe.academia.persistence.entity.Exercicio;
import br.unipe.academia.persistence.entity.Medicao;
import br.unipe.academia.persistence.entity.MedidaAvaliacao;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class MedicaoService {

	@Autowired
	private MedicaoDao medicaoDao;
	
	@Autowired
	private MedidaAvaliacaoDao medidaAvaliacaoDao;
	
	public MedicaoService() {
		super();
	}

	@Transactional
	public void salvarMedicao(Medicao medicao){
		medicaoDao.salavar(medicao);	
	}
	
	@Transactional
	public void salvarMedicaoMedida(Medicao medicao, List<MedidaAvaliacao> medidadasAvaliacoes){
		medicao.setMedidaavaliacoes(medidadasAvaliacoes);
		medicaoDao.salavar(medicao);		

		for (MedidaAvaliacao medidaAvaliacao : medidadasAvaliacoes) {
			medidaAvaliacao.setMedicao(medicao);
			medidaAvaliacaoDao.salavar(medidaAvaliacao);
		}		
	}
		
	@Transactional
	public void atualizarMedicao(Medicao medicao){
		medicaoDao.atualizar(medicao);
	}
	
	@Transactional
	public void exluirMedicao(Medicao medicao){
		medicaoDao.excluir(medicao);
	}

	
	public List<Medicao> listarMedicao(){
		return medicaoDao.listar();
	}
	
	public List<Medicao> listarPorAtributoNome(String nome){
		return medicaoDao.listarPorAtributo("nome", nome);
	}
	
	public List<Medicao> listarPorAtributoData(Date data){
		return medicaoDao.listarPorAtributoData(data);
	}
	
	public Medicao buscar(Medicao medicao){
		return medicaoDao.buscaPorId(medicao);
	}
	
	public Medicao buscarPorId(Long id) {
		return medicaoDao.buscarPorIdLong(id);
	}
	
}
