package br.unifor.restful.resources;

import static javax.ws.rs.core.MediaType.*;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.unifor.entity.Aluno;
import br.unifor.services.AlunoService;


@Stateless
@Path("/alunos")
public class AlunoResource {
	
	@EJB(name="AlunoService",mappedName="AlunoService")
    private AlunoService alunoBean;
	
	@Path("{first}/{page}")
	@GET
	@Produces(APPLICATION_JSON)
	public Collection<Aluno> listaAlunos( @PathParam("first") Integer first,  @PathParam("page") Integer page) {				
		
		return alunoBean.listaAlunos(first, page);	
	}	

	@Path("count")
	@GET
	@Produces(APPLICATION_JSON)
	public Integer count() {				
		return alunoBean.count();	
	}

	@POST
	@Consumes({ APPLICATION_XML, TEXT_XML, APPLICATION_JSON })
	@Produces(TEXT_PLAIN)
	public String adicionaAluno(Aluno aluno) {
						
		return alunoBean.adicionaAluno(aluno);
	}	
		
	@PUT
	@Consumes(APPLICATION_JSON)
	@Produces(TEXT_PLAIN)
	public String alteraAluno(Aluno aluno) {		
				
		return alunoBean.alteraAluno(aluno);
	}
	
	@Path("{id}")
	@DELETE	
	@Produces(TEXT_PLAIN)
	public String removeAluno(@PathParam("id") Long id) {
		
		return alunoBean.removeAluno(id);
	}	

}

