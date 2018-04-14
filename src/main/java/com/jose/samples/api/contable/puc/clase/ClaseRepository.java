package com.jose.samples.api.contable.puc.clase;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClaseRepository extends JpaRepository<Clase, Long> {
	List<Clase> findByCodigoContainingOrNombreContaining (String codigo, String nombre);
	Optional<Clase> findById(Long id);
	void deleteById(Long id);
}
