package com.jose.samples.api.contable.puc.subcuenta;

import com.jose.samples.api.contable.puc.PucModel;
import com.jose.samples.api.contable.puc.auxiliar.Auxiliar;
import com.jose.samples.api.contable.puc.cuenta.Cuenta;

import javax.persistence.*;
import java.util.List;

@Entity
public class SubCuenta extends PucModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(targetEntity = Cuenta.class, cascade = CascadeType.ALL)
	private Cuenta cuenta;

	@OneToMany(targetEntity = Auxiliar.class, cascade = CascadeType.ALL)
	private List<Auxiliar> auxiliares;

	public SubCuenta () {}

	public SubCuenta (String codigo, String nombre) {
		this.setCodigo(codigo);
		this.setNombre(nombre);
	}

	public void setCuenta (Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Cuenta getCuenta () {
		return cuenta;
	}

	public List<Auxiliar> getAuxiliares() {
		return auxiliares;
	}
}
