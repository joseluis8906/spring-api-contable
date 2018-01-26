package com.jose.samples.api.contable.puc;


import javax.persistence.*;
import java.util.List;

@Entity
public class Cuenta extends PucModel {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne (targetEntity = Grupo.class, cascade = CascadeType.ALL)
	private Grupo grupo;

	@OneToMany(targetEntity = SubCuenta.class)
	private List<SubCuenta> subcuentas;
}
