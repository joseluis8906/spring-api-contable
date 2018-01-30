package com.jose.samples.api.contable.puc.clase;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClaseRepository extends JpaRepository<Clase, Long> {
	public List<Clase> findByCodigoContainingOrNombreContaining (String codigo, String nombre);
	public Clase findById(Long id);
}
