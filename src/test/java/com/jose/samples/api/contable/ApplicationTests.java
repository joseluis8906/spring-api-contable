package com.jose.samples.api.contable;

import com.jose.samples.api.contable.puc.Clase;
import com.sun.deploy.net.HttpResponse;
import jdk.nashorn.internal.codegen.ClassEmitter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class ApplicationTests {

	private static final String URI_CLASE = "http://localhost:8080/puc/clases";
	private RestTemplate restTemplate = new RestTemplate();

	public static void main (String args[]) {
		ApplicationTests applicationTests = new ApplicationTests();

		Clase clase = new Clase(null, "Comercial", "1", "Activo");
		applicationTests.add(clase);
		System.out.println(clase);

		List<Clase> clases =  applicationTests.listAll();
		clases.forEach(clase_ -> System.out.println(clase_.toString()));

		clase.setCodigo("2");
		clase.setNombre("Pasivo");
		clase.setTipo("Supersolidaria");
		applicationTests.update(10L, clase);

		clases =  applicationTests.listAll();
		clases.forEach(clase_ -> System.out.println(clase_.toString()));

	}

	public void add (Clase clase) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Clase> entityClase = new HttpEntity<>(clase, httpHeaders);
		ResponseEntity<Clase> resposeClase = restTemplate.postForEntity(URI_CLASE, entityClase, Clase.class);

		System.out.println(entityClase.getBody());
	}

	public List<Clase> listAll() {
		Clase[] clases = restTemplate.getForObject(URI_CLASE, Clase[].class);
		return Arrays.asList(clases);
	}

	public void update (Long id, Clase clase) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Clase> entityClase = new HttpEntity<>(clase, httpHeaders);

		restTemplate.put(URI_CLASE + "/{id}", entityClase, id);
	}

	public void delete (long id) {
		restTemplate.delete(URI_CLASE + "{id}", id);
	}

}
