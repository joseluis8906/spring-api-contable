package com.jose.samples.api.contable.puc.clase;

import com.jose.samples.api.contable.puc.grupo.Grupo;
import com.jose.samples.api.contable.puc.PucModel;

import javax.persistence.*;
import java.util.List;

@Entity
public class Clase extends PucModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String tipo;

	@OneToMany(targetEntity = Grupo.class)
	private List<Grupo> grupos;

	public Clase () {}

	public Clase (String tipo, String codigo, String nombre) {
		this.setTipo(tipo);
		this.setCodigo(codigo);
		this.setNombre(nombre);
	}

	public void setTipo (String tipo) {
		this.tipo = tipo;
	}

	public List<Grupo> getGrupos () {
		return grupos;
	}
}
