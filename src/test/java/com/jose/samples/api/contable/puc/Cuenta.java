package com.jose.samples.api.contable.puc;

import org.springframework.web.client.RestTemplate;

import java.util.List;

public class Cuenta {
	private Long id;
	private String codigo;
	private String nombre;

	private static final String URI_CLASE = "http://localhost:8080/v1/puc/cuentas";
	private RestTemplate restTemplate = new RestTemplate();

	public Cuenta() {}

	public Cuenta(Long id, String codigo, String nombre) {
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public void setId (Long id) {
		this.id = id;
	}

	public void setCodigo (String codigo) {
		this.codigo = codigo;
	}

	public void setNombre (String nombre) {
		this.nombre = nombre;
	}

	public Long getId () {
		return id;
	}

	public String getCodigo () {
		return codigo;
	}

	public String getNombre () {
		return nombre;
	}

	@Override
	public String toString() {
		return "{ \"id\": " + id + ", " +
				"\"codigo\": \"" + codigo + "\", " +
				"\"nombre\": \"" + nombre + "\"} ";
	}
}
