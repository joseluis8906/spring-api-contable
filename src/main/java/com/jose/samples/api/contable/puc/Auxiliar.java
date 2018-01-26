package com.jose.samples.api.contable.puc;

import javax.persistence.*;

@Entity
public class Auxiliar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(targetEntity = SubCuenta.class, cascade = CascadeType.ALL)
	private SubCuenta subcuenta;
}
