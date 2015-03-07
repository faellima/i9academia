package br.unipe.academia.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.unipe.academia.daos.FichaDao;
import br.unipe.academia.daos.FichaExercicioDao;
import br.unipe.academia.daos.ExercicoDao;
import br.unipe.academia.daos.FichaDao;
import br.unipe.academia.daos.FichaExercicioDao;
import br.unipe.academia.daos.InstrutorDao;
import br.unipe.academia.daos.MedicaoDao;
import br.unipe.academia.persistence.entity.Aluno;
import br.unipe.academia.persistence.entity.Ficha;
import br.unipe.academia.persistence.entity.FichaExercicio;
import br.unipe.academia.persistence.entity.Exercicio;
import br.unipe.academia.persistence.entity.Ficha;
import br.unipe.academia.persistence.entity.FichaExercicio;
import br.unipe.academia.persistence.entity.Instrutor;
import br.unipe.academia.persistence.entity.Medicao;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class FichaService {
	
	@Autowired
	private FichaDao fichaDao;
	
	@Autowired
	private FichaExercicioDao fichaExercicioDao;
	
	@Transactional
	public void SalvarFicha(Ficha ficha){
			fichaDao.salavar(ficha);
	}
	
	public FichaService() {
		super();
	}

	@Transactional
	public void salvarFichaFichaExercicio(Ficha ficha, List<FichaExercicio> fichaExercicios){
		ficha.setFichaExercicios(fichaExercicios);
		fichaDao.salavar(ficha);	
		
		for (FichaExercicio fichaExercicio : fichaExercicios) {
			fichaExercicio.setFicha(ficha);
			fichaExercicioDao.salavar(fichaExercicio);
		}	
	}

	@Transactional
	public void AtualizarFicha(Ficha ficha){
			fichaDao.atualizar(ficha);
	}
	
	@Transactional
	public void ExcluirFicha(Ficha ficha){
			fichaDao.excluir(ficha);
	}
	
	public List<Ficha> listarFicha(){
		return fichaDao.listar();
	}

	public Ficha buscarPorId(Long id) {
		return fichaDao.buscarPorIdLong(id);
	}
	
	public List<Ficha> listarPorAtributoNomeEmFicha(String nome){
		return fichaDao.listarPorAtributoNomeEmFicha(nome);
	}
	
	public List<Ficha> listarPorAtributoModalidadeEmFicha(String nome){
		return fichaDao.listarPorAtributoModalidadeEmFicha(nome);
	}
	
	public List<Ficha> listarPorFichaApartirDoAluno(Long id){
		return fichaDao.listarPorFichaApartirDoAluno(id);
	}
}
