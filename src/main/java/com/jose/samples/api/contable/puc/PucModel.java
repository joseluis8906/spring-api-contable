package com.jose.samples.api.contable.puc;

public abstract class PucModel {
	private String codigo;
	private String nombre;

	public void setCodigo (String Codigo) {
		this.codigo = Codigo;
	}

	public void setNombre (String Nombre) {
		this.nombre = Nombre;
	}

	public String getCodigo () { return codigo; }

	public String getNombre () { return nombre; }
}
