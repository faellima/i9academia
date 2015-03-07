package br.unipe.academia.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.unipe.academia.daos.AdministradorDao;
import br.unipe.academia.daos.AlunoDao;
import br.unipe.academia.daos.AvaliacaoDao;
import br.unipe.academia.daos.InstrutorDao;
import br.unipe.academia.daos.AlunoDao;
import br.unipe.academia.persistence.entity.Administrador;
import br.unipe.academia.persistence.entity.Aluno;
import br.unipe.academia.persistence.entity.Avaliacao;
import br.unipe.academia.persistence.entity.Instrutor;
import br.unipe.academia.persistence.entity.Medicao;
import br.unipe.academia.persistence.entity.Pessoa;
import br.unipe.academia.persistence.entity.Aluno;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AlunoService {
	
	@Autowired
	private AlunoDao alunoDao;
	
	@Autowired
	private AvaliacaoDao avaliacaoDao;
	
	public AlunoService() {
		super();
	}

	@Transactional
	public void salvarAluno(Aluno aluno){
		alunoDao.salavar(aluno);	
	}
	
	@Transactional
	public void salvarAlunoAvaliacao(Aluno aluno, List<Avaliacao> avaliacoes){
		aluno.setAvaliacoes(avaliacoes);
		alunoDao.salavar(aluno);	
		
		for (Avaliacao avaliacao : avaliacoes) {
			avaliacao.setAluno(aluno);
			avaliacaoDao.salavar(avaliacao);
		}
	}
	
	@Transactional
	public void atualizarAluno(Aluno aluno){
		alunoDao.atualizar(aluno);
	}
	
	@Transactional
	public void exluirAluno(Aluno aluno){
		alunoDao.excluir(aluno);
	}

	public List<Aluno> listarAluno(){
		return alunoDao.listar();
	}
	
	public List<Aluno> listarPorAtributoNome(String nome){
		return alunoDao.listarPorAtributo("nome", nome);
	}
	

	public List<Aluno> listarPorAtributoModalidade(String nome){
		return alunoDao.listarPorAtributoModalidade(nome);
	}
	

	public Aluno buscar(Aluno aluno){
		return (Aluno) alunoDao.buscaPorId(aluno);
	}
	
	public Aluno buscarPorId(Long id) {
		return alunoDao.buscarPorIdLong(id);
	}
}
