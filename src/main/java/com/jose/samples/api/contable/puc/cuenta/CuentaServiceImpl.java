package com.jose.samples.api.contable.puc.cuenta;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CuentaServiceImpl implements CuentaService {

	private CuentaRepository cuentaRepository;

	public CuentaServiceImpl(CuentaRepository cuentaRepository) {
		this.cuentaRepository = cuentaRepository;
	}

	@Override
	public Optional<Cuenta> findById (Long id) {
		return this.cuentaRepository.findById(id);
	}

	@Override
	public List<Cuenta> findByCodigoOrNombre (String codigo, String nombre) {
		return this.cuentaRepository.findByCodigoContainingOrNombreContaining (codigo, nombre);
	}

	//@Override
	/*public List<Cuenta> findByClase(Long clase) {
		return this.cuentaRepository.findByClase(clase);
	}*/

	@Override
	public List<Cuenta> findAll() {
		return this.cuentaRepository.findAll();
	}

	@Override
	public Cuenta add (Cuenta clase) {
		this.cuentaRepository.save(clase);
		return clase;
	}

	@Override
	public void update (Cuenta clase) {
		this.cuentaRepository.save(clase);
	}

	@Override
	public void delete (Long id) {
		this.cuentaRepository.deleteById(id);
	}
}
