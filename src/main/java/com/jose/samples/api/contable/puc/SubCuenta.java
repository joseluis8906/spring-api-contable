package com.jose.samples.api.contable.puc;

import javax.persistence.*;

@Entity
public class SubCuenta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(targetEntity = Cuenta.class, cascade = CascadeType.ALL)
	private Cuenta cuenta;
}
