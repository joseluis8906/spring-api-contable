package com.jose.samples.api.contable.puc.cuenta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
	public List<Cuenta> findByCodigoContainingOrNombreContaining(String codigo, String nombre);
	public Cuenta findById(Long id);
}
