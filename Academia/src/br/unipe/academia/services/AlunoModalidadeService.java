package br.unipe.academia.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.unipe.academia.daos.AlunoModalidadeDao;
import br.unipe.academia.daos.AvaliacaoDao;
import br.unipe.academia.persistence.entity.Aluno;
import br.unipe.academia.persistence.entity.AlunoModalidade;
import br.unipe.academia.persistence.entity.Avaliacao;
import br.unipe.academia.persistence.entity.Modalidade;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AlunoModalidadeService {
	
	@Autowired
	private AlunoModalidadeDao alunoModalidadeDao;

	public AlunoModalidadeService() {
		super();
	}

	@Transactional
	public void salvarAlunoModalidade(AlunoModalidade alunoModalidade){
		alunoModalidadeDao.salavar(alunoModalidade);	
	}

	@Transactional
	public void atualizarAlunoModalidade(AlunoModalidade alunoModalidade){
		alunoModalidadeDao.atualizar(alunoModalidade);
	}
	
	@Transactional
	public void exluirAlunoModalidade(AlunoModalidade alunoModalidade){
		alunoModalidadeDao.excluir(alunoModalidade);
	}

	
	public List<AlunoModalidade> listarAlunoModalidade(){
		return alunoModalidadeDao.listar();
	}
	
	public List<AlunoModalidade> listarPorAtributoModalidadeEmAluno(Long id){
		return alunoModalidadeDao.listarPorAtributoModalidadeEmAluno(id);
	}

	public AlunoModalidade buscarPorId(Long id) {
		return alunoModalidadeDao.buscarPorIdLong(id);
	}
}
