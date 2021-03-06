package com.jose.samples.api.contable.puc.clase;

import com.jose.samples.api.contable.puc.grupo.Grupo;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = {
				"tipo",
				"nombre"
		})
})
public class Clase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String tipo;
	private String codigo;
	private String nombre;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "clase_id")
	private List<Grupo> grupos;

	public Clase () {}

	public Clase (ClaseServiceImpl.ClaseBuilder builder) {
		this.tipo = builder.getTipo();
		this.codigo = builder.getCodigo();
		this.nombre = builder.getNombre();
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
