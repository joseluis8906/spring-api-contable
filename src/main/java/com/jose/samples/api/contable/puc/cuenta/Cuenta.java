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
	private long id;

	@ManyToOne (targetEntity = Grupo.class, cascade = CascadeType.ALL)
	private Grupo grupo;

	@OneToMany(targetEntity = SubCuenta.class)
	private List<SubCuenta> subcuentas;

	public Cuenta () {}

	public Cuenta (String codigo, String nombre) {
		this.setCodigo(codigo);
		this.setNombre(nombre);
	}

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
