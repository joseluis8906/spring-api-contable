package com.jose.samples.api.contable.puc;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class Cuenta {
	private Long id;
	private String codigo;
	private String nombre;
	private List<SubCuenta> subcuentas = new ArrayList<>();

	public Cuenta() {}

	public Cuenta(Long id, String codigo, String nombre) {
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public Long getId () {
		return id;
	}
	public void setId (Long id) {
		this.id = id;
	}

	public String getCodigo () {
		return codigo;
	}
	public void setCodigo (String codigo) {
		this.codigo = codigo;
	}

	public String getNombre () {
		return nombre;
	}
	public void setNombre (String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "{ \"id\": " + id + ", " +
				"\"codigo\": \"" + codigo + "\", " +
				"\"nombre\": \"" + nombre + "\"} ";
	}
}
