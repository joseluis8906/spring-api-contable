package com.jose.samples.api.contable.puc.subcuenta;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SubCuentaServiceImpl implements SubCuentaService {

	private SubCuentaRepository subCuentaRepository;

	public SubCuentaServiceImpl(SubCuentaRepository cuentaRepository) {
		this.subCuentaRepository = subCuentaRepository;
	}

	@Override
	public SubCuenta findById (Long id) {
		return this.subCuentaRepository.findById(id);
	}

	@Override
	public List<SubCuenta> findByCodigoOrNombre (String codigo, String nombre) {
		return this.subCuentaRepository.findByCodigoContainingOrNombreContaining (codigo, nombre);
	}

	@Override
	public List<SubCuenta> findAll() {
		return this.subCuentaRepository.findAll();
	}

	@Override
	public SubCuenta add (SubCuenta subcuenta) {
		this.subCuentaRepository.save(subcuenta);
		return subcuenta;
	}

	@Override
	public void update (SubCuenta subcuenta) {
		this.subCuentaRepository.save(subcuenta);
	}

	@Override
	public void delete (Long id) {
		this.subCuentaRepository.delete(id);
	}
}
