package br.unifor.services.impl;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.unifor.dao.AlunoDAO;
import br.unifor.entity.Aluno;
import br.unifor.services.AlunoService;

/**
 * Session Bean implementation class AlunoBean
 */
@Stateless(name="AlunoService",mappedName="AlunoService")
public class AlunoServiceImpl implements AlunoService {

	@EJB
	private AlunoDAO alunoDAO;

	public AlunoServiceImpl() {

	}

	@Override
	public Collection<Aluno> listaAlunos() {

		return this.alunoDAO.lista();
	}

	@Override
	public String adicionaAluno(Aluno aluno) {

		return this.alunoDAO.insere(aluno);
	}

	@Override
	public String removeAluno(Long id) {

		return this.alunoDAO.remove(id);
	}

	@Override
	public String alteraAluno(Aluno aluno) {

		return this.alunoDAO.altera(aluno);
	}

	@Override
	public Collection<Aluno> listaAlunos(int first, int pageSize) {
		return this.alunoDAO.lista(first, pageSize);
	}

	@Override
	public Integer count() {
		return this.alunoDAO.count();
	}
}
