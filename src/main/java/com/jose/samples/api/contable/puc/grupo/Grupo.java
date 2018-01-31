package com.jose.samples.api.contable.puc.grupo;

import com.jose.samples.api.contable.puc.cuenta.Cuenta;

import javax.persistence.*;
import java.util.List;

@Entity
public class Grupo {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String codigo;
	private String nombre;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "grupo_id")
	private List<Cuenta> cuentas;

	public Grupo () {}

	public Grupo (String codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public void setId (Long id) { this.id = id; }

	public void setCodigo (String codigo) {this.codigo = codigo; }

	public void setNombre (String nombre) { this.nombre = nombre; }

	public void setCuentas (List<Cuenta> cuents) {
		this.cuentas = cuentas;
	}

	public Long getId () { return id; }

	public String getCodigo () { return codigo; }

	public String getNombre () { return nombre; }

	public List<Cuenta> getCuentas () {
		return cuentas;
	}
}
