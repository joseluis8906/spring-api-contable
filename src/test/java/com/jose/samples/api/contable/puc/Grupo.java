package com.jose.samples.api.contable.puc;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class Grupo {
	private Long id;
	private String codigo;
	private String nombre;
	private List<Cuenta> cuentas = new ArrayList<>();

	private static final String URI_CLASE = "http://localhost:8080/v1/puc/grupos";
	private RestTemplate restTemplate = new RestTemplate();

	public Grupo() {}

	public Grupo(Long id, String codigo, String nombre) {
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public void setId (Long id) {
		this.id = id;
	}

	public void setCodigo (String codigo) {
		this.codigo = codigo;
	}

	public void setNombre (String nombre) {
		this.nombre = nombre;
	}

	public void setCuentas (List<Cuenta> cuentas) { this.cuentas = cuentas; }

	public Long getId () {
		return id;
	}

	public String getCodigo () {
		return codigo;
	}

	public String getNombre () {
		return nombre;
	}

	public List<Cuenta> getCuentas () { return cuentas; }

	@Override
	public String toString() {
		return "{ \"id\": " + id + ", " +
				"\"codigo\": \"" + codigo + "\", " +
				"\"nombre\": \"" + nombre + "\" " +
				"\"cuentas\": [" + cuentasToString() + "]}";
	}

	private String cuentasToString () {
		String cuentasString = new String("");
		if(cuentas != null){
			for (Cuenta c : cuentas) {
				cuentasString += c.toString();
			}
		}

		return cuentasString;
	}

	public void addCuenta (Cuenta cuenta) {
		getCuentas().add(cuenta);
	}

}
