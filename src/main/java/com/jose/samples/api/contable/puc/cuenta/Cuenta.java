package com.jose.samples.api.contable.puc.cuenta;

import com.jose.samples.api.contable.puc.subcuenta.SubCuenta;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cuenta {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String codigo;
	private String nombre;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "cuenta_id")
	private List<SubCuenta> subcuentas;

	public Cuenta() {}

	public Cuenta(String codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public void setId (Long id) { this.id = id; }

	public void setCodigo (String codigo) {this.codigo = codigo; }

	public void setNombre (String nombre) { this.nombre = nombre; }

	public Long getId () { return id; }

	public String getCodigo () { return codigo; }

	public String getNombre () { return nombre; }

	public List<SubCuenta> getSubCuentas () {
		return subcuentas;
	}
}
