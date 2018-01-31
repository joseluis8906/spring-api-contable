package com.jose.samples.api.contable.puc.cuenta;

import io.jsonwebtoken.lang.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/puc/cuentas")
public class CuentaController {

	private CuentaServiceImpl cuentaServiceImpl;

	public CuentaController(CuentaServiceImpl cuentaServiceImpl) {
		this.cuentaServiceImpl = cuentaServiceImpl;
	}

	@GetMapping("/{id}")
	public Cuenta findOne (@PathVariable Long id) {
		return cuentaServiceImpl.findById(id);
	}

	@GetMapping("/{codigo}/{nombre}")
	public List<Cuenta> findByCodigoOrNombre (@PathVariable String codigo, @PathVariable String nombre) {
		return cuentaServiceImpl.findByCodigoOrNombre(codigo, nombre);
	}

	@GetMapping
	public List<Cuenta> listAll () {
		return cuentaServiceImpl.findAll();
	}

	@PostMapping
	public Cuenta add (@RequestBody Cuenta cuenta) {
		return cuentaServiceImpl.add (cuenta);
	}

	@PutMapping("/{id}")
	public void update (@PathVariable Long id, @RequestBody Cuenta cuenta) {
		Cuenta existingCuenta = cuentaServiceImpl.findById(id);
		Assert.notNull(existingCuenta, "Cuenta no encontrada");
		existingCuenta.setCodigo(cuenta.getCodigo());
		existingCuenta.setNombre(cuenta.getNombre());
		cuentaServiceImpl.update(existingCuenta);
	}

	@DeleteMapping("/{id}")
	public void delete (@PathVariable long id) {
		cuentaServiceImpl.delete(id);
	}
}
