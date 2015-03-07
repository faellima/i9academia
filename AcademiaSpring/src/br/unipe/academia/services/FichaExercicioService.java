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
import br.unipe.academia.daos.MedicaoDao;
import br.unipe.academia.persistence.entity.Aluno;
import br.unipe.academia.persistence.entity.Exercicio;
import br.unipe.academia.persistence.entity.Ficha;
import br.unipe.academia.persistence.entity.FichaExercicio;
import br.unipe.academia.persistence.entity.Medicao;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class FichaExercicioService {
	
	@Autowired
	private FichaExercicioDao fichaExercicioDao;
	
	@Autowired
	private FichaDao fichaDao;
	
	@Autowired
	private ExercicoDao exercicoDao;
	
	public FichaExercicioService() {
		super();
	}

	@Transactional
	public void SalvarFichaExercicio(FichaExercicio fichaexercicio){
			fichaExercicioDao.salavar(fichaexercicio);
	}
	
	@Transactional
	public void AtualizarFichaExercicio(FichaExercicio fichaexercicio){
			fichaExercicioDao.atualizar(fichaexercicio);
	}
	
	@Transactional
	public void ExcluirFichaExercicio(FichaExercicio fichaexercicio){
			fichaExercicioDao.excluir(fichaexercicio);
	}
	
	@Transactional
	public void ExcluirFichaExercicioComExercicioFicha(FichaExercicio fichaexercicio,	
														Exercicio exercicio, Ficha ficha){
			exercicoDao.excluir(exercicio);
			
			fichaDao.excluir(ficha);
			
			fichaExercicioDao.excluir(fichaexercicio);
	}
	
	public List<FichaExercicio> listarFichaExercicio(){
		return fichaExercicioDao.listar();
	}
	
	public List<FichaExercicio> listarPorFichaExercicoApartirDeFicha(Long id){
		return fichaExercicioDao.listarPorFichaExercicoApartirDeFicha(id);
	}
	
	public FichaExercicio buscarPorId(Long id) {
		return fichaExercicioDao.buscarPorIdLong(id);
	}
}
