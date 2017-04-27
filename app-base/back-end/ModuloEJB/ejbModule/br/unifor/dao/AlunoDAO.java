package br.unifor.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import br.unifor.entity.Aluno;

@Stateless
public class AlunoDAO {

	private final static Logger LOG = Logger.getLogger(AlunoDAO.class);

	@PersistenceContext(name = "ExemploPU")
	private EntityManager manager;

	public String insere(Aluno aluno) {

		try {
			manager.persist(aluno);

			return "Aluno " + aluno.getNome() + " inserido";
		} catch (PersistenceException e) {
			LOG.error("ERRO DE ACESSO A DADOS", e);
			return "Erro ao inserir dados" + e;
		}

	}

	public List<Aluno> lista() {
		List<Aluno> list = null;
		try {
			list = manager.createQuery("SELECT a FROM Aluno a", Aluno.class)
					.getResultList();
		} catch (Exception e) {
			LOG.error("ERRO DE ACESSO A DADOS", e);
		}
		return list;
	}

	public List<Aluno> lista(int first, int pageSize) {
		List<Aluno> list = null;
		try {
			TypedQuery<Aluno> query = manager.createQuery("SELECT a FROM Aluno a", Aluno.class);
			query.setFirstResult(first);
			query.setMaxResults(pageSize);
			list = query.getResultList();
		} catch (Exception e) {
			LOG.error("ERRO DE ACESSO A DADOS", e);
		}
		return list;
	}

	public Integer count() {
		Long count = null;
		try {
			TypedQuery<Long> query = manager.createQuery("SELECT count(a) as valor FROM Aluno a", Long.class);
			count = query.getSingleResult();
		} catch (Exception e) {
			LOG.error("ERRO DE ACESSO A DADOS", e);
			throw e;
		}
		return count.intValue();
	}

	public String remove(Long id) {

		try {
			Aluno aluno = manager.find(Aluno.class, id);
			manager.remove(aluno);
			return "Aluno " + aluno.getNome() + " removido";
		} catch (PersistenceException e) {
			LOG.error("ERRO DE ACESSO A DADOS", e);
			return "Erro ao remover dados" + e;
		}
	}

	public String altera(Aluno aluno) {

		try {
			Aluno alunoDetached = manager.find(Aluno.class, aluno.getId());

			Aluno alunoManaged = manager.merge(alunoDetached);

			alunoManaged.setNome(aluno.getNome());
			alunoManaged.setEmail(aluno.getEmail());
			alunoManaged.setIdade(aluno.getIdade());
			alunoManaged.setCurso(aluno.getCurso());

			return "Aluno " + aluno.getNome() + " atualizado";
		} catch (PersistenceException e) {
			LOG.error("ERRO DE ACESSO A DADOS", e);
			return "Erro ao atualizar dados" + e;
		}
	}

}
