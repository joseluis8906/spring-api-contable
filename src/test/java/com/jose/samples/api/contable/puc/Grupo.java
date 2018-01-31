package com.jose.samples.api.contable.puc;

import java.util.List;

public class Grupo {
	private Long id;
	private String codigo;
	private String nombre;
	private Clase clase;

	public Grupo() {}

	public Grupo(Long id, String codigo, String nombre) {
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

	public Clase getClase () { return clase; }

	@Override
	public String toString() {
		return "{ \"id\": " + id + ", " +
				"\"codigo\": \"" + codigo + "\", " +
				"\"nombre\": \"" + nombre + "\", " +
				"\"clase\": " + clase.toString() + "}";
	}
}
