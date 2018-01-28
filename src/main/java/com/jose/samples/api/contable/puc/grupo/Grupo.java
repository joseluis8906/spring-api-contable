package com.jose.samples.api.contable.puc.grupo;

import com.jose.samples.api.contable.puc.cuenta.Cuenta;
import com.jose.samples.api.contable.puc.PucModel;
import com.jose.samples.api.contable.puc.clase.Clase;

import javax.persistence.*;
import java.util.List;

@Entity
public class Grupo extends PucModel {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String codigo;
	private String nombre;

	@ManyToOne(targetEntity = Clase.class, cascade = CascadeType.ALL)
	private Clase clase;

	@OneToMany(targetEntity = Cuenta.class)
	private List<Cuenta> cuentas;

	public Grupo () {}

	public Grupo (String codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public void setCodigo (String codigo) {this.codigo = codigo; }

	public void setNombre (String nombre) { this.nombre = nombre; }

	public String getCodigo () { return codigo; }

	public String getNombre () { return nombre; }

	public void setClase (Clase clase) {
		this.clase = clase;
	}

	public Clase getClase () {
		return clase;
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}
}
