package com.jose.samples.api.contable.puc.cuenta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
	List<Cuenta> findByCodigoContainingOrNombreContaining(String codigo, String nombre);
	Optional<Cuenta> findById(Long id);
	void deleteById(Long id);
}
