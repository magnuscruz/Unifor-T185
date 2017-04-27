package br.unifor.web.mbean;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.LazyDataModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import javax.faces.bean.ViewScoped;

import br.unifor.entity.Aluno;

@ManagedBean
@ViewScoped
public class AlunoMB {
	private static final String URL_REST = "http://localhost:8080/ExemploRest/rest/alunos";
	private Aluno aluno;
	private LazyDataModel<Aluno> dataModel;

	@PostConstruct
	public void init() {
		if (aluno == null) {
			aluno = new Aluno();
		}
		if (dataModel == null) {
			dataModel = new AlunoDataModel();
		}
	}

	public List<Aluno> getAlunos() {
		Client c = Client.create();
		WebResource wr = c.resource(URL_REST);
		String json = wr.get(String.class);
		Gson gson = new Gson();
		return gson.fromJson(json, new TypeToken<List<Aluno>>() {
		}.getType());
	}

	public String inserir() {
		Client c = Client.create();
		WebResource wr = c.resource(URL_REST);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		wr.type(APPLICATION_JSON).post(ClientResponse.class, gson.toJson(aluno));
		return "";
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public LazyDataModel<Aluno> getDataModel() {
		return dataModel;
	}

	public void setDataModel(LazyDataModel<Aluno> dataModel) {
		this.dataModel = dataModel;
	}

}