package com.jose.samples.api.contable;

import com.jose.samples.api.contable.puc.Clase;
import com.jose.samples.api.contable.puc.Cuenta;
import com.jose.samples.api.contable.puc.Grupo;

import java.util.List;

public class ApplicationClaseTests {



	public static void main (String args[]) {

		Clase clase = new Clase(null, "Comercial", "1", "Activo");
		clase.save();
		System.out.println("Created Clase: \n" + clase);

		clase = new Clase(null, "Comercial", "2", "Pasivo");
		clase.save();
		System.out.println("Created Clase: \n" + clase);

		List<Clase> clases =  new Clase().listAll();
		System.out.println("List all clases: ");
		clases.forEach(clase_ -> System.out.println(clase_.toString()));

		clase.findOne(clase.getId());
		System.out.println("Clases with id = 1: \n" + clase);

		clases =  new Clase().findByCodigoOrNombre("1", "Pas");
		System.out.println("List clases filtered: ");
		clases.forEach(clase_ -> System.out.println(clase_.toString()));

		/*clase.setCodigo("3");
		clase.setNombre("Patrimonio");
		clase.setTipo("Supersolidaria");
		clase.update();*/

		clase.addGrupo(new Grupo(null, "11", "Bancos"));
		clase.getGrupos().forEach(grupo -> grupo.addCuenta(new Cuenta(null, "1110", "Caja")));
		clase.save();

		clases =  new Clase().listAll();
		System.out.println("List all clases after update: ");
		clases.forEach(clase_ -> System.out.println(clase_.toString()));

		/*
		clases =  new Clase().findByCodigoOrNombre("1", "Pat");
		System.out.println("List clases filtered: ");
		clases.forEach(clase_ -> System.out.println(clase_.toString()));

		clase.delete();
		clases =  new Clase().listAll();
		System.out.println("List all clases after delete: ");
		clases.forEach(clase_ -> System.out.println(clase_.toString()));
		*/
	}
}
