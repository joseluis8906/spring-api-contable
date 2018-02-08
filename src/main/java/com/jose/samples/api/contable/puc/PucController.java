package com.jose.samples.api.contable.puc;

import com.jose.samples.api.contable.puc.clase.Clase;
import com.jose.samples.api.contable.puc.clase.ClaseServiceImpl;
import com.jose.samples.api.contable.puc.cuenta.Cuenta;
import com.jose.samples.api.contable.puc.cuenta.CuentaServiceImpl;
import com.jose.samples.api.contable.puc.grupo.Grupo;
import com.jose.samples.api.contable.puc.grupo.GrupoServiceImpl;
import io.jsonwebtoken.lang.Assert;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/v1/puc")
@Transactional
public class PucController {

	private ClaseServiceImpl claseServiceImpl;
	private GrupoServiceImpl grupoServiceImpl;
	private CuentaServiceImpl cuentaServiceImpl;

	public PucController (
			ClaseServiceImpl claseServiceImpl,
			GrupoServiceImpl grupoServiceImpl,
			CuentaServiceImpl cuentaService
	) {
		this.claseServiceImpl = claseServiceImpl;
		this.grupoServiceImpl = grupoServiceImpl;
		this.cuentaServiceImpl = cuentaService;
	}

	@GetMapping("/clases/{id}")
	public Clase findById (@PathVariable Long id) {
		return claseServiceImpl.findById(id);
	}

	@GetMapping("/clases")
	public List<Clase> find (@RequestParam(value = "codigo", defaultValue = "") String codigo, @RequestParam(value = "nombre", defaultValue = "") String nombre) {
		if(!codigo.isEmpty() || !nombre.isEmpty()) {
			return claseServiceImpl.findByCodigoOrNombre(codigo, nombre);
		}else {
			return claseServiceImpl.findAll();
		}
	}

	@PostMapping("/clases")
	public Clase add (@RequestBody Clase clase) {
		return claseServiceImpl.add (clase);
	}

	@PutMapping("/clases/{id}")
	public void update (@PathVariable Long id, @RequestBody Clase clase) {
		Clase existingClase = claseServiceImpl.findById(id);
		Assert.notNull(existingClase, "Clase no encontrada");
		existingClase.setTipo(clase.getTipo());
		existingClase.setCodigo(clase.getCodigo());
		existingClase.setNombre(clase.getNombre());
		claseServiceImpl.update(existingClase);
	}

	@DeleteMapping("/clases/{id}")
	public void delete (@PathVariable Long id) {
		claseServiceImpl.delete(id);
	}

	@PostMapping("/clases/{id}/grupos")
	public Grupo addGrupo (@PathVariable Long id, @RequestBody Grupo grupo) {
		Clase existingClase = claseServiceImpl.findById(id);
		Assert.notNull(existingClase, "Clase no encontrada");
		existingClase.getGrupos().add(grupo);
		claseServiceImpl.update(existingClase);
		return grupo;
	}

	@GetMapping("/clases/{id}/grupos")
	public List<Grupo> listGrupos (@PathVariable Long id) {
		Clase existingClase = claseServiceImpl.findById(id);
		Assert.notNull(existingClase, "Clase no encontrada");
		return existingClase.getGrupos();
	}

	@GetMapping("/clases/{ClaseId}/grupos/{GrupoId}")
	public Grupo getGrupo (@PathVariable Long ClaseId, @PathVariable Long GrupoId){
		Clase existingClase = claseServiceImpl.findById(ClaseId);
		Assert.notNull(existingClase, "Clase no encontrada");
		Grupo grupo = existingClase.getGrupo(GrupoId);
		return grupo;
	}

	@GetMapping("/clases/{ClaseId}/grupos/{GrupoId}/cuentas")
	public List<Cuenta> listCuentas (@PathVariable Long ClaseId,  @PathVariable Long GrupoId) {
		Clase existingClase = claseServiceImpl.findById(ClaseId);
		Assert.notNull(existingClase, "Clase no encontrada");
		Grupo grupo = existingClase.getGrupo(GrupoId);
		return grupo.getCuentas();
	}



}
