package com.jose.samples.api.contable.puc;

import com.jose.samples.api.contable.puc.auxiliar.Auxiliar;
import com.jose.samples.api.contable.puc.clase.Clase;
import com.jose.samples.api.contable.puc.clase.ClaseServiceImpl;
import com.jose.samples.api.contable.puc.cuenta.Cuenta;
import com.jose.samples.api.contable.puc.cuenta.CuentaServiceImpl;
import com.jose.samples.api.contable.puc.grupo.Grupo;
import com.jose.samples.api.contable.puc.grupo.GrupoServiceImpl;
import com.jose.samples.api.contable.puc.subcuenta.SubCuenta;
import com.jose.samples.api.contable.puc.subcuenta.SubCuentaServiceImpl;
import io.jsonwebtoken.lang.Assert;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	private SubCuentaServiceImpl subCuentaServiceImpl;

	public PucController (
			ClaseServiceImpl claseServiceImpl,
			GrupoServiceImpl grupoServiceImpl,
			CuentaServiceImpl cuentaServiceImpl,
			SubCuentaServiceImpl subCuentaServiceImpl
	) {
		this.claseServiceImpl = claseServiceImpl;
		this.grupoServiceImpl = grupoServiceImpl;
		this.cuentaServiceImpl = cuentaServiceImpl;
		this.subCuentaServiceImpl = subCuentaServiceImpl;
	}

	@GetMapping("/clases/{id}")
	public Clase findById (@PathVariable Long id) {
		return claseServiceImpl.findById(id);
	}

	@GetMapping("/clases")
	public List<Clase> find (
			@RequestParam(value = "codigo", defaultValue = "") String codigo,
			@RequestParam(value = "nombre", defaultValue = "") String nombre ){

		if(!codigo.isEmpty() || !nombre.isEmpty()) {
			return claseServiceImpl.findByCodigoOrNombre(codigo, nombre);
		}

		return claseServiceImpl.findAll();
	}

	@PostMapping("/clases")
	public Clase add (@RequestBody Clase clase) {
		claseServiceImpl.add (clase);
		return clase;
	}

	@PutMapping("/clases/{id}")
	public void update (@PathVariable Long id, @RequestBody Clase clase) {
		Clase existingClase = claseServiceImpl.findById(id);
		if(null != existingClase){
			existingClase.setTipo(clase.getTipo());
			existingClase.setCodigo(clase.getCodigo());
			existingClase.setNombre(clase.getNombre());
			claseServiceImpl.update(existingClase);
		}
	}

	@DeleteMapping("/clases/{id}")
	public void delete (@PathVariable Long id) {
		claseServiceImpl.delete(id);
	}

	@PostMapping("/clases/{id}/grupos")
	public Grupo addGrupo (@PathVariable Long id, @RequestBody Grupo grupo) {
		Clase existingClase = claseServiceImpl.findById(id);
		if(null == existingClase) {
			return null;
		}
		existingClase.getGrupos().add(grupo);
		claseServiceImpl.update(existingClase);
		return grupo;
	}

	@GetMapping("/clases/{id}/grupos")
	public List<Grupo> listGrupos (@PathVariable Long id) {
		Clase existingClase = claseServiceImpl.findById(id);
		if (null == existingClase) {
			return null;
		}
		return existingClase.getGrupos();
	}

	@GetMapping("/clases/{ClaseId}/grupos/{GrupoId}")
	public Grupo getGrupo (
			@PathVariable Long ClaseId,
			@PathVariable Long GrupoId ){

		Clase existingClase = claseServiceImpl.findById(ClaseId);

		if(null == existingClase) {
			return null;
		}

		List<Grupo> grupos = existingClase.getGrupos();

		for (Grupo grupo: grupos){
			if(grupo.getId() == GrupoId){
				return grupo;
			}
		}

		return null;
	}

	@PostMapping("/clases/{ClaseId}/grupos/{GrupoId}/cuentas")
	public Cuenta addCuenta (
			@PathVariable Long ClaseId,
			@PathVariable Long GrupoId,
			@RequestBody Cuenta cuenta ){

		Clase existingClase = claseServiceImpl.findById(ClaseId);

		if(null == existingClase){
			return null;
		}

		List<Grupo> grupos = existingClase.getGrupos();

		Grupo grupo = null;

		for(Grupo _grupo: grupos){
			if(_grupo.getId() == GrupoId){
				grupo = _grupo;
				break;
			}
		}

		if(null == grupo){
			return null;
		}

		grupo.getCuentas().add(cuenta);
		grupoServiceImpl.update(grupo);

		return cuenta;
	}

	@GetMapping("/clases/{ClaseId}/grupos/{GrupoId}/cuentas")
	public List<Cuenta> listCuentas (
			@PathVariable Long ClaseId,
			@PathVariable Long GrupoId ){

		Clase existingClase = claseServiceImpl.findById(ClaseId);

		if(null == existingClase){
			return null;
		}

		List<Grupo> grupos = existingClase.getGrupos();
		Grupo grupo = null;

		for(Grupo _grupo: grupos){
			if(_grupo.getId() == GrupoId){
				grupo = _grupo;
				break;
			}
		}

		if(null == grupo){
			return null;
		}

		return grupo.getCuentas();
	}

	@GetMapping("/clases/{ClaseId}/grupos/{GrupoId}/cuentas/{CuentaId}")
	public Cuenta getCuenta (
			@PathVariable Long ClaseId,
			@PathVariable Long GrupoId,
			@PathVariable Long CuentaId
	) {
		Clase existingClase = claseServiceImpl.findById(ClaseId);

		if(null == existingClase){
			return null;
		}

		List<Grupo> grupos = existingClase.getGrupos();
		Grupo grupo = null;

		for(Grupo _grupo: grupos){
			if(_grupo.getId() == GrupoId){
				grupo = _grupo;
				break;
			}
		}

		if(null == grupo){
			return null;
		}

		List<Cuenta> cuentas = grupo.getCuentas();

		for(Cuenta cuenta: cuentas){
			if(cuenta.getId() == CuentaId){
				return cuenta;
			}
		}

		return null;
	}

	@PostMapping("/clases/{ClaseId}/grupos/{GrupoId}/cuentas/{CuentaId}/subcuentas")
	public SubCuenta addSubcuenta (
			@PathVariable Long ClaseId,
			@PathVariable Long GrupoId,
			@PathVariable Long CuentaId,
			@RequestBody SubCuenta subcuenta ){

		Clase existingClase = claseServiceImpl.findById(ClaseId);

		if(null == existingClase){
			return null;
		}

		List<Grupo> grupos = existingClase.getGrupos();
		Grupo grupo = null;

		for(Grupo _grupo: grupos){
			if(_grupo.getId() == GrupoId){
				grupo = _grupo;
				break;
			}
		}

		if(null == grupo){
			return null;
		}

		List<Cuenta> cuentas = grupo.getCuentas();
		Cuenta cuenta = null;

		for(Cuenta _cuenta: cuentas){
			if(_cuenta.getId() == CuentaId){
				cuenta = _cuenta;
				break;
			}
		}

		if(null == cuenta){
			return null;
		}

		cuenta.getSubCuentas().add(subcuenta);
		cuentaServiceImpl.update(cuenta);

		return subcuenta;
	}

	@GetMapping("/clases/{ClaseId}/grupos/{GrupoId}/cuentas/{CuentaId}/subcuentas")
	public List<SubCuenta> listSubCuentas (
			@PathVariable Long ClaseId,
			@PathVariable Long GrupoId,
			@PathVariable Long CuentaId
	) {
		Clase existingClase = claseServiceImpl.findById(ClaseId);
		Assert.notNull(existingClase, "Clase no encontrada");
		Grupo existingGrupo = existingClase.getGrupo(GrupoId);
		Assert.notNull(existingGrupo, "Grupo no encontrado");
		Cuenta existingCuenta = existingGrupo.getCuenta(CuentaId);
		Assert.notNull(existingCuenta, "Cuenta no encontrada");
		return existingCuenta.getSubCuentas();
	}

	@GetMapping("/clases/{ClaseId}/grupos/{GrupoId}/cuentas/{CuentaId}/subcuentas/{SubCuentaId}")
	public SubCuenta getSubCuenta (
			@PathVariable Long ClaseId,
			@PathVariable Long GrupoId,
			@PathVariable Long CuentaId,
			@PathVariable Long SubCuentaId
	) {
		Clase existingClase = claseServiceImpl.findById(ClaseId);
		Assert.notNull(existingClase, "Clase no encontrada");
		Grupo existingGrupo = existingClase.getGrupo(GrupoId);
		Assert.notNull(existingGrupo, "Grupo no encontrado");
		Cuenta existingCuenta = existingGrupo.getCuenta(CuentaId);
		Assert.notNull(existingCuenta, "Cuenta no encontrada");
		return existingCuenta.getSubCuenta(SubCuentaId);
	}

	@PostMapping("/clases/{ClaseId}/grupos/{GrupoId}/cuentas/{CuentaId}/subcuentas/{SubCuentaId}/auxiliares")
	public Auxiliar addAuxiliar (
			@PathVariable Long ClaseId,
			@PathVariable Long GrupoId,
			@PathVariable Long CuentaId,
			@PathVariable Long SubCuentaId,
			@RequestBody Auxiliar auxiliar
	) {
		Clase existingClase = claseServiceImpl.findById(ClaseId);
		Assert.notNull(existingClase, "Clase no encontrada");
		Grupo existingGrupo = existingClase.getGrupo(GrupoId);
		Assert.notNull(existingGrupo, "Grupo no encontrado");
		Cuenta existingCuenta = existingGrupo.getCuenta(CuentaId);
		Assert.notNull(existingCuenta, "Cuenta no encontrada");
		SubCuenta existingSubCuenta = existingCuenta.getSubCuenta(SubCuentaId);
		Assert.notNull(existingSubCuenta, "SubCuenta no encontrada");
		existingSubCuenta.getAuxiliares().add(auxiliar);
		subCuentaServiceImpl.update(existingSubCuenta);
		return auxiliar;
	}

	@GetMapping("/clases/{ClaseId}/grupos/{GrupoId}/cuentas/{CuentaId}/subcuentas/{SubCuentaId}/auxiliares")
	public List<Auxiliar> listAuxiliares (
			@PathVariable Long ClaseId,
			@PathVariable Long GrupoId,
			@PathVariable Long CuentaId,
			@PathVariable Long SubCuentaId
	) {
		Clase existingClase = claseServiceImpl.findById(ClaseId);
		Assert.notNull(existingClase, "Clase no encontrada");
		Grupo existingGrupo = existingClase.getGrupo(GrupoId);
		Assert.notNull(existingGrupo, "Grupo no encontrado");
		Cuenta existingCuenta = existingGrupo.getCuenta(CuentaId);
		Assert.notNull(existingCuenta, "Cuenta no encontrada");
		SubCuenta existingSubCuenta = existingCuenta.getSubCuenta(SubCuentaId);
		Assert.notNull(existingSubCuenta, "SubCuenta no encontrada");
		return existingSubCuenta.getAuxiliares();
	}

	@GetMapping("/clases/{ClaseId}/grupos/{GrupoId}/cuentas/{CuentaId}/subcuentas/{SubCuentaId}/auxiliares/{AuxiliarId}")
	public Auxiliar getAuxiliar (
			@PathVariable Long ClaseId,
			@PathVariable Long GrupoId,
			@PathVariable Long CuentaId,
			@PathVariable Long SubCuentaId,
			@PathVariable Long AuxiliarId
	) {
		Clase existingClase = claseServiceImpl.findById(ClaseId);
		Assert.notNull(existingClase, "Clase no encontrada");
		Grupo existingGrupo = existingClase.getGrupo(GrupoId);
		Assert.notNull(existingGrupo, "Grupo no encontrado");
		Cuenta existingCuenta = existingGrupo.getCuenta(CuentaId);
		Assert.notNull(existingGrupo, "Cuenta no encontrada");
		SubCuenta existingSubCuenta = existingCuenta.getSubCuenta(SubCuentaId);
		Assert.notNull(existingSubCuenta, "SubCuenta no encontrada");
		return existingSubCuenta.getAuxiliar(AuxiliarId);
	}

}
