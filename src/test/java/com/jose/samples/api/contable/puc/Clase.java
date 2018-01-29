package com.jose.samples.api.contable.puc;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Clase {
	private Long id;
	private String tipo;
	private String codigo;
	private String nombre;

	public Clase () {}

	public Clase (Long id, String tipo, String codigo, String nombre) {
		this.id = id;
		this.tipo = tipo;
		this.codigo = codigo;
		this.nombre = nombre;
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

	@Override
	public String toString() {
		return "{ \"id\": " + id + ", " +
				"\"tipo\": \"" + tipo + "\", " +
				"\"codigo\": \"" + codigo + "\", " +
				"\"nombre\": \"" + nombre + "\"}";
	}
}
