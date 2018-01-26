package com.jose.samples.api.contable.puc;

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
}
