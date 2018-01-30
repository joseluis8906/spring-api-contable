package com.jose.samples.api.contable.puc.grupo;

import io.jsonwebtoken.lang.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/puc/grupos")
public class GrupoController {

	private GrupoServiceImpl grupoServiceImpl;

	public GrupoController(GrupoServiceImpl grupoServiceImpl) {
		this.grupoServiceImpl = grupoServiceImpl;
	}

	@GetMapping("/{id}")
	public Grupo findOne (@PathVariable Long id) {
		return grupoServiceImpl.findById(id);
	}

	@GetMapping("/{codigo}/{nombre}")
	public List<Grupo> findByCodigoOrNombre (@PathVariable String codigo, @PathVariable String nombre) {
		return grupoServiceImpl.findByCodigoOrNombre(codigo, nombre);
	}

	@GetMapping
	public List<Grupo> listAll () {
		return grupoServiceImpl.findAll();
	}

	@PostMapping
	public Grupo add (@RequestBody Grupo grupo) {
		return grupoServiceImpl.add (grupo);
	}

	@PutMapping("/{id}")
	public void update (@PathVariable Long id, @RequestBody Grupo grupo) {
		Grupo existingGrupo = grupoServiceImpl.findById(id);
		Assert.notNull(existingGrupo, "Grupo no encontrada");
		existingGrupo.setClase(grupo.getClase());
		existingGrupo.setCodigo(grupo.getCodigo());
		existingGrupo.setNombre(grupo.getNombre());
		grupoServiceImpl.update(existingGrupo);
	}

	@DeleteMapping("/{id}")
	public void delete (@PathVariable long id) {
		grupoServiceImpl.delete(id);
	}
}
