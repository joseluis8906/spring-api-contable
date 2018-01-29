package com.jose.samples.api.contable.puc.auxiliar;

import com.jose.samples.api.contable.puc.PucModel;
import com.jose.samples.api.contable.puc.subcuenta.SubCuenta;

import javax.persistence.*;

@Entity
public class Auxiliar extends PucModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String codigo;
	private String nombre;

	@ManyToOne(targetEntity = SubCuenta.class, cascade = CascadeType.ALL)
	private SubCuenta subcuenta;

	public Auxiliar () {}

	public Auxiliar (String codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public void setId (Long id) { this.id = id; }

	public void setCodigo (String codigo) {this.codigo = codigo; }

	public void setNombre (String nombre) { this.nombre = nombre; }

	public Long getId () { return id; }

	public String getCodigo () { return codigo; }

	public String getNombre () { return nombre; }

	public void setSubcuenta (SubCuenta subcuenta) {
		this.subcuenta = subcuenta;
	}

	public SubCuenta getSubcuenta() {
		return subcuenta;
	}
}
