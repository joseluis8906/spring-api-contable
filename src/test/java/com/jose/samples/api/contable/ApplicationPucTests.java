package com.jose.samples.api.contable;

import com.jose.samples.api.contable.puc.Clase;
import com.jose.samples.api.contable.puc.Grupo;

import java.util.List;

public class ApplicationPucTests {

	public static void main (String args[]) {
		ApplicationPucTests app = new ApplicationPucTests();
		app.listGrupos();
	}

	public void saveAClase () {
		Clase clase = new Clase(null, "Comercial", "1", "Activo");
		clase.save();
		System.out.println("Created Clase: " + clase);
	}

	public void getClase () {
		Clase clase = new Clase();
		clase.findOne(1L);
		System.out.println("return clase with id = " + clase.getId() +": " + clase);
	}

	public void updateClase () {
		Clase clase = new Clase();
		clase.findOne(1L);
		clase.setCodigo("3");
		clase.setNombre("Patrimonio");
		clase.setTipo("Supersolidaria");
		clase.update();
		System.out.println("clase updated with id = " + clase.getId() + ": " + clase);
	}

	public void deleteClase () {
		Clase clase = new Clase ();
		clase.findOne(1L);
		clase.delete();
		System.out.println("clase deleted with id = " + clase.getId() + ": " + clase);
	}

	public void getAllClases () {
		Clase clase = new Clase(null, "Comercial", "1", "Activo");
		clase = new Clase(null, "Comercial", "2", "Pasivo");
		clase = new Clase(null, "Comercial", "3", "Patrimonio");

		List<Clase> clases =  new Clase().listAll();
		System.out.println("List all clases: ");
		clases.forEach(clase_ -> System.out.println(clase_.toString()));
	}

	public void getFilterClases () {
		Clase clase = new Clase(null, "Comercial", "1", "Activo");
		clase = new Clase(null, "Comercial", "2", "Pasivo");
		clase = new Clase(null, "Comercial", "3", "Patrimonio");

		List<Clase> clases =  new Clase().findByCodigoOrNombre("1", "Pas");
		System.out.println("List clases filtered: ");
		clases.forEach(clase_ -> System.out.println(clase_.toString()));
	}

	public void addGrupos () {
		Clase clase = new Clase(null, "2", "Pasivo", "Comercial");
		clase.addGrupo(new Grupo(null, "25", "Corrientes"));
		clase.addGrupo(new Grupo(null, "26", "Otros"));
		System.out.println("Two grupos added to clase = " + clase);
	}

	public void listGrupos () {
		Clase clase = new Clase(null, "2", "Pasivo", "Comercial");
		clase.addGrupo(new Grupo(null, "25", "Corrientes"));
		clase.addGrupo(new Grupo(null, "26", "Otros"));
		System.out.println("Two grupos added to clase = " + clase);

		Long lId = clase.getId();

		clase = new Clase();
		clase.findOne(lId);
		clase.listGrupos();
		System.out.println("grupos of clase with = " + clase.getId() +": " + clase);
		List<Grupo> grupos = clase.getGrupos();

		grupos.forEach(g_ -> System.out.println(g_.toString()));
	}

}
