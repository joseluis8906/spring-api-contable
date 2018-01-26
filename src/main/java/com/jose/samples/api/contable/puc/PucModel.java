package com.jose.samples.api.contable.puc;

abstract class PucModel {
	private String Codigo;
	private String Nombre;

	public void setCodigo (String Codigo) {
		this.Codigo = Codigo;
	}

	public void setNombre (String Nombre) {
		this.Nombre = Nombre;
	}
}
