package com.jose.samples.api.contable.puc.clase;

import com.jose.samples.api.contable.puc.grupo.Grupo;
import com.jose.samples.api.contable.puc.PucModel;

import javax.persistence.*;
import java.util.List;

@Entity
public class Clase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String tipo;
	private String codigo;
	private String nombre;

	@OneToMany(targetEntity = Grupo.class)
	private List<Grupo> grupos;

	public Clase () {}

	public Clase (String tipo, String codigo, String nombre) {
		this.tipo = tipo;
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public void setId (Long id) { this.id = id; }

	public void setTipo (String tipo) {
		this.tipo = tipo;
	}

	public void setCodigo (String codigo) {this.codigo = codigo; }

	public void setNombre (String nombre) { this.nombre = nombre; }

	public void setGrupos (List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public Long getId () { return id; }

	public String getTipo () { return tipo;	}

	public String getCodigo () { return codigo; }

	public String getNombre () { return nombre; }

	public List<Grupo> getGrupos () {
		return grupos;
	}
}
