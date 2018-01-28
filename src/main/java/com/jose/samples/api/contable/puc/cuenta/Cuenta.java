package com.jose.samples.api.contable.puc.cuenta;


import com.jose.samples.api.contable.puc.PucModel;
import com.jose.samples.api.contable.puc.subcuenta.SubCuenta;
import com.jose.samples.api.contable.puc.grupo.Grupo;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cuenta extends PucModel {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String codigo;
	private String nombre;

	@ManyToOne (targetEntity = Grupo.class, cascade = CascadeType.ALL)
	private Grupo grupo;

	@OneToMany(targetEntity = SubCuenta.class)
	private List<SubCuenta> subcuentas;

	public Cuenta () {}

	public Cuenta (String codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public void setCodigo (String codigo) {this.codigo = codigo; }

	public void setNombre (String nombre) { this.nombre = nombre; }

	public String getCodigo () { return codigo; }

	public String getNombre () { return nombre; }

	public void setGrupo (Grupo grupo) {
		this.grupo = grupo;
	}

	public Grupo getGrupo () {
		return grupo;
	}

	public List<SubCuenta> getSubcuentas() {
		return subcuentas;
	}
}
