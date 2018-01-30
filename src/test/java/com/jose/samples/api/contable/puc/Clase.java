package com.jose.samples.api.contable.puc;

public class Clase {
	private Long id;
	private String tipo;
	private String codigo;
	private String nombre;
	private Grupo[] grupos;

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

	public void setGrupos (Grupo[] grupos) { this.grupos = grupos; }

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

	public Grupo[] getGrupos () { return grupos; }

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
			for (int i=0; i<grupos.length; i++) {
				grupoString += grupos[i].toString();
			}
		}

		return grupoString;
	}
}
