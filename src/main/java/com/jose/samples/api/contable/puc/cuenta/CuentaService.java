package com.jose.samples.api.contable.puc.cuenta;

import java.util.List;
import java.util.Optional;

public interface CuentaService {
	Optional<Cuenta> findById(Long id);
	List<Cuenta> findByCodigoOrNombre(String codigo, String nombre);
	List<Cuenta> findAll();
	Cuenta add(Cuenta grupo);
	void update(Cuenta grupo);
	void delete(Long id);
}
