package com.jose.samples.api.contable.puc.clase;

import io.jsonwebtoken.lang.Assert;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/puc/clases")
public class ClaseController {

	private ClaseServiceImpl claseServiceImpl;

	public ClaseController (ClaseServiceImpl claseServiceImpl) {
		this.claseServiceImpl = claseServiceImpl;
	}

	@GetMapping("/{id}")
	public Clase findOne (@PathVariable Long id) {
		return claseServiceImpl.findOne(id);
	}

	@GetMapping("/{codigo}/{nombre}")
	public List<Clase> findByCodigoOrNombre (@PathVariable String codigo, @PathVariable String nombre) {
		return claseServiceImpl.findByCodigoOrNombre(codigo, nombre);
	}

	@GetMapping
	public List<Clase> listAll () {
		return claseServiceImpl.findAll();
	}

	@PostMapping
	public void add (@RequestBody Clase clase) {
		claseServiceImpl.add (clase);
	}

	@PutMapping("/{id}")
	public void update (@PathVariable Long id, @RequestBody Clase clase) {
		Clase existingClase = claseServiceImpl.findOne(id);
		Assert.notNull(existingClase, "Clase no encontrada");
		existingClase.setTipo(clase.getTipo());
		existingClase.setCodigo(clase.getCodigo());
		existingClase.setNombre(clase.getNombre());
		claseServiceImpl.update(existingClase);
	}
}
