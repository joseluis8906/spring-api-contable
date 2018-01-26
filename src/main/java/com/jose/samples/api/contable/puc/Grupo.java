package com.jose.samples.api.contable.puc;

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
}
