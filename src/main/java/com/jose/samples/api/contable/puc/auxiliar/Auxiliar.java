package com.jose.samples.api.contable.puc.auxiliar;

import javax.persistence.*;

@Entity
public class Auxiliar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String codigo;
	private String nombre;

	public Auxiliar() {}

	public Auxiliar(String codigo, String nombre) {
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
