package br.unifor.services.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.unifor.dao.AlunoDAO;
import br.unifor.entity.Aluno;
import br.unifor.infra.core.dao.SistemaDao;
import br.unifor.infra.core.services.impl.AbstractServiceImpl;
import br.unifor.services.AlunoService;

/**
 * Session Bean implementation class AlunoBean
 */
@Stateless(name="AlunoService",mappedName="AlunoService")
public class AlunoServiceImpl extends AbstractServiceImpl<Aluno, Aluno, Long> implements AlunoService {

	@EJB
	private AlunoDAO alunoDAO;

	public AlunoServiceImpl() {

	}

	@Override
	protected SistemaDao<Aluno, Aluno, Long> getDao() {
		return alunoDAO;
	}

	@Override
	protected Class<Aluno> getEntidadeClass() {
		return Aluno.class;
	}

}
