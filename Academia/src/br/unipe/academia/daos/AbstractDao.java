package br.unipe.academia.daos;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.unipe.academia.persistence.entity.AbstractEntity;
import br.unipe.academia.persistence.entity.Aluno;
import br.unipe.academia.persistence.entity.Modalidade;
import br.unipe.academia.persistence.entity.Usuario;


public abstract class AbstractDao<T extends AbstractEntity> {
	
	@PersistenceContext
	EntityManager entityManager;
	
	public void setEntityManager(EntityManager manager){
		this.entityManager = manager;
	}
		
	public AbstractDao(EntityManager entityManager){
		this.entityManager=entityManager;
	}
	
	public AbstractDao() {
		super();
	}

	public void salavar(T entity){
		entityManager.persist(entity);
	}
	
	public void atualizar(T entity){
		entityManager.merge(entity);
	}
	
	public void excluir(T entity){
		entity = entityManager.find(getEntityClass(), entity.getId());
		entityManager.remove(entity);
	}
	
	public List<T> listar(){
		Query query = entityManager.createQuery("select m from "+getEntityClass().getSimpleName()+" m");
		return query.getResultList();
	}
	
	public T buscaPorId(T entity) {
		return entityManager.find(getEntityClass(), entity.getId());
	}

	public T buscarPorIdLong(long id) {
		return entityManager.find(getEntityClass(), id);
	}
	
	public List<T> listarPorAtributo(String atributo, String valor) {
		Query query = entityManager.createQuery("select r from "
				+ getEntityClass().getSimpleName() + " r where " + "r." + atributo 
				+ " like :valor");
		query.setParameter("valor", "%"+ valor + "%");
		return query.getResultList();
	}
	
	public List<T> listarPorAtributoData(Date data) {
		Query query = entityManager.createQuery("select r from "
				+ getEntityClass().getSimpleName() + " r where " + "r.data  = :data");
		query.setParameter("data", data);
		return query.getResultList();
	}
	
	public List<T> buscarUsuarioPorLoginSenha(String login, String senha) {
		Query query = entityManager.createQuery("select u from "+ getEntityClass().getSimpleName() +" u where u.login = :login and u.senha = :senha");
		query.setParameter("login",login );
		query.setParameter("senha",senha );
		return query.getResultList();
	}
	
	public List<T> listarPorAtributoModalidade(String nome){
		Query query = entityManager.createQuery("select a from AlunoModalidade am JOIN am.aluno as a"
				+ " JOIN am.modalidade as m "
				+ " where m.nome like :nome").setParameter("nome","%"+ nome +"%");
		return query.getResultList();
	}
	
	public List<T> listarPorAtributoModalidadeEmInstrutor(String nome){
		Query query = entityManager.createQuery("select ins from Ficha am JOIN am.alunoModalidade as a"
				+ " JOIN a.modalidade as m "
				+ " JOIN am.instrutor as ins "
				+ " where m.nome like :nome").setParameter("nome","%"+ nome +"%");
		return query.getResultList();
	}

	public List<T>  listarPorAtributoModalidadeEmAluno(Long id){
		Query query = entityManager.createQuery("select am from AlunoModalidade am JOIN am.aluno as a"
				+ " JOIN am.modalidade as m "
				+ " where a.id = :id").setParameter("id",id);
		return query.getResultList();
	}
	
	public List<T> listarAvaliacaoApartirDeAluno(String nome) {
		Query query = entityManager.createQuery("select av from Avaliacao av JOIN av.aluno as a"
				+ " where a.nome like :nome").setParameter("nome","%"+ nome +"%");;
		return query.getResultList();
	}
	
	public List<T> listarPorAtributoNomeEmFicha(String nome) {
		Query query = entityManager.createQuery("select fc from Ficha fc JOIN fc.aluno as a"
				+ " where a.nome like :nome").setParameter("nome","%"+ nome +"%");;
		return query.getResultList();
	}
	
	public List<T> listarPorAtributoModalidadeEmFicha(String nome){
		Query query = entityManager.createQuery("select fc from Ficha fc JOIN fc.aluno as a"
				+ " JOIN a.alunosModalidade as am "
				+ " JOIN am.modalidade as m "
				+ " where m.nome like :nome").setParameter("nome","%"+ nome +"%");
		return query.getResultList();
	}
	
	public List<T> listarPorMedidaAvaliacao(Long id){
		Query query = entityManager.createQuery("select ma from MedidaAvaliacao ma JOIN ma.avaliacao as a"
				+ " JOIN ma.medicao as m "
				+ " where a.id = :id").setParameter("id",id);
		return query.getResultList();
	}
	
	public List<T> listarPorFichaApartirDoAluno(Long id){
		Query query = entityManager.createQuery("select fc from Ficha fc JOIN fc.aluno as a"
				+ " where a.id = :id").setParameter("id",id);
		return query.getResultList();
	}
	
	public List<T> listarPorFichaExercicoApartirDeFicha(Long id){
		Query query = entityManager.createQuery("select fe from FichaExercicio fe JOIN fe.ficha as f"
				+ " JOIN fe.exercicio as e "
				+ " where f.id = :id").setParameter("id",id);
		return query.getResultList();
	}
	
	public List<T> listarUsuarioAparitrDeAluno(Long id){
		Query query = entityManager.createQuery("select u from Usuario u JOIN u.pessoa as p"
				+ " where p.id = :id").setParameter("id",id);
		return query.getResultList();
	}
	
	
	public abstract Class<T> getEntityClass();
}
