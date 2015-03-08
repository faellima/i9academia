package br.unipe.academia.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.unipe.academia.daos.ExercicoDao;
import br.unipe.academia.daos.FichaDao;
import br.unipe.academia.daos.FichaExercicioDao;
import br.unipe.academia.persistence.entity.Aluno;
import br.unipe.academia.persistence.entity.Exercicio;
import br.unipe.academia.persistence.entity.Exercicio;
import br.unipe.academia.persistence.entity.Ficha;
import br.unipe.academia.persistence.entity.FichaExercicio;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ExercicioService {

	@Autowired
	private ExercicoDao exercicioDao;
	
	@Autowired
	private FichaExercicioDao fichaExercicioDao;
	
	public ExercicioService() {
		super();
	}

	@Transactional
	public void SalvarExercicio(Exercicio exercicio){
		exercicioDao.salavar(exercicio);
	}
	
	@Transactional
	public void salvarExercicioFicha(Exercicio exercicio, List<FichaExercicio> fichaExercicios){
		exercicio.setFichaExercicios(fichaExercicios);
		exercicioDao.salavar(exercicio);	
		
		for (FichaExercicio fichaExercicio : fichaExercicios) {
			fichaExercicio.setExercicio(exercicio);
			fichaExercicioDao.salavar(fichaExercicio);
		}
		
	}
	
	@Transactional
	public void ExluirExercicio(Exercicio exercicio){
		exercicioDao.excluir(exercicio);
	}
		
	@Transactional
	public void AtualizarExercicio(Exercicio exercicio){
		exercicioDao.atualizar(exercicio);
	}	
	
	public List<Exercicio> listarExercicio(){
		return exercicioDao.listar();
	}
	
	public List<Exercicio> listarPorAtributoNome(String nome){
		return exercicioDao.listarPorAtributo("nome", nome);
	}
	
	@Transactional
	public Exercicio buscar(Exercicio exercicio){
		return exercicioDao.buscaPorId(exercicio);
	}
	
	public Exercicio buscarPorId(Long id) {
		return exercicioDao.buscarPorIdLong(id);
	}
}
