package com.jose.samples.api.contable.puc.subcuenta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCuentaRepository extends JpaRepository<SubCuenta, Long> {
	public List<SubCuenta> findByCodigoContainingOrNombreContaining(String codigo, String nombre);
	public SubCuenta findById(Long id);
}
