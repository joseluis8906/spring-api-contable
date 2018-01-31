package com.jose.samples.api.contable.puc.clase;

import com.jose.samples.api.contable.puc.grupo.Grupo;
import io.jsonwebtoken.lang.Assert;
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
	public Clase findById (@PathVariable Long id) {
		return claseServiceImpl.findById(id);
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
		Clase existingClase = claseServiceImpl.findById(id);
		Assert.notNull(existingClase, "Clase no encontrada");
		existingClase.setTipo(clase.getTipo());
		existingClase.setCodigo(clase.getCodigo());
		existingClase.setNombre(clase.getNombre());
		claseServiceImpl.update(existingClase);
	}

	@DeleteMapping("/{id}")
	public void delete (@PathVariable Long id) {
		claseServiceImpl.delete(id);
	}

	@PostMapping("/{id}/grupos")
	public void addGrupo (@PathVariable Long id, @RequestParam Grupo grupo) {
		Clase existingClase = claseServiceImpl.findById(id);
		Assert.notNull(existingClase, "Clase no encontrada");
		existingClase.getGrupos().add(grupo);
		claseServiceImpl.update(existingClase);
	}
}
