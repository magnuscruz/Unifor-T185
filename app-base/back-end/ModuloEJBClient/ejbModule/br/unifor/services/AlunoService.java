package br.unifor.services;

import javax.ejb.Remote;

import br.unifor.entity.Aluno;
import br.unifor.infra.core.services.SistemaService;

@Remote
public interface AlunoService extends SistemaService<Aluno, Aluno, Long> {

}
