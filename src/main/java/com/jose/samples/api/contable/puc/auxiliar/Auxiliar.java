package com.jose.samples.api.contable.puc.auxiliar;

import com.jose.samples.api.contable.puc.PucModel;
import com.jose.samples.api.contable.puc.subcuenta.SubCuenta;

import javax.persistence.*;

@Entity
public class Auxiliar extends PucModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(targetEntity = SubCuenta.class, cascade = CascadeType.ALL)
	private SubCuenta subcuenta;

	public Auxiliar () {}

	public Auxiliar (String codigo, String nombre) {
		this.setCodigo(codigo);
		this.setNombre(nombre);
	}

	public void setSubcuenta (SubCuenta subcuenta) {
		this.subcuenta = subcuenta;
	}

	public SubCuenta getSubcuenta() {
		return subcuenta;
	}
}
