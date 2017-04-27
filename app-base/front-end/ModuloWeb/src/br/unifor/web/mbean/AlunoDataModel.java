package br.unifor.web.mbean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import br.unifor.entity.Aluno;

/**
 * Dummy implementation of LazyDataModel that uses a list to mimic a real
 * datasource like a database.
 */
public class AlunoDataModel extends LazyDataModel<Aluno> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2689909354203052167L;
	private static final String URL_REST = "http://localhost:8080/ExemploRest/rest/alunos";
	private List<Aluno> datasource;

	public AlunoDataModel() {
	}

	@Override
	public Aluno getRowData(String rowKey) {
		for (Aluno aluno : datasource) {
			if (aluno.getId().equals(rowKey))
				return aluno;
		}

		return null;
	}

	@Override
	public Object getRowKey(Aluno aluno) {
		return aluno.getId();
	}

	@Override
	public List<Aluno> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		setPageSize(3);
		setRowIndex(first);
		datasource = new ArrayList<Aluno>();
		Client c = Client.create();
		WebResource wr = c.resource(URL_REST + "/" + first + "/" + pageSize);
		String json = wr.get(String.class);
		Gson gson = new Gson();
		datasource = gson.fromJson(json, new TypeToken<List<Aluno>>() {
		}.getType());
		wr = c.resource(URL_REST + "/count");
		json = wr.get(String.class);
		Integer count = gson.fromJson(json, new TypeToken<Integer>() {
		}.getType());
		setRowCount(count);

		return datasource;
	}

	@Override
	public List<Aluno> load(int first, int pageSize,
			List<SortMeta> multiSortMeta, Map<String, Object> filters) {
		return load(first, pageSize, null, null, null);
	}

}