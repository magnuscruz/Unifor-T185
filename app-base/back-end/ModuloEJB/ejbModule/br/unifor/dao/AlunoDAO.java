package br.unifor.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.log4j.Logger;

import br.unifor.entity.Aluno;
import br.unifor.infra.core.dao.SistemaDao;
import br.unifor.infra.core.dao.impl.AbstractDao;

@Stateless
public class AlunoDAO extends AbstractDao<Aluno, Aluno, Long> implements SistemaDao<Aluno, Aluno, Long> {

	private final static Logger LOG = Logger.getLogger(AlunoDAO.class);

	@PersistenceContext(name = "ExemploPU")
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	protected void extrairFiltros(Aluno filtro,
			CriteriaBuilder criteriaBuilder,
			CriteriaQuery criteriaQuery, Root<Aluno> root) {
		String nome = filtro.getNome();

		EntityType<Aluno> model = entityManager.getMetamodel().entity(Aluno.class);
		if (nome != null) {
			criteriaQuery.where(criteriaBuilder.like(criteriaBuilder.upper(root
					.get((SingularAttribute<Aluno, String>) model
							.getSingularAttribute("nome"))), "%" + nome.toUpperCase() + "%"));
		}

//		Double valor = filtro.getValor();
//
//		if (valor != null) {
//			criteriaQuery.where(criteriaBuilder.equal(root
//					.get((SingularAttribute<Produto, Double>) model
//							.getSingularAttribute("valor")), valor));
//		}
	}

}
