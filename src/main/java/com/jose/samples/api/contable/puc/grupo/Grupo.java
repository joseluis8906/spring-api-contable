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
	private long id;

	@ManyToOne(targetEntity = Clase.class, cascade = CascadeType.ALL)
	private Clase clase;

	@OneToMany(targetEntity = Cuenta.class)
	private List<Cuenta> cuentas;

	public Grupo () {}

	public Grupo (String codigo, String nombre) {
		this.setCodigo(codigo);
		this.setNombre(nombre);
	}

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
