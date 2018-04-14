package com.jose.samples.api.contable.puc.grupo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {
	List<Grupo> findByCodigoContainingOrNombreContaining (String codigo, String nombre);
	Optional<Grupo> findById(Long id);
	void deleteById(Long id);
}
