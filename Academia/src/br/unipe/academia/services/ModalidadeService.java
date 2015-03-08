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
import br.unipe.academia.daos.AlunoModalidadeDao;
import br.unipe.academia.daos.ModalidadeDao;
import br.unipe.academia.daos.UsuarioDao;
import br.unipe.academia.persistence.entity.Aluno;
import br.unipe.academia.persistence.entity.AlunoModalidade;
import br.unipe.academia.persistence.entity.Modalidade;
import br.unipe.academia.persistence.entity.Usuario;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ModalidadeService {

	@Autowired
	private ModalidadeDao modalidadeDao;
	
	@Autowired
	private AlunoDao alunoDao;
	
	public ModalidadeService() {
		super();
	}

//	@Autowired
//	private AlunoModalidade alunoModalidade;
	
	@Autowired
	private AlunoModalidadeDao alunoModalidadeDao;
	
	@Transactional
	public void salvarModalidade(Modalidade modalidade){
		modalidadeDao.salavar(modalidade);	
	}

	@Transactional
	public void excluirAtualizar(Modalidade modalidade, Modalidade modalidade1){
		modalidadeDao.atualizar(modalidade);
		modalidadeDao.excluir(modalidade1);
	}
	
	@Transactional
	public void salvarAlunoEModalidade(Aluno aluno, Modalidade modalidade){
		alunoDao.salavar(aluno);
		modalidadeDao.salavar(modalidade);
	 	
		AlunoModalidade alunoModalidade = new AlunoModalidade();
		
		alunoModalidade.setAluno(aluno);
		alunoModalidade.setModalidade(modalidade);
		alunoModalidadeDao.salavar(alunoModalidade);
		
	}
	
	public List<Modalidade> listarModalidade(){
		return modalidadeDao.listar();
	}
	
	@Transactional
	public void excluir(Modalidade modalidade){
		modalidadeDao.excluir(modalidade);
	}
	
	@Transactional
	public void atualizar(Modalidade modalidade){
		modalidadeDao.atualizar(modalidade);
	}
	
	public Modalidade buscar(Modalidade modalidade){
		return modalidadeDao.buscaPorId(modalidade);
	}
	
	public List<Modalidade> listarPorNome(String nome){
		return modalidadeDao.listarPorAtributo("nome", nome);
	}
		
	public Modalidade buscarPorId(Long id) {
		return modalidadeDao.buscarPorIdLong(id);
	}
}


