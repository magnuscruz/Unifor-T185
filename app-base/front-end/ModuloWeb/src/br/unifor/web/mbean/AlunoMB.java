package br.unifor.web.mbean;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.unifor.entity.Aluno;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

@ManagedBean
public class AlunoMB {
	public List<Aluno> getAlunos() {
		Client c = Client.create();
		WebResource wr = c
				.resource("http://localhost:8080/ExemploRest/rest/alunos");
		String json = wr.get(String.class);
		Gson gson = new Gson();
		return gson.fromJson(json, new TypeToken<List<Aluno>>() {}.getType());
	}
}