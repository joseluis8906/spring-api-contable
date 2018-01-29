package com.jose.samples.api.contable;

import com.jose.samples.api.contable.puc.Clase;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class ApplicationClaseTests {

	private static final String URI_CLASE = "http://localhost:8080/puc/clases";
	private RestTemplate restTemplate = new RestTemplate();

	public static void main (String args[]) {
		ApplicationClaseTests applicationTests = new ApplicationClaseTests();

		Clase clase = new Clase(null, "Comercial", "1", "Activo");
		clase = applicationTests.add(clase);

		System.out.println("Created Clase: \n" + clase);

		List<Clase> clases =  applicationTests.listAll();
		System.out.println("List all clases: ");
		clases.forEach(clase_ -> System.out.println(clase_.toString()));

		clase.setCodigo("2");
		clase.setNombre("Pasivo");
		clase.setTipo("Supersolidaria");
		applicationTests.update(clase);

		clases =  applicationTests.listAll();
		System.out.println("List all clases after update: ");
		clases.forEach(clase_ -> System.out.println(clase_.toString()));

		applicationTests.delete(clase.getId());
		clases =  applicationTests.listAll();
		System.out.println("List all clases after delete: ");
		clases.forEach(clase_ -> System.out.println(clase_.toString()));
	}

	public Clase add (Clase clase) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Clase> entityClase = new HttpEntity<>(clase, httpHeaders);
		ResponseEntity<Clase> resposeClase = restTemplate.postForEntity(URI_CLASE, entityClase, Clase.class);

		Clase createdClase = null;
		createdClase = resposeClase.getBody();

		return createdClase;
	}

	public List<Clase> listAll() {
		Clase[] clases = restTemplate.getForObject(URI_CLASE, Clase[].class);
		return Arrays.asList(clases);
	}

	public void update (Clase clase) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Clase> entityClase = new HttpEntity<>(clase, httpHeaders);

		restTemplate.put(URI_CLASE + "/{id}", entityClase, clase.getId());
	}

	public void delete (long id) {
		restTemplate.delete(URI_CLASE + "/{id}", id);
	}

}
