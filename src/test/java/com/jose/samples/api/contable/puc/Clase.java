package com.jose.samples.api.contable.puc;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Clase {
	private Long id;
	private String tipo;
	private String codigo;
	private String nombre;
	private List<Grupo> grupos = new ArrayList<>();

	private static final String URI_CLASE = "http://localhost:8080/v1/puc/clases";
	private RestTemplate restTemplate = new RestTemplate();

	public Clase () {}

	public Clase (Long id, String tipo, String codigo, String nombre) {
		this.id = id;
		this.tipo = tipo;
		this.codigo = codigo;
		this.nombre = nombre;
		save();
	}

	public void setId (Long id) {
		this.id = id;
	}

	public void setTipo (String tipo) {
		this.tipo = tipo;
	}

	public void setCodigo (String codigo) {
		this.codigo = codigo;
	}

	public void setNombre (String nombre) {
		this.nombre = nombre;
	}

	public void setGrupos (List<Grupo> grupos) { this.grupos = grupos; }

	public Long getId () {
		return id;
	}

	public String getTipo () {
		return tipo;
	}

	public String getCodigo () {
		return codigo;
	}

	public String getNombre () {
		return nombre;
	}

	public List<Grupo> getGrupos () { return grupos; }

	@Override
	public String toString() {
		return "{ \"id\": " + id + ", " +
				"\"tipo\": \"" + tipo + "\", " +
				"\"codigo\": \"" + codigo + "\", " +
				"\"nombre\": \"" + nombre + "\", " +
				"\"grupos\": [" + gruposToString() + "]}";
	}

	private String gruposToString () {
		String grupoString = new String("");
		if(grupos != null){
			for (Grupo g : grupos) {
				grupoString += g.toString();
			}
		}

		return grupoString;
	}

	public void save () {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Clase> entityClase = new HttpEntity<>(this, httpHeaders);
		ResponseEntity<Clase> resposeClase = restTemplate.postForEntity(URI_CLASE, entityClase, Clase.class);

		Clase createdClase = null;
		createdClase = resposeClase.getBody();

		setId(createdClase.getId());
	}

	public List<Clase> listAll() {
		Clase[] clases = restTemplate.getForObject(URI_CLASE, Clase[].class);
		return Arrays.asList(clases);
	}

	public void findOne(Long id) {
		Clase existsClase = restTemplate.getForObject(URI_CLASE + "/{id}", Clase.class, id);
		setId(existsClase.getId());
		setTipo(existsClase.getTipo());
		setCodigo(existsClase.getCodigo());
		setNombre(existsClase.getNombre());
		setGrupos(existsClase.getGrupos());
	}

	public List<Clase> findByCodigoOrNombre(String codigo, String nombre) {
		Clase[] clases = restTemplate.getForObject(URI_CLASE + "?codigo={codigo}&nombre={nombre}", Clase[].class, codigo, nombre);
		return Arrays.asList(clases);
	}

	public void update () {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Clase> entityClase = new HttpEntity<>(this, httpHeaders);

		restTemplate.put(URI_CLASE + "/{id}", entityClase, getId());
	}

	public void delete () {
		restTemplate.delete(URI_CLASE + "/{id}", getId());
	}

	public void addGrupo (Grupo grupo) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Grupo> entityGrupo = new HttpEntity<>(grupo, httpHeaders);

		ResponseEntity<Grupo> resposeGrupo = restTemplate.postForEntity(URI_CLASE + "/{id}/grupos", entityGrupo, Grupo.class, getId());

		getGrupos().add(resposeGrupo.getBody());
	}

	public void listGrupos () {
		Grupo[] grupos_ = restTemplate.getForObject(URI_CLASE + "/{id}/grupos", Grupo[].class, getId());
		setGrupos(Arrays.asList(grupos_));
	}
}
