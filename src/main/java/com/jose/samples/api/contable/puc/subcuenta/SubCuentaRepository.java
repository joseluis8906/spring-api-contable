package com.jose.samples.api.contable.puc.subcuenta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubCuentaRepository extends JpaRepository<SubCuenta, Long> {
	List<SubCuenta> findByCodigoContainingOrNombreContaining(String codigo, String nombre);
	Optional<SubCuenta> findById(Long id);
	void deleteById(Long id);
}
