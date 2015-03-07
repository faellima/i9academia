package br.unipe.academia.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.unipe.academia.persistence.entity.Aluno;
import br.unipe.academia.persistence.entity.Modalidade;


@Repository
public class AlunoDao extends AbstractDao<Aluno>{

	public AlunoDao() {
	}

	@Override
	public Class<Aluno> getEntityClass() {
		return Aluno.class;
	}
	
//	public List<Aluno> listarPorAtributoModalidade(String nome){
//		Query query = entityManager.createQuery("select a from AlunoModalidade am JOIN am.aluno as a"
//				+ " JOIN am.modalidade as m "
//				+ " where m.nome like :nome").setParameter("nome","%"+ nome +"%");
//		List<Aluno> alunos = query.getResultList();
//		return query.getResultList();
//	}

}
