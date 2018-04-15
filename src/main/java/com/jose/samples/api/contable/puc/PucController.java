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
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/puc")
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

	@PostMapping("/clases")
	public ResponseEntity add (@RequestBody Clase clase) {
		claseServiceImpl.add (clase);
		return ResponseEntity.status(HttpStatus.CREATED).body(clase);
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
		List<Grupo> grupos = existingClase.getGrupos();

		for(Grupo _grupo: grupos){
			if(_grupo.getCodigo().equals(grupo.getCodigo())){
				return _grupo;
			}
		}

		return null;
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

		List<Cuenta> cuentas = grupo.getCuentas();
		for (Cuenta _cuenta: cuentas){
			if(_cuenta.getCodigo().equals(cuenta.getCodigo())){
				return _cuenta;
			}
		}

		return null;
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

		List<SubCuenta> subcuentas = cuenta.getSubCuentas();
		for (SubCuenta _subcuenta: subcuentas){
			if(_subcuenta.getCodigo().equals(subcuenta.getCodigo())){
				return _subcuenta;
			}
		}

		return null;
	}

	@GetMapping("/clases/{ClaseId}/grupos/{GrupoId}/cuentas/{CuentaId}/subcuentas")
	public List<SubCuenta> listSubCuentas (
			@PathVariable Long ClaseId,
			@PathVariable Long GrupoId,
			@PathVariable Long CuentaId ){

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


		return cuenta.getSubCuentas();

	}

	@GetMapping("/clases/{ClaseId}/grupos/{GrupoId}/cuentas/{CuentaId}/subcuentas/{SubCuentaId}")
	public SubCuenta getSubCuenta (
			@PathVariable Long ClaseId,
			@PathVariable Long GrupoId,
			@PathVariable Long CuentaId,
			@PathVariable Long SubCuentaId	){

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

		List<SubCuenta> subcuentas = cuenta.getSubCuentas();

		for(SubCuenta subcuenta: subcuentas){
			if(subcuenta.getId() == SubCuentaId){
				return subcuenta;
			}
		}

		return null;
	}

	@PostMapping("/clases/{ClaseId}/grupos/{GrupoId}/cuentas/{CuentaId}/subcuentas/{SubCuentaId}/auxiliares")
	public Auxiliar addAuxiliar (
			@PathVariable Long ClaseId,
			@PathVariable Long GrupoId,
			@PathVariable Long CuentaId,
			@PathVariable Long SubCuentaId,
			@RequestBody Auxiliar auxiliar	){

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

		List<SubCuenta> subcuentas = cuenta.getSubCuentas();
		SubCuenta subcuenta = null;

		for(SubCuenta _subcuenta: subcuentas){
			if(_subcuenta.getId() == SubCuentaId){
				subcuenta = _subcuenta;
				break;
			}
		}

		if(null == subcuenta){
			return null;
		}

		subcuenta.getAuxiliares().add(auxiliar);
		subCuentaServiceImpl.update(subcuenta);

		List<Auxiliar> auxiliares = subcuenta.getAuxiliares();
		for (Auxiliar _auxiliar: auxiliares){
			if(_auxiliar.getCodigo().equals(auxiliar.getCodigo())){
				return _auxiliar;
			}
		}

		return null;
	}

	@GetMapping("/clases/{ClaseId}/grupos/{GrupoId}/cuentas/{CuentaId}/subcuentas/{SubCuentaId}/auxiliares")
	public List<Auxiliar> listAuxiliares (
			@PathVariable Long ClaseId,
			@PathVariable Long GrupoId,
			@PathVariable Long CuentaId,
			@PathVariable Long SubCuentaId
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

		List<SubCuenta> subcuentas = cuenta.getSubCuentas();
		SubCuenta subcuenta = null;

		for(SubCuenta _subcuenta: subcuentas){
			if(_subcuenta.getId() == SubCuentaId){
				subcuenta = _subcuenta;
				break;
			}
		}

		if(null == subcuenta){
			return null;
		}

		return subcuenta.getAuxiliares();
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

		List<SubCuenta> subcuentas = cuenta.getSubCuentas();
		SubCuenta subcuenta = null;

		for(SubCuenta _subcuenta: subcuentas){
			if(_subcuenta.getId() == SubCuentaId){
				subcuenta = _subcuenta;
				break;
			}
		}

		if(null == subcuenta){
			return null;
		}

		List<Auxiliar> auxiliares = subcuenta.getAuxiliares();

		for(Auxiliar auxiliar: auxiliares){
			if(auxiliar.getId() == AuxiliarId){
				return auxiliar;
			}
		}

		return null;
	}
}
