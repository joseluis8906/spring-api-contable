package com.jose.samples.api.contable.puc.grupo;

import com.jose.samples.api.contable.puc.clase.Clase;

import javax.persistence.*;

@Entity
public class Grupo {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String codigo;
	private String nombre;

	public Grupo () {}

	public Grupo (String codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public void setId (Long id) { this.id = id; }

	public void setCodigo (String codigo) {this.codigo = codigo; }

	public void setNombre (String nombre) { this.nombre = nombre; }

	public Long getId () { return id; }

	public String getCodigo () { return codigo; }

	public String getNombre () { return nombre; }
}
