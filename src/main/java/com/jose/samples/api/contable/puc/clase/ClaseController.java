package com.jose.samples.api.contable.puc.clase;

import io.jsonwebtoken.lang.Assert;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/puc/clases")
public class ClaseController {

	private ClaseServiceImpl claseServiceImpl;

	public ClaseController (ClaseServiceImpl claseServiceImpl) {
		this.claseServiceImpl = claseServiceImpl;
	}

	@GetMapping("/{id}")
	public Clase findOne (@PathVariable Long id) {
		return claseServiceImpl.findOne(id);
	}

	@GetMapping
	public List<Clase> find (@RequestParam(defaultValue = "") String codigo, @RequestParam(defaultValue = "") String nombre) {
		if(!codigo.isEmpty() || !nombre.isEmpty()) {
			return claseServiceImpl.findByCodigoOrNombre(codigo, nombre);
		}else {
			return claseServiceImpl.findAll();
		}
	}

	@PostMapping
	public Clase add (@RequestBody Clase clase) {
		return claseServiceImpl.add (clase);
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

	@DeleteMapping("/{id}")
	public void delete (@PathVariable long id) {
		claseServiceImpl.delete(id);
	}
}
